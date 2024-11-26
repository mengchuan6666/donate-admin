package com.mengadmin.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mengadmin.common.core.web.PageParam;
import com.mengadmin.common.system.entity.TChildInfo;
import com.mengadmin.common.system.param.TChildInfoParam;

import java.util.List;

/**
 * 儿童信息Mapper
 */
public interface TChildInfoMapper extends BaseMapper<TChildInfo> {

    List<TChildInfo> selectPageRel(PageParam<TChildInfo, TChildInfoParam> page, TChildInfoParam param);

    TChildInfo selectOneRel(Integer id);

}
