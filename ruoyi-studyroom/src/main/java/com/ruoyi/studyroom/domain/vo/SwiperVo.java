package com.ruoyi.studyroom.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 轮播图视图对象 sr_swiper
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@ApiModel("轮播图视图对象")
@ExcelIgnoreUnannotated
public class SwiperVo {

    private static final long serialVersionUID = 1L;

    /**
     * 对象存储主键
     */
    @ExcelProperty(value = "对象存储主键")
    @ApiModelProperty("对象存储主键")
    private Long ossId;

    /**
     * 文件名
     */
    @ExcelProperty(value = "文件名")
    @ApiModelProperty("文件名")
    private String fileName;

    /**
     * 原名
     */
    @ExcelProperty(value = "原名")
    @ApiModelProperty("原名")
    private String originalName;

    /**
     * 文件后缀名
     */
    @ExcelProperty(value = "文件后缀名")
    @ApiModelProperty("文件后缀名")
    private String fileSuffix;

    /**
     * URL地址
     */
    @ExcelProperty(value = "URL地址")
    @ApiModelProperty("URL地址")
    private String url;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "common_status")
    @ApiModelProperty("状态")
    private Integer status;

    /**
     * 服务商
     */
    @ExcelProperty(value = "服务商")
    @ApiModelProperty("服务商")
    private String service;


}
