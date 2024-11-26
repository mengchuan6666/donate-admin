package com.mengadmin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengadmin.common.system.entity.TComment;
import com.mengadmin.common.system.mapper.TCommentMapper;
import com.mengadmin.common.system.service.TCommentService;
import org.springframework.stereotype.Service;

/**
 * 评论信息服务实现类
 */
@Service
public class TCommentServiceImpl extends ServiceImpl<TCommentMapper, TComment> implements TCommentService {

}
