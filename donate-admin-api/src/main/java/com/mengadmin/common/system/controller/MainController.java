package com.mengadmin.common.system.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mengadmin.common.core.annotation.OperationLog;
import com.mengadmin.common.core.config.ConfigProperties;
import com.mengadmin.common.core.security.JwtSubject;
import com.mengadmin.common.core.security.JwtUtil;
import com.mengadmin.common.core.utils.CommonUtil;
import com.mengadmin.common.core.web.ApiResult;
import com.mengadmin.common.core.web.BaseController;
import com.mengadmin.common.system.entity.*;
import com.mengadmin.common.system.param.RegiterParam;
import com.mengadmin.common.system.result.CaptchaResult;
import com.mengadmin.common.system.param.LoginParam;
import com.mengadmin.common.system.result.LoginResult;
import com.mengadmin.common.system.param.UpdatePasswordParam;
import com.mengadmin.common.system.service.*;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 登录认证控制器
 */
@Api(tags = "登录认证")
@RestController
public class MainController extends BaseController {
    @Resource
    private ConfigProperties configProperties;
    @Resource
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleMenuService roleMenuService;
    @Resource
    private LoginRecordService loginRecordService;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public ApiResult<LoginResult> login(@RequestBody LoginParam param, HttpServletRequest request) {
        String username = param.getUsername();
        User user = userService.getByUsername(username);
        if (user == null) {
            String message = "账号不存在";
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, message, request);
            return fail(message, null);
        }
        if (!user.getStatus().equals(0)) {
            String message = "账号被冻结";
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, message, request);
            return fail(message, null);
        }
        if (!userService.comparePassword(user.getPassword(), param.getPassword())) {
            String message = "密码错误";
            loginRecordService.saveAsync(username, LoginRecord.TYPE_ERROR, message, request);
            return fail(message, null);
        }
        loginRecordService.saveAsync(username, LoginRecord.TYPE_LOGIN, null, request);
        // 签发token
        String access_token = JwtUtil.buildToken(new JwtSubject(username),
                configProperties.getTokenExpireTime(), configProperties.getTokenKey());
        return success("登录成功", new LoginResult(access_token, user));
    }

    @ApiOperation("用户注册")
    @PostMapping("/regiter")
    public ApiResult<?> regiter(@RequestBody RegiterParam param, HttpServletRequest request) {
        String username = param.getUsername();
        User user = userService.getByUsername(username);
        if(user != null){
            return fail("此用户已经注册");
        }else if(!param.getPassword().equals(param.getPasstwo())){
            return fail("确认密码与密码不一致");
        }else{
            User user2 = new User();
            user2.setPassword(userService.encodePassword(param.getPassword()));
            user2.setUsername(param.getUsername());
            user2.setType("普通用户");
            user2.setNickname(param.getNickname());
            user2.setEmail(param.getEmail());
            user2.setPhone(param.getPhone());
            if(userService.saveUser(user2)){
                User user1 = userService.getByUsername(username);
                Role role = roleService.getOne(new QueryWrapper<Role>().eq("role_name","普通用户"));
                UserRole userRole = new UserRole();
                userRole.setCreateTime(DateUtil.date());
                userRole.setRoleId(role.getRoleId());
                userRole.setRoleName(role.getRoleName());
                userRole.setUserId(user1.getUserId());
                userRoleService.save(userRole);
                return success("注册成功");
            }else{
                return success("注册失败");
            }
        }
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping("/auth/user")
    public ApiResult<User> userInfo() {
        return success(userService.getByIdRel(getLoginUserId()));
    }

    @ApiOperation("获取登录用户菜单")
    @GetMapping("/auth/menu")
    public ApiResult<List<Menu>> userMenu() {
        List<Menu> menus = roleMenuService.listMenuByUserId(getLoginUserId(), Menu.TYPE_MENU);
        return success(CommonUtil.toTreeData(menus, 0, Menu::getParentId, Menu::getMenuId, Menu::setChildren));
    }

    @OperationLog
    @ApiOperation("修改个人信息")
    @PutMapping("/auth/user")
    public ApiResult<User> updateInfo(@RequestBody User user) {
        user.setUserId(getLoginUserId());
        // 不能修改的字段
        user.setUsername(null);
        user.setPassword(null);
        user.setStatus(null);
        if (userService.updateById(user)) {
            return success(userService.getByIdRel(user.getUserId()));
        }
        return fail("保存失败", null);
    }

    @OperationLog
    @ApiOperation("修改自己密码")
    @PutMapping("/auth/password")
    public ApiResult<?> updatePassword(@RequestBody UpdatePasswordParam param) {
        if (StrUtil.hasBlank(param.getOldPassword(), param.getPassword())) {
            return fail("参数不能为空");
        }
        Integer userId = getLoginUserId();
        if (userId == null) {
            return fail("未登录");
        }
        if (!userService.comparePassword(userService.getById(userId).getPassword(), param.getOldPassword())) {
            return fail("原密码输入不正确");
        }
        User user = new User();
        user.setUserId(userId);
        user.setPassword(userService.encodePassword(param.getPassword()));
        if (userService.updateById(user)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @ApiOperation("图形验证码")
    @GetMapping("/captcha")
    public ApiResult<CaptchaResult> captcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        return success(new CaptchaResult(specCaptcha.toBase64(), specCaptcha.text().toLowerCase()));
    }

}
