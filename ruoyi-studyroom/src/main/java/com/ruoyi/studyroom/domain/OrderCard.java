package com.ruoyi.studyroom.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 购卡管理对象 sr_order_card
 *
 * @author P_Peaceful
 * @date 2022-05-10
 */
@Data
@TableName("sr_order_card")
public class OrderCard extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 优惠卡ID
     */
    private Long cardId;
    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 描述
     */
    private String description;
    /**
     * 账单金额
     */
    private BigDecimal amountTotal;
    /**
     * 付款状态
     */
    private Integer payStatus;

}
