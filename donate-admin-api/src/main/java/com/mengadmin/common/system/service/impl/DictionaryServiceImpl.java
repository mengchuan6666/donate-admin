package com.mengadmin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengadmin.common.system.entity.Dictionary;
import com.mengadmin.common.system.mapper.DictionaryMapper;
import com.mengadmin.common.system.service.DictionaryService;
import org.springframework.stereotype.Service;

/**
 * 字典Service实现
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

}
