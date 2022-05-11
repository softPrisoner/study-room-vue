package com.ruoyi.studyroom.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 轮播图对象 sr_swiper
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@TableName("sr_swiper")
public class Swiper extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 对象存储主键
     */
    @TableId(value = "oss_id")
    private Long ossId;
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 原名
     */
    private String originalName;
    /**
     * 文件后缀名
     */
    private String fileSuffix;
    /**
     * URL地址
     */
    private String url;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 服务商
     */
    private String service;

}
