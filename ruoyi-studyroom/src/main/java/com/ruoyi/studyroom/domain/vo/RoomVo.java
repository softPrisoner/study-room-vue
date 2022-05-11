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
 * 自习室视图对象 sr_room
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@ApiModel("自习室视图对象")
@ExcelIgnoreUnannotated
public class RoomVo {

    private static final long serialVersionUID = 1L;

    /**
     * 自习室ID
     */
    @ExcelProperty(value = "自习室ID")
    @ApiModelProperty("自习室ID")
    private Long roomId;

    /**
     * 自习室名字
     */
    @ExcelProperty(value = "自习室名字")
    @ApiModelProperty("自习室名字")
    private String roomName;

    /**
     * 自习室地址
     */
    @ExcelProperty(value = "自习室地址")
    @ApiModelProperty("自习室地址")
    private String roomAddr;

    /**
     * 自习室联系电话
     */
    @ExcelProperty(value = "自习室联系电话")
    @ApiModelProperty("自习室联系电话")
    private String phone;


}
