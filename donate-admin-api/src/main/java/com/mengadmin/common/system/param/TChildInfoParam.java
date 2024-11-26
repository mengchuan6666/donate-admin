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
 * 儿童信息查询参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "儿童信息查询参数")
public class TChildInfoParam extends BaseParam {

    @ApiModelProperty("id")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty("儿童名称")
    private String name;

    @ApiModelProperty("儿童性别，1-男，2-女")
    private Integer sex;

}
