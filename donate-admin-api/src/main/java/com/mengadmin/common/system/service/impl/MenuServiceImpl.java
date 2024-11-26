package com.mengadmin.common.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengadmin.common.system.entity.Menu;
import com.mengadmin.common.system.mapper.MenuMapper;
import com.mengadmin.common.system.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * 菜单Service实现
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
