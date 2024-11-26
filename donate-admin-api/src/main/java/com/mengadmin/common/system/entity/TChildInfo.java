package com.mengadmin.common.system.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 儿童信息表DTO模型
 * Created by 创建人 on 2024-02-07 20:31:41.
 */
@Data
@ApiModel(description = "儿童信息")
@TableName("t_child_info")
public class TChildInfo {
    @ApiModelProperty("主键ID")
    private Integer id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("性别名称")
    @TableField(exist = false)
    private String sexName;
    @ApiModelProperty("图片")
    private String image;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("性情")
    private String temperament;
    @ApiModelProperty("爱好")
    private String hobby;
    @ApiModelProperty("发布日期")
    private String publicDate;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("创建人")
    private String createUser;
    @ApiModelProperty("是否领养, 0否, 1是, 2审批中")
    private Integer isAdopt;
    @ApiModelProperty("是否领养, 0否, 1是, 2审批中")
    @TableField(exist = false)
    private String isAdoptName;

}

