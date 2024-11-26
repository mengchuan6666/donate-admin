package com.mengadmin.common.system.controller;

import cn.hutool.core.date.DateUtil;
import com.mengadmin.common.core.annotation.OperationLog;
import com.mengadmin.common.core.web.ApiResult;
import com.mengadmin.common.core.web.BaseController;
import com.mengadmin.common.core.web.PageParam;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.entity.TNotice;
import com.mengadmin.common.system.entity.User;
import com.mengadmin.common.system.param.TNoticeParam;
import com.mengadmin.common.system.service.TNoticeService;
import com.mengadmin.common.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 公告控制器
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:02
 */
@Api(tags = "公告管理")
@RestController
@RequestMapping("/system/notice")
public class TNoticeController extends BaseController {
    @Resource
    private TNoticeService tNoticeService;
    @Resource
    private UserService userService;

    @OperationLog
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ApiResult<PageResult<TNotice>> page(TNoticeParam param) {
        PageParam<TNotice, TNoticeParam> page = new PageParam<>(param);
        return success(tNoticeService.page(page, page.getWrapper()));
    }

    @OperationLog
    @ApiOperation("查询全部")
    @GetMapping()
    public ApiResult<List<TNotice>> list(TNoticeParam param) {
        PageParam<TNotice, TNoticeParam> page = new PageParam<>(param);
        return success(tNoticeService.list(page.getOrderWrapper()));
    }

    @OperationLog
    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public ApiResult<TNotice> get(@PathVariable("id") Integer id) {
        return success(tNoticeService.getById(id));
    }

    @OperationLog
    @ApiOperation("添加")
    @PostMapping()
    public ApiResult<?> save(@RequestBody TNotice notice) {
        User user = getLoginUser();
        notice.setCreateUser(user.getNickname());
        notice.setCreateTime(DateUtil.now());
        notice.setStatus("0");
        if (tNoticeService.save(notice)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @OperationLog
    @ApiOperation("修改")
    @PutMapping()
    public ApiResult<?> update(@RequestBody TNotice notice) {
        if (tNoticeService.updateById(notice)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @OperationLog
    @ApiOperation("发布")
    @PutMapping("/{id}")
    public ApiResult<?> fabu(@PathVariable("id") Integer id) {
        TNotice notice = tNoticeService.getById(id);
        notice.setStatus("1");
        if (tNoticeService.updateById(notice)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @OperationLog
    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (tNoticeService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }


}
