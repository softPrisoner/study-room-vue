package com.ruoyi.studyroom.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import java.util.Date;



/**
 * 座位视图对象 sr_seat
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@ApiModel("座位视图对象")
@ExcelIgnoreUnannotated
public class SeatVo {

    private static final long serialVersionUID = 1L;

    /**
     * 座位ID
     */
    @ExcelProperty(value = "座位ID")
    @ApiModelProperty("座位ID")
    private Long seatId;

    /**
     * 座位号
     */
    @ExcelProperty(value = "座位号")
    @ApiModelProperty("座位号")
    private Integer seatNum;

    /**
     * 座位区域
     */
    @ExcelProperty(value = "座位区域", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "area_status")
    @ApiModelProperty("座位区域")
    private Integer seatArea;

    /**
     * 区域名字
     */
    @ExcelProperty(value = "区域名字")
    @ApiModelProperty("区域名字")
    private String areaName;

    @ExcelProperty(value = "自习室ID")
    @ApiModelProperty("自习室ID")
    private Long roomId;

    @ApiModelProperty("座位是否可预定")
    private Boolean status;

}
