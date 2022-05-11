package com.ruoyi.studyroom.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 自习室-座位关联视图对象 sr_room_seat
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@ApiModel("自习室-座位关联视图对象")
@ExcelIgnoreUnannotated
public class RoomSeatVo {

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

    @ExcelProperty(value = "座位")
    @ApiModelProperty("座位信息")
    private SeatVo seat;

}
