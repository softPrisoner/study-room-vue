package com.ruoyi.studyroom.domain;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单对象 sr_order
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@TableName("sr_order")
public class Order extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 会员ID
     */
    private Long userId;
    /**
     * 自习室ID
     */
    private Long roomId;
    /**
     * 座位ID
     */
    private Long seatId;
    /**
     * 商品描述
     */
    private String description;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 付款状态
     */
    private Integer payStatus;

    /**
     * 小时数
     */
    private Double hours;
}
