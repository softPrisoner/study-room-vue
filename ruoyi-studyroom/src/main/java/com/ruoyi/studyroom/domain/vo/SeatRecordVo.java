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
 * 座位-记录视图对象 sr_seat_record
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@ApiModel("座位-记录视图对象")
@ExcelIgnoreUnannotated
public class SeatRecordVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    @ApiModelProperty("主键ID")
    private Long id;

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
     * 会员ID
     */
    @ExcelProperty(value = "会员ID")
    @ApiModelProperty("会员ID")
    private Long userId;

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


}
