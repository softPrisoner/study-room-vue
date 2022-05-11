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
 * 自习室业务对象 sr_room
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("自习室业务对象")
public class RoomBo extends BaseEntity {

    /**
     * 自习室ID
     */
    @ApiModelProperty(value = "自习室ID", required = true)
    @NotNull(message = "自习室ID不能为空", groups = { EditGroup.class })
    private Long roomId;

    /**
     * 自习室名字
     */
    @ApiModelProperty(value = "自习室名字", required = true)
    @NotBlank(message = "自习室名字不能为空", groups = { AddGroup.class, EditGroup.class })
    private String roomName;

    /**
     * 自习室地址
     */
    @ApiModelProperty(value = "自习室地址", required = true)
    @NotBlank(message = "自习室地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String roomAddr;

    /**
     * 自习室联系电话
     */
    @ApiModelProperty(value = "自习室联系电话", required = true)
    @NotBlank(message = "自习室联系电话不能为空", groups = { AddGroup.class, EditGroup.class })
    private String phone;


}
