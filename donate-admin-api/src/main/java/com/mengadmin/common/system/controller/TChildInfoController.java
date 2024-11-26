package com.mengadmin.common.system.controller;

import cn.hutool.core.date.DateUtil;
import com.mengadmin.common.core.annotation.OperationLog;
import com.mengadmin.common.core.web.ApiResult;
import com.mengadmin.common.core.web.BaseController;
import com.mengadmin.common.core.web.PageParam;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.entity.TChildInfo;
import com.mengadmin.common.system.entity.User;
import com.mengadmin.common.system.param.TChildInfoParam;
import com.mengadmin.common.system.service.TChildInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 儿童信息控制器
 */
@Api(tags = "儿童信息管理")
@RestController
@RequestMapping("/neirong/child")
public class TChildInfoController extends BaseController {
    @Resource
    private TChildInfoService tChildInfoService;

    @OperationLog
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ApiResult<PageResult<TChildInfo>> page(TChildInfoParam param) {
        return success(tChildInfoService.pageRel(param));
    }

    @OperationLog
    @ApiOperation("查询全部")
    @GetMapping()
    public ApiResult<List<TChildInfo>> list(TChildInfoParam param) {
        PageParam<TChildInfo, TChildInfoParam> page = new PageParam<>(param);
        return success(tChildInfoService.list(page.getOrderWrapper()));
    }

    @OperationLog
    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public ApiResult<TChildInfo> get(@PathVariable("id") Integer id) {
        return success(tChildInfoService.getById(id));
    }

    @OperationLog
    @ApiOperation("添加")
    @PostMapping()
    public ApiResult<?> save(@RequestBody TChildInfo tChildInfo) {
        User user = getLoginUser();

        tChildInfo.setCreateUser(user.getNickname());
        tChildInfo.setCreateTime(DateUtil.now());
        if (tChildInfoService.save(tChildInfo)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @OperationLog
    @ApiOperation("修改")
    @PutMapping()
    public ApiResult<?> update(@RequestBody TChildInfo tChildInfo) {
        User user = getLoginUser();
        tChildInfo.setCreateUser(user.getNickname());
        tChildInfo.setCreateTime(DateUtil.now());
        if (tChildInfoService.updateById(tChildInfo)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @OperationLog
    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (tChildInfoService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
