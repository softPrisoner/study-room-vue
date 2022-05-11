package com.ruoyi.studyroom.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.math.BigDecimal;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 购卡管理业务对象 sr_order_card
 *
 * @author P_Peaceful
 * @date 2022-05-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("购卡管理业务对象")
public class OrderCardBo extends BaseEntity {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 优惠卡ID
     */
    @ApiModelProperty(value = "优惠卡ID", required = true)
    @NotNull(message = "优惠卡ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long cardId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号", required = true)
    @NotBlank(message = "订单编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderId;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", required = true)
    @NotBlank(message = "描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 账单金额
     */
    @ApiModelProperty(value = "账单金额", required = true)
    @NotNull(message = "账单金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amountTotal;

    /**
     * 付款状态
     */
    @ApiModelProperty(value = "付款状态", required = true)
    @NotNull(message = "付款状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer payStatus;


}
