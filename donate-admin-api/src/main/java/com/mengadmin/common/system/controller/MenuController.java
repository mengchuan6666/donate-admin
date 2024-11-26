package com.mengadmin.common.system.controller;

import com.mengadmin.common.core.annotation.OperationLog;
import com.mengadmin.common.core.web.*;
import com.mengadmin.common.system.entity.Menu;
import com.mengadmin.common.system.param.MenuParam;
import com.mengadmin.common.system.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单控制器
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/system/menu")
public class MenuController extends BaseController {
    @Resource
    private MenuService menuService;

    @OperationLog
    @ApiOperation("分页查询菜单")
    @GetMapping("/page")
    public ApiResult<PageResult<Menu>> page(MenuParam param) {
        PageParam<Menu, MenuParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(menuService.page(page, page.getWrapper()));
    }

    @OperationLog
    @ApiOperation("查询全部菜单")
    @GetMapping()
    public ApiResult<List<Menu>> list(MenuParam param) {
        PageParam<Menu, MenuParam> page = new PageParam<>(param);
        page.setDefaultOrder("sort_number");
        return success(menuService.list(page.getOrderWrapper()));
    }


    @OperationLog
    @ApiOperation("根据id查询菜单")
    @GetMapping("/{id}")
    public ApiResult<Menu> get(@PathVariable("id") Integer id) {
        return success(menuService.getById(id));
    }


    @OperationLog
    @ApiOperation("添加菜单")
    @PostMapping()
    public ApiResult<?> add(@RequestBody Menu menu) {
        if (menuService.save(menu)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }


    @OperationLog
    @ApiOperation("修改菜单")
    @PutMapping()
    public ApiResult<?> update(@RequestBody Menu menu) {
        if (menuService.updateById(menu)) {
            return success("修改成功");
        }
        return fail("修改失败");
    }


    @OperationLog
    @ApiOperation("删除菜单")
    @DeleteMapping("/{id}")
    public ApiResult<?> remove(@PathVariable("id") Integer id) {
        if (menuService.removeById(id)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }


    @OperationLog
    @ApiOperation("批量添加菜单")
    @PostMapping("/batch")
    public ApiResult<?> saveBatch(@RequestBody List<Menu> menus) {
        if (menuService.saveBatch(menus)) {
            return success("添加成功");
        }
        return fail("添加失败");
    }


    @OperationLog
    @ApiOperation("批量修改菜单")
    @PutMapping("/batch")
    public ApiResult<?> updateBatch(@RequestBody BatchParam<Menu> batchParam) {
        if (batchParam.update(menuService, "menu_id")) {
            return success("修改成功");
        }
        return fail("修改失败");
    }


    @OperationLog
    @ApiOperation("批量删除菜单")
    @DeleteMapping("/batch")
    public ApiResult<?> removeBatch(@RequestBody List<Integer> ids) {
        if (menuService.removeByIds(ids)) {
            return success("删除成功");
        }
        return fail("删除失败");
    }

}
