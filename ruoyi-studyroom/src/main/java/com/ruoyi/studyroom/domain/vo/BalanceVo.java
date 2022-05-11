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
 * 余额
视图对象 sr_balance
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@ApiModel("余额视图对象")
@ExcelIgnoreUnannotated
public class BalanceVo {

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
     * 余额
     */
    @ExcelProperty(value = "余额")
    @ApiModelProperty("余额")
    private Long balance;


}
