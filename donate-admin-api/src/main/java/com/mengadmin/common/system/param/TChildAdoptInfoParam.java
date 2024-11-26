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
 * 领养人信息查询参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "领养人信息查询参数")
public class TChildAdoptInfoParam extends BaseParam {

    @ApiModelProperty("id")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty("领养人名称")
    private String adoptName;

    @ApiModelProperty("领养人性别")
    private String adoptSex;

    @ApiModelProperty("领养人手机号")
    private String adoptPhone;

    @ApiModelProperty("领养人地址")
    private String adoptAddress;

    @ApiModelProperty("创建人id")
    private Integer createUserId;

    @ApiModelProperty("审核状态 0-不通过 1-通过")
    private String auditStatus;

}
