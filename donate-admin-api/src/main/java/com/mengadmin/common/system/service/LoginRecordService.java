package com.mengadmin.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.entity.LoginRecord;
import com.mengadmin.common.system.param.LoginRecordParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 登录日志Service
 */
public interface LoginRecordService extends IService<LoginRecord> {

    /**
     * 关联分页查询
     *
     * @param param 查询参数
     * @return PageResult<LoginRecord>
     */
    PageResult<LoginRecord> pageRel(LoginRecordParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<LoginRecord>
     */
    List<LoginRecord> listRel(LoginRecordParam param);

    /**
     * 根据id查询
     *
     * @param id id
     * @return LoginRecord
     */
    LoginRecord getByIdRel(Integer id);

    /**
     * 异步添加
     *
     * @param username 用户账号
     * @param type     操作类型
     * @param comments 备注
     * @param request  HttpServletRequest
     */
    void saveAsync(String username, Integer type, String comments, HttpServletRequest request);

}
