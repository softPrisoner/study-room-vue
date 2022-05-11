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
 * 会员视图对象 sr_user
 *
 * @author P_Peaceful
 * @date 2022-05-09
 */
@Data
@ApiModel("会员视图对象")
@ExcelIgnoreUnannotated
public class UserVo {

    private static final long serialVersionUID = 1L;

    /**
     * 会员ID
     */
    @ExcelProperty(value = "会员ID")
    @ApiModelProperty("会员ID")
    private Long userId;

    /**
     * 微信openid
     */
    @ExcelProperty(value = "微信openid")
    @ApiModelProperty("微信openid")
    private String openid;

    /**
     * 微信昵称
     */
    @ExcelProperty(value = "微信昵称")
    @ApiModelProperty("微信昵称")
    private String nickname;

    /**
     * 微信头像
     */
    @ExcelProperty(value = "微信头像")
    @ApiModelProperty("微信头像")
    private String avatarUrl;

    /**
     * 性别
     */
    @ExcelProperty(value = "性别", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    @ApiModelProperty("性别")
    private Integer sex;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    @ApiModelProperty("手机号")
    private String phone;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "common_status")
    @ApiModelProperty("状态")
    private Integer status;


}
