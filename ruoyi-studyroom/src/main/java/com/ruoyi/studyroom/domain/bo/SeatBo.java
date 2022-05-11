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
 * 座位业务对象 sr_seat
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("座位业务对象")
public class SeatBo extends BaseEntity {

    /**
     * 座位ID
     */
    @ApiModelProperty(value = "座位ID", required = true)
    @NotNull(message = "座位ID不能为空", groups = { EditGroup.class })
    private Long seatId;

    /**
     * 座位号
     */
    @ApiModelProperty(value = "座位号", required = true)
    @NotNull(message = "座位号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer seatNum;

    /**
     * 座位区域
     */
    @ApiModelProperty(value = "座位区域", required = true)
    @NotNull(message = "座位区域不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer seatArea;

    /**
     * 区域名字
     */
    @ApiModelProperty(value = "区域名字", required = true)
    @NotBlank(message = "区域名字不能为空", groups = { AddGroup.class, EditGroup.class })
    private String areaName;

    private Long roomId;

}
