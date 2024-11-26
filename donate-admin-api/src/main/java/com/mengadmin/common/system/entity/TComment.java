package com.mengadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 评论信息
 */
@Data
@ApiModel(description = "评论信息")
@TableName("t_comment")
public class TComment implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("公告ID")
    private String sid;

    @ApiModelProperty("评论")
    private String comment;

    @ApiModelProperty("公告名称")
    private String name;

    @ApiModelProperty("创建时间")
    private String createTime;

    @ApiModelProperty("创建人")
    private String createUser;

}
