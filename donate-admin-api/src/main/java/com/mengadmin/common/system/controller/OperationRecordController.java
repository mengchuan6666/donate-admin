package com.mengadmin.common.system.controller;

import com.mengadmin.common.core.annotation.OperationLog;
import com.mengadmin.common.core.web.ApiResult;
import com.mengadmin.common.core.web.BaseController;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.entity.OperationRecord;
import com.mengadmin.common.system.param.OperationRecordParam;
import com.mengadmin.common.system.service.OperationRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 操作日志控制器
 */
@Api(tags = "操作日志")
@RestController
@RequestMapping("/system/operation-record")
public class OperationRecordController extends BaseController {
    @Resource
    private OperationRecordService operationRecordService;

    /**
     * 分页查询操作日志
     */
    @ApiOperation("分页查询操作日志")
    @GetMapping("/page")
    public ApiResult<PageResult<OperationRecord>> page(OperationRecordParam param) {
        return success(operationRecordService.pageRel(param));
    }

    /**
     * 查询全部操作日志
     */
    @OperationLog
    @ApiOperation("查询全部操作日志")
    @GetMapping()
    public ApiResult<List<OperationRecord>> list(OperationRecordParam param) {
        return success(operationRecordService.listRel(param));
    }

    /**
     * 根据id查询操作日志
     */
    @ApiOperation("根据id查询操作日志")
    @GetMapping("/{id}")
    public ApiResult<OperationRecord> get(@PathVariable("id") Integer id) {
        return success(operationRecordService.getByIdRel(id));
    }

}
