package com.mengadmin.common.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mengadmin.common.system.entity.Role;
import com.mengadmin.common.system.entity.UserRole;

import java.util.List;

/**
 * 用户角色Service
 */
public interface UserRoleService extends IService<UserRole> {

    /**
     * 批量添加用户角色
     *
     * @param userId  用户id
     * @param roleIds 角色id集合
     * @return int
     */
    int saveBatch(Integer userId, List<Integer> roleIds);

    /**
     * 根据用户id查询角色
     *
     * @param userId 用户id
     * @return List<Role>
     */
    List<Role> listByUserId(Integer userId);

    /**
     * 批量根据用户id查询角色
     *
     * @param userIds 用户id集合
     * @return List<RoleResult>
     */
    List<Role> listByUserIds(List<Integer> userIds);

}
