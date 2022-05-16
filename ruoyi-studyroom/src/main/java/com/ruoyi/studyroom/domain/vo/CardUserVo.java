package com.ruoyi.studyroom.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.annotation.ExcelDictFormat;
import com.ruoyi.common.convert.ExcelDictConvert;
import com.ruoyi.studyroom.domain.Card;
import com.ruoyi.studyroom.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;



/**
 * 优惠卡-会员关联视图对象 sr_card_user
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@ApiModel("优惠卡-会员关联视图对象")
@ExcelIgnoreUnannotated
public class CardUserVo {

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
     * 卡片ID
     */
    @ExcelProperty(value = "卡片ID")
    @ApiModelProperty("卡片ID")
    private Long cardId;

    private CardVo card;

    @ExcelProperty(value = "过期时间")
    @ApiModelProperty("过期时间")
    private Date expiryTime;

}
