package com.mengadmin.common.system.controller;

import cn.hutool.core.date.DateUtil;
import com.mengadmin.common.core.annotation.OperationLog;
import com.mengadmin.common.core.web.ApiResult;
import com.mengadmin.common.core.web.BaseController;
import com.mengadmin.common.core.web.PageParam;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.entity.TComment;
import com.mengadmin.common.system.entity.User;
import com.mengadmin.common.system.param.TCommentParam;
import com.mengadmin.common.system.service.TCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 评论信息控制器
 */
@Api(tags = "评论信息管理")
@RestController
@RequestMapping("/system/comment")
public class TCommentController extends BaseController {
    @Resource
    private TCommentService tCommentService;

    @OperationLog
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ApiResult<PageResult<TComment>> page(TCommentParam param) {
        PageParam<TComment, TCommentParam> page = new PageParam<>(param);
        return success(tCommentService.page(page, page.getWrapper()));
    }

    @OperationLog
    @ApiOperation("查询全部")
    @GetMapping()
    public ApiResult<List<TComment>> list(TCommentParam param) {
        PageParam<TComment, TCommentParam> page = new PageParam<>(param);
        return success(tCommentService.list(page.getOrderWrapper()));
    }

    @OperationLog
    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public ApiResult<TComment> get(@PathVariable("id") Integer id) {
        return success(tCommentService.getById(id));
    }

    @OperationLog
    @ApiOperation("添加")
    @PostMapping()
    public ApiResult<?> save(@RequestBody TComment comment) {
        User user = getLoginUser();
        comment.setCreateUser(user.getNickname());
        comment.setCreateTime(DateUtil.now());
        if (tCommentService.save(comment)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @OperationLog
    @ApiOperation("修改")
    @PutMapping()
    public ApiResult<?> update(@RequestBody TComment comment) {
        if (tCommentService.updateById(comment)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @OperationLog
    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (tCommentService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }


}
