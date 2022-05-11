package com.ruoyi.studyroom.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 会员业务对象 sr_user
 *
 * @author P_Peaceful
 * @date 2022-05-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("会员业务对象")
public class UserBo extends BaseEntity {

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID", required = true)
    @NotNull(message = "会员ID不能为空", groups = { EditGroup.class })
    private Long userId;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid", required = true)
    @NotBlank(message = "微信openid不能为空", groups = { AddGroup.class, EditGroup.class })
    private String openid;

    /**
     * 微信昵称
     */
    @ApiModelProperty(value = "微信昵称", required = true)
    @NotBlank(message = "微信昵称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String nickname;

    /**
     * 微信头像
     */
    @ApiModelProperty(value = "微信头像", required = true)
    @NotBlank(message = "微信头像不能为空", groups = { AddGroup.class, EditGroup.class })
    private String avatarUrl;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别", required = true)
    @NotNull(message = "性别不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer sex;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank(message = "手机号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String phone;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;


}
