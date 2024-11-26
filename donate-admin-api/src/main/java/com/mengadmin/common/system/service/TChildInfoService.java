package com.mengadmin.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.entity.TChildInfo;
import com.mengadmin.common.system.param.TChildInfoParam;

/**
 * 儿童信息表业务接口
 * Created by 创建人 on 2024-02-07 20:31:41
 */
public interface TChildInfoService extends IService<TChildInfo> {

    /**
     * 关联分页查询儿童信息
     *
     * @param param 查询参数
     * @return PageResult<User>
     */
    PageResult<TChildInfo> pageRel(TChildInfoParam param);

    TChildInfo getById(Integer id);
}

