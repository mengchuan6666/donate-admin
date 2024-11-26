package com.mengadmin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengadmin.common.core.web.PageParam;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.entity.TChildInfo;
import com.mengadmin.common.system.mapper.TChildInfoMapper;
import com.mengadmin.common.system.param.TChildInfoParam;
import com.mengadmin.common.system.service.TChildInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 儿童信息表业务实现
 * Created by 创建人 on 2024-02-07 20:31:41
 */
@Service
public class TChildInfoServiceImpl extends ServiceImpl<TChildInfoMapper, TChildInfo> implements TChildInfoService  {

    @Override
    public PageResult<TChildInfo> pageRel(TChildInfoParam param) {
        PageParam<TChildInfo, TChildInfoParam> page = new PageParam<>(param);
        page.setDefaultOrder("create_time desc");
        List<TChildInfo> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public TChildInfo getById(Integer id) {
        return baseMapper.selectOneRel(id);
    }

}

