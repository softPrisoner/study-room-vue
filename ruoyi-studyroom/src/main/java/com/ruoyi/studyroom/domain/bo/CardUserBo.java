package com.ruoyi.studyroom.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 优惠卡-会员关联业务对象 sr_card_user
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("优惠卡-会员关联业务对象")
public class CardUserBo extends BaseEntity {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID", required = true)
    @NotNull(message = "会员ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 卡片ID
     */
    @ApiModelProperty(value = "卡片ID", required = true)
    @NotNull(message = "卡片ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long cardId;



    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间", required = true)
    @NotNull(message = "过期时间不能为空", groups = { AddGroup.class, EditGroup.class })
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expiryTime;
}
