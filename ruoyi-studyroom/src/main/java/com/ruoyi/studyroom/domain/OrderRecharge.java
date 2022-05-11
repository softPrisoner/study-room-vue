package com.ruoyi.studyroom.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充值订单对象 sr_order_recharge
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@TableName("sr_order_recharge")
public class OrderRecharge extends BaseEntity {

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
     * 商品描述
     */
    private String description;
    /**
     * 支付金额
     */
    private Long amountTotal;
    /**
     * 充值额度
     */
    private Long rechargeTotal;
    /**
     * 付款状态
     */
    private Integer payStatus;

}
