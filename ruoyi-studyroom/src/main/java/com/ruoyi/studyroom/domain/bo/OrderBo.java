package com.ruoyi.studyroom.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 订单业务对象 sr_order
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("订单业务对象")
public class OrderBo extends BaseEntity {

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
     * 自习室ID
     */
    @ApiModelProperty(value = "自习室ID", required = true)
    @NotNull(message = "自习室ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long roomId;

    /**
     * 座位ID
     */
    @ApiModelProperty(value = "座位ID", required = true)
    @NotNull(message = "座位ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long seatId;

    /**
     * 商品描述
     */
    @ApiModelProperty(value = "商品描述", required = true)
    @NotBlank(message = "商品描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = true)
    @NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = true)
    @NotNull(message = "结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 付款状态
     */
    @ApiModelProperty(value = "付款状态", required = true)
    @NotNull(message = "付款状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer payStatus;

    @ApiModelProperty("支付方式 0余额 1时卡")
    private Integer flag;

    @ApiModelProperty("总计余额")
    private Integer total;

    @ApiModelProperty("小时数")
    private Double hours;
}
