package com.ruoyi.studyroom.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员对象 sr_user
 *
 * @author P_Peaceful
 * @date 2022-05-09
 */
@Data
@TableName("sr_user")
public class User extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 会员ID
     */
    @TableId(value = "user_id")
    private Long userId;
    /**
     * 微信openid
     */
    private String openid;
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 微信头像
     */
    private String avatarUrl;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 状态
     */
    private Integer status;

}
