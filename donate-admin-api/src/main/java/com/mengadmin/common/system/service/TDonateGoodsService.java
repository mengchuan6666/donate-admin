package com.mengadmin.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.entity.TDonateGoods;
import com.mengadmin.common.system.param.TDonateGoodsParam;

import java.util.List;

/**
 * 捐赠物资表Service
 *
 * @author EleAdmin
 * @since 2024-02-12 16:44:24
 */
public interface TDonateGoodsService extends IService<TDonateGoods> {

    /**
     * 分页关联查询
     *
     * @param param 查询参数
     * @return PageResult<TDonateGoods>
     */
    PageResult<TDonateGoods> pageRel(TDonateGoodsParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<TDonateGoods>
     */
    List<TDonateGoods> listRel(TDonateGoodsParam param);

    /**
     * 根据id查询
     *
     * @param id 主键ID
     * @return TDonateGoods
     */
    TDonateGoods getByIdRel(Integer id);

}
