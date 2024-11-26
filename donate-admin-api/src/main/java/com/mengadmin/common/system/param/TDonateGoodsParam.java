package com.mengadmin.common.system.param;

import com.mengadmin.common.core.annotation.QueryField;
import com.mengadmin.common.core.annotation.QueryType;
import com.mengadmin.common.core.web.BaseParam;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 捐赠物资表查询参数
 *
 * @author EleAdmin
 * @since 2024-02-12 16:44:24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "TDonateGoodsParam对象", description = "捐赠物资表查询参数")
public class TDonateGoodsParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty(value = "捐赠名称")
    private String donateName;

    @ApiModelProperty(value = "捐赠类型")
    private String donateType;

    @ApiModelProperty(value = "捐赠数量")
    @QueryField(type = QueryType.EQ)
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

    @ApiModelProperty(value = "操作人")
    private String createUser;

    @ApiModelProperty(value = "审核状态 0-不通过 1-通过")
    private String auditStatus;

    @ApiModelProperty(value = "审核时间")
    private String auditTime;

    @ApiModelProperty(value = "审核人")
    private String auditUser;

}
