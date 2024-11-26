package com.mengadmin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengadmin.common.system.entity.Role;
import com.mengadmin.common.system.mapper.RoleMapper;
import com.mengadmin.common.system.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * 角色服务实现类
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
