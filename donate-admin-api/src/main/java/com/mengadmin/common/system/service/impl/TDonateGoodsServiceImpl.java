package com.mengadmin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengadmin.common.core.web.PageParam;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.mapper.TDonateGoodsMapper;
import com.mengadmin.common.system.param.TDonateGoodsParam;
import com.mengadmin.common.system.service.TDonateGoodsService;
import com.mengadmin.common.system.entity.TDonateGoods;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 捐赠物资表Service实现
 *
 * @author EleAdmin
 * @since 2024-02-12 16:44:24
 */
@Service
public class TDonateGoodsServiceImpl extends ServiceImpl<TDonateGoodsMapper, TDonateGoods> implements TDonateGoodsService {

    @Override
    public PageResult<TDonateGoods> pageRel(TDonateGoodsParam param) {
        PageParam<TDonateGoods, TDonateGoodsParam> page = new PageParam<>(param);
        //page.setDefaultOrder("create_time desc");
        List<TDonateGoods> list = baseMapper.selectPageRel(page, param);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    public List<TDonateGoods> listRel(TDonateGoodsParam param) {
        List<TDonateGoods> list = baseMapper.selectListRel(param);
        // 排序
        PageParam<TDonateGoods, TDonateGoodsParam> page = new PageParam<>();
        //page.setDefaultOrder("create_time desc");
        return page.sortRecords(list);
    }

    @Override
    public TDonateGoods getByIdRel(Integer id) {
        TDonateGoodsParam param = new TDonateGoodsParam();
        param.setId(id);
        return param.getOne(baseMapper.selectListRel(param));
    }

}
