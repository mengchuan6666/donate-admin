package com.mengadmin.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengadmin.common.core.web.PageResult;
import com.mengadmin.common.system.entity.DictionaryData;
import com.mengadmin.common.system.param.DictionaryDataParam;

import java.util.List;

/**
 * 字典数据Service
 */
public interface DictionaryDataService extends IService<DictionaryData> {

    /**
     * 关联分页查询
     *
     * @param param 查询参数
     * @return PageResult<DictionaryData>
     */
    PageResult<DictionaryData> pageRel(DictionaryDataParam param);

    /**
     * 关联查询全部
     *
     * @param param 查询参数
     * @return List<DictionaryData>
     */
    List<DictionaryData> listRel(DictionaryDataParam param);

    /**
     * 根据id查询
     *
     * @param dictDataId 字典数据id
     * @return DictionaryData
     */
    DictionaryData getByIdRel(Integer dictDataId);

    /**
     * 根据dictCode和dictDataName查询
     *
     * @param dictCode     字典标识
     * @param dictDataName 字典项名称
     * @return DictionaryData
     */
    DictionaryData getByDictCodeAndName(String dictCode, String dictDataName);

}
