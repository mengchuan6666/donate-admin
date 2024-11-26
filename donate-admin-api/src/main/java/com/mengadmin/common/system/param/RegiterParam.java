package com.mengadmin.common.system.param;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 注册参数
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "注册参数")
public class RegiterParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("用户昵称")
    private String nickname;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("用户类型")
    private String type;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("确认密码")
    private String passtwo;



}
