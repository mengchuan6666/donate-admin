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
 * 新闻信息查询参数
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "新闻信息查询参数")
public class TNewsParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty("新闻名称")
    private String name;


}
