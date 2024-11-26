package com.mengadmin.common.system.param;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.mengadmin.common.core.annotation.QueryField;
import com.mengadmin.common.core.annotation.QueryType;
import com.mengadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户查询参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "用户查询参数")
public class UserParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户id")
    @QueryField(type = QueryType.EQ)
    private Integer userId;

    @ApiModelProperty("用户类型")
    @QueryField(type = QueryType.EQ)
    private String type;

    @ApiModelProperty("账号")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("性别(字典)")
    @QueryField(type = QueryType.EQ)
    private String sex;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("学号或者工号")
    private Integer bianhao;

    @ApiModelProperty("专业或者职称")
    private String zhuanye;

    @ApiModelProperty("班级")
    private String banji;

    @ApiModelProperty("学院")
    private String xueyuan;

    @ApiModelProperty("出生日期")
    private String birthday;

    @ApiModelProperty("级别或者入职年份")
    private Integer nianfen;

    @ApiModelProperty("状态, 0正常, 1冻结")
    @QueryField(type = QueryType.EQ)
    private Integer status;

    @ApiModelProperty("是否删除, 0否, 1是")
    @TableLogic
    private Integer deleted;

    @ApiModelProperty("角色id")
    @TableField(exist = false)
    private Integer roleId;

    @ApiModelProperty("机构名称")
    @TableField(exist = false)
    private String organizationName;

    @ApiModelProperty("性别名称")
    @TableField(exist = false)
    private String sexName;

    @ApiModelProperty("搜索关键字")
    @TableField(exist = false)
    private String keywords;

}
