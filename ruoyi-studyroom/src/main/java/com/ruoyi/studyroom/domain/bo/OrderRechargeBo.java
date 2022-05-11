package com.ruoyi.studyroom.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 充值订单业务对象 sr_order_recharge
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("充值订单业务对象")
public class OrderRechargeBo extends BaseEntity {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", required = true)
    @NotNull(message = "订单编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderId;

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID", required = true)
    @NotNull(message = "会员ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 商品描述
     */
    @ApiModelProperty(value = "商品描述", required = true)
    @NotBlank(message = "商品描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 支付金额
     */
    @ApiModelProperty(value = "支付金额", required = true)
    @NotNull(message = "支付金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amountTotal;

    /**
     * 充值额度
     */
    @ApiModelProperty(value = "充值额度", required = true)
    @NotNull(message = "充值额度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long rechargeTotal;

    /**
     * 付款状态
     */
    @ApiModelProperty(value = "付款状态", required = true)
    @NotNull(message = "付款状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer payStatus;


}
