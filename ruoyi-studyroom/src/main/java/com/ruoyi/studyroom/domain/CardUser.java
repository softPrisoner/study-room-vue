package com.ruoyi.studyroom.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 优惠卡-会员关联对象 sr_card_user
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@TableName("sr_card_user")
public class CardUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 会员ID
     */
    private Long userId;
    /**
     * 卡片ID
     */
    private Long cardId;

}
