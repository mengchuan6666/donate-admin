package com.mengadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 新闻信息
 */
@Data
@ApiModel(description = "新闻信息")
@TableName("t_news")
public class TNews implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("缩略图")
    private String image;

    @ApiModelProperty("新闻名称")
    private String name;

    @ApiModelProperty("新闻简介")
    private String description;

    @ApiModelProperty("新闻详细")
    private String content;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("创建人")
    private String createUser;

}
