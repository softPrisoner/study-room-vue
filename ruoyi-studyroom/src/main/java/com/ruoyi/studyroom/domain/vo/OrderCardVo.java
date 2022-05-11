package com.ruoyi.studyroom.domain.vo;

import java.math.BigDecimal;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 购卡管理视图对象 sr_order_card
 *
 * @author P_Peaceful
 * @date 2022-05-10
 */
@Data
@ApiModel("购卡管理视图对象")
@ExcelIgnoreUnannotated
public class OrderCardVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    @ApiModelProperty("用户ID")
    private Long userId;

    /**
     * 优惠卡ID
     */
    @ExcelProperty(value = "优惠卡ID")
    @ApiModelProperty("优惠卡ID")
    private Long cardId;

    /**
     * 订单编号
     */
    @ExcelProperty(value = "订单编号")
    @ApiModelProperty("订单编号")
    private String orderId;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    @ApiModelProperty("描述")
    private String description;

    /**
     * 账单金额
     */
    @ExcelProperty(value = "账单金额")
    @ApiModelProperty("账单金额")
    private BigDecimal amountTotal;

    /**
     * 付款状态
     */
    @ExcelProperty(value = "付款状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "pay_status")
    @ApiModelProperty("付款状态")
    private Integer payStatus;


}
