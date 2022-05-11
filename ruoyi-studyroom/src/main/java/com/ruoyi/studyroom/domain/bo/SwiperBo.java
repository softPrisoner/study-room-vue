package com.ruoyi.studyroom.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 轮播图业务对象 sr_swiper
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("轮播图业务对象")
public class SwiperBo extends BaseEntity {

    /**
     * 对象存储主键
     */
    @ApiModelProperty(value = "对象存储主键", required = true)
    @NotNull(message = "对象存储主键不能为空", groups = { EditGroup.class })
    private Long ossId;

    /**
     * 文件名
     */
    @ApiModelProperty(value = "文件名", required = true)
    @NotBlank(message = "文件名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileName;

    /**
     * 原名
     */
    @ApiModelProperty(value = "原名", required = true)
    @NotBlank(message = "原名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String originalName;

    /**
     * 文件后缀名
     */
    @ApiModelProperty(value = "文件后缀名", required = true)
    @NotBlank(message = "文件后缀名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileSuffix;

    /**
     * URL地址
     */
    @ApiModelProperty(value = "URL地址", required = true)
    @NotBlank(message = "URL地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String url;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 服务商
     */
    @ApiModelProperty(value = "服务商", required = true)
    @NotBlank(message = "服务商不能为空", groups = { AddGroup.class, EditGroup.class })
    private String service;


}
