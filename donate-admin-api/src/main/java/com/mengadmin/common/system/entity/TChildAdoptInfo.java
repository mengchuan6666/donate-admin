package com.mengadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 领养信息记录
 * Created by 创建人 on 2024-02-07 20:31:41.
 */
@Data
@ApiModel(description = "领养信息记录")
@TableName("t_child_adopt_info")
public class TChildAdoptInfo {
    @ApiModelProperty("主键ID")
    private Integer id;
    @ApiModelProperty("领养人名称")
    private String adoptName;
    @ApiModelProperty("领养儿童名称")
    @TableField(exist = false)
    private String adoptChildName;
    @ApiModelProperty("领养人性别")
    private String adoptSex;
    @ApiModelProperty("性别名称")
    @TableField(exist = false)
    private String adoptSexName;
    @ApiModelProperty("领养人手机号")
    private String adoptPhone;
    @ApiModelProperty("领养人年龄")
    private Integer adoptAge;
    @ApiModelProperty("领养人地址")
    private String adoptAddress;
    @ApiModelProperty("婚姻 0-未婚 1-已婚")
    private String marriage;
    @TableField(exist = false)
    private String marriageName;
    @ApiModelProperty("领养孩子的id")
    private Integer adoptChildId;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("审核状态 0-不通过 1-通过")
    private String auditStatus;
    @TableField(exist = false)
    private String auditStatusName;
    @ApiModelProperty("审核时间")
    private String auditTime;
    @ApiModelProperty("审核人")
    private String auditUser;
    @ApiModelProperty("创建人id")
    private Integer createUserId;

}

