package com.mengadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

/**
 * 公告
 *
 * @author EleAdmin
 * @since 2018-12-24 16:10:01
 */
@Data
@ApiModel(description = "公告")
@TableName("t_notice")
public class TNotice implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("公告名称")
    private String name;

    @ApiModelProperty("公告内容")
    private String neirong;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("创建人")
    private String createUser;

    @ApiModelProperty("状态")
    private String status;

}
