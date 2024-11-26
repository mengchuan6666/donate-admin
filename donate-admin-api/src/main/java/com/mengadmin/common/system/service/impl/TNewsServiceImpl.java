package com.mengadmin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengadmin.common.system.entity.TNews;
import com.mengadmin.common.system.mapper.TNewsMapper;
import com.mengadmin.common.system.service.TNewsService;
import org.springframework.stereotype.Service;

/**
 * 新闻服务实现类
 */
@Service
public class TNewsServiceImpl extends ServiceImpl<TNewsMapper, TNews> implements TNewsService {

}
