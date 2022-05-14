package com.ruoyi.studyroom.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 订单视图对象 sr_order
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@ApiModel("订单视图对象")
@ExcelIgnoreUnannotated
public class OrderVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 订单编号
     */
    @ExcelProperty(value = "订单编号")
    @ApiModelProperty("订单编号")
    private String orderId;

    /**
     * 会员ID
     */
    @ExcelProperty(value = "会员ID")
    @ApiModelProperty("会员ID")
    private Long userId;

    /**
     * 自习室ID
     */
    @ExcelProperty(value = "自习室ID")
    @ApiModelProperty("自习室ID")
    private Long roomId;

    /**
     * 座位ID
     */
    @ExcelProperty(value = "座位ID")
    @ApiModelProperty("座位ID")
    private Long seatId;

    /**
     * 商品描述
     */
    @ExcelProperty(value = "商品描述")
    @ApiModelProperty("商品描述")
    private String description;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    @ApiModelProperty("开始时间")
    private Date startTime;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    @ApiModelProperty("结束时间")
    private Date endTime;

    /**
     * 付款状态
     */
    @ExcelProperty(value = "付款状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "pay_status")
    @ApiModelProperty("付款状态")
    private Integer payStatus;


    @ExcelProperty(value = "小时数")
    @ApiModelProperty("小时数")
    private Double hours;

    @ApiModelProperty("支付方式 0余额 1时卡")
    private Integer payMethod;

    @ApiModelProperty("总计余额")
    private Integer total;


    /**
     * 自习室名字
     */
    private String roomName;

    /**
     * 座位号
     */
    private Integer seatNum;
    /**
     * 座位区域
     */
    private Integer seatArea;
    /**
     * 区域名字
     */
    private String areaName;
}
