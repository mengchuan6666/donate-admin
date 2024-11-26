package com.mengadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 捐赠物资表
 *
 * @author EleAdmin
 * @since 2024-02-12 16:44:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "TDonateGoods对象", description = "捐赠物资表")
public class TDonateGoods implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "捐赠名称")
    private String donateName;

    @ApiModelProperty(value = "捐赠类型")
    private String donateType;

    @ApiModelProperty(value = "捐赠类型名称")
    @TableField(exist = false)
    private String donateTypeName;

    @ApiModelProperty(value = "捐赠数量")
    private Integer donateNumber;

    @ApiModelProperty(value = "捐赠账号")
    private String donateAccount;

    @ApiModelProperty(value = "捐赠人名称")
    private String donatePersonName;

    @ApiModelProperty(value = "捐赠人手机")
    private String donatePersonPhone;

    @ApiModelProperty(value = "捐赠物品描述")
    private String donateGoodsDesc;

    @ApiModelProperty(value = "捐赠时间")
    private String donateTime;

    @ApiModelProperty(value = "操作时间")
    private String createTime;

    @ApiModelProperty(value = "操作人")
    private String createUser;

    @ApiModelProperty(value = "审核状态 0-不通过 1-通过")
    private String auditStatus;
    @TableField(exist = false)
    private String auditStatusName;

    @ApiModelProperty(value = "审核回复")
    private String auditReply;

    @ApiModelProperty(value = "审核时间")
    private String auditTime;

    @ApiModelProperty(value = "审核人")
    private String auditUser;

    @ApiModelProperty("创建人id")
    private Integer createUserId;

}
