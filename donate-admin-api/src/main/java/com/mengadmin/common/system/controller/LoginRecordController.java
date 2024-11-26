package com.mengadmin.common.system.controller;

import com.mengadmin.common.core.annotation.OperationLog;
import com.mengadmin.common.core.web.ApiResult;
import com.mengadmin.common.core.web.BaseController;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.entity.LoginRecord;
import com.mengadmin.common.system.param.LoginRecordParam;
import com.mengadmin.common.system.service.LoginRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 登录日志控制器
 */
@Api(tags = "登录日志")
@RestController
@RequestMapping("/system/login-record")
public class LoginRecordController extends BaseController {
    @Resource
    private LoginRecordService loginRecordService;

    @OperationLog
    @ApiOperation("分页查询登录日志")
    @GetMapping("/page")
    public ApiResult<PageResult<LoginRecord>> page(LoginRecordParam param) {
        return success(loginRecordService.pageRel(param));
    }

    @OperationLog
    @ApiOperation("查询全部登录日志")
    @GetMapping()
    public ApiResult<List<LoginRecord>> list(LoginRecordParam param) {
        return success(loginRecordService.listRel(param));
    }

    @OperationLog
    @ApiOperation("根据id查询登录日志")
    @GetMapping("/{id}")
    public ApiResult<LoginRecord> get(@PathVariable("id") Integer id) {
        return success(loginRecordService.getByIdRel(id));
    }

}
