package com.ruoyi.studyroom.domain.bo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 优惠卡业务对象 sr_card
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("优惠卡业务对象")
public class CardBo extends BaseEntity {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    @NotNull(message = "主键ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 优惠卡
     */
    @ApiModelProperty(value = "优惠卡", required = true)
    @NotBlank(message = "优惠卡不能为空", groups = {AddGroup.class, EditGroup.class})
    private String cardTitle;

    /**
     * 价格
     */
    @ApiModelProperty(value = "价格", required = true)
    @NotNull(message = "价格不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal price;

    /**
     * 可用区域
     */
    @ApiModelProperty(value = "可用区域", required = true)
    @NotNull(message = "可用区域不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer usable;

    /**
     * 次数(天)
     */
    @ApiModelProperty(value = "次数(天)", required = true)
    @NotNull(message = "次数(天)不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long frequency;

    /**
     * 有效期(天)
     */
    @ApiModelProperty(value = "有效期(天)", required = true)
    @NotNull(message = "有效期(天)不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long term;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer status;


}
