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
 * 自习室-座位关联业务对象 sr_room_seat
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("自习室-座位关联业务对象")
public class RoomSeatBo extends BaseEntity {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID", required = true)
    @NotNull(message = "主键ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 自习室ID
     */
    @ApiModelProperty(value = "自习室ID", required = true)
    @NotNull(message = "自习室ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long roomId;

    /**
     * 座位ID
     */
    @ApiModelProperty(value = "座位ID", required = true)
    @NotNull(message = "座位ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long seatId;


}
