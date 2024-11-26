package com.mengadmin.common.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengadmin.common.system.entity.TDonateGoods;
import com.mengadmin.common.system.param.TDonateGoodsParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 捐赠物资表Mapper
 *
 * @author EleAdmin
 * @since 2024-02-12 16:44:24
 */
public interface TDonateGoodsMapper extends BaseMapper<TDonateGoods> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<TDonateGoods>
     */
    List<TDonateGoods> selectPageRel(@Param("page") IPage<TDonateGoods> page,
                             @Param("param") TDonateGoodsParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<TDonateGoods> selectListRel(@Param("param") TDonateGoodsParam param);

}
