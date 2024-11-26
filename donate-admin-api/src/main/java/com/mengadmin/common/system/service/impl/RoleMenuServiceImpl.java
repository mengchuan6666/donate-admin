package com.mengadmin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengadmin.common.system.entity.Menu;
import com.mengadmin.common.system.entity.RoleMenu;
import com.mengadmin.common.system.mapper.RoleMenuMapper;
import com.mengadmin.common.system.service.RoleMenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色菜单Service实现
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Override
    public List<Menu> listMenuByUserId(Integer userId, Integer menuType) {
        return baseMapper.listMenuByUserId(userId, menuType);
    }

    @Override
    public List<Menu> listMenuByRoleIds(List<Integer> roleIds, Integer menuType) {
        return baseMapper.listMenuByRoleIds(roleIds, menuType);
    }

}
