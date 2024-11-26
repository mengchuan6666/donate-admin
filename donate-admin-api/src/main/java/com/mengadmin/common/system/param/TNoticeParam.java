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
 * 公告查询参数
 *
 * @author EleAdmin
 * @since 2021-08-29 20:35:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(description = "公告查询参数")
public class TNoticeParam extends BaseParam {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @QueryField(type = QueryType.EQ)
    private Integer id;

    @ApiModelProperty("公告名称")
    private String name;

    @ApiModelProperty("状态")
    private String status;


}
