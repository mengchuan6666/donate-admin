package com.mengadmin.common.system.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mengadmin.common.system.entity.User;
import com.mengadmin.common.system.param.UserParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 分页查询
     *
     * @param page  分页对象
     * @param param 查询参数
     * @return List<User>
     */
    List<User> selectPageRel(@Param("page") IPage<User> page,
                             @Param("param") UserParam param);

    /**
     * 查询全部
     *
     * @param param 查询参数
     * @return List<User>
     */
    List<User> selectListRel(@Param("param") UserParam param);

    /**
     * 根据账号查询
     *
     * @param username 账号
     * @return User
     */
    @InterceptorIgnore(tenantLine = "true")
    User selectByUsername(@Param("username") String username);

    /**
     * 根据账号查询
     *
     * @param username 账号
     * @return User
     */
    @InterceptorIgnore(tenantLine = "true")
    User selectByUsernameType(@Param("username") String username,@Param("type") String type);

}
