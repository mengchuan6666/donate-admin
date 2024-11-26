package com.mengadmin.common.system.controller;

import cn.hutool.core.date.DateUtil;
import com.mengadmin.common.core.annotation.OperationLog;
import com.mengadmin.common.core.web.ApiResult;
import com.mengadmin.common.core.web.BaseController;
import com.mengadmin.common.core.web.PageParam;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.entity.TNews;
import com.mengadmin.common.system.entity.User;
import com.mengadmin.common.system.param.TNewsParam;
import com.mengadmin.common.system.service.TNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 新闻信息控制器
 */
@Api(tags = "新闻信息管理")
@RestController
@RequestMapping("/system/news")
public class TNewsController extends BaseController {
    @Resource
    private TNewsService newsService;

    @OperationLog
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ApiResult<PageResult<TNews>> page(TNewsParam param) {
        PageParam<TNews, TNewsParam> page = new PageParam<>(param);
        return success(newsService.page(page, page.getWrapper()));
    }

    @OperationLog
    @ApiOperation("查询全部")
    @GetMapping()
    public ApiResult<List<TNews>> list(TNewsParam param) {
        PageParam<TNews, TNewsParam> page = new PageParam<>(param);
        return success(newsService.list(page.getOrderWrapper()));
    }

    @OperationLog
    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public ApiResult<TNews> get(@PathVariable("id") Integer id) {
        return success(newsService.getById(id));
    }

    @OperationLog
    @ApiOperation("添加")
    @PostMapping()
    public ApiResult<?> save(@RequestBody TNews attraction) {
        User user = getLoginUser();
        attraction.setCreateUser(user.getNickname());
        attraction.setCreateTime(DateUtil.now());
        if (newsService.save(attraction)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }

    @OperationLog
    @ApiOperation("修改")
    @PutMapping()
    public ApiResult<?> update(@RequestBody TNews attraction) {
        if (newsService.updateById(attraction)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }

    @OperationLog
    @ApiOperation("删除")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (newsService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }


}
