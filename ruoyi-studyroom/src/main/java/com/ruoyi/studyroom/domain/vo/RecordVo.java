package com.ruoyi.studyroom.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.studyroom.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 学习记录视图对象 sr_record
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data

@ApiModel("学习记录视图对象")
@ExcelIgnoreUnannotated
public class RecordVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ExcelProperty(value = "主键ID")
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 会员ID
     */
    @ExcelProperty(value = "会员ID")
    @ApiModelProperty("会员ID")
    private Long userId;

    /**
     * 小时数
     */
    @ExcelProperty(value = "小时数")
    @ApiModelProperty("小时数")
    private Double hours;

    private User user;

}
