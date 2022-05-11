package com.ruoyi.studyroom.domain.bo;

import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.*;

import java.util.Date;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 座位-记录业务对象 sr_seat_record
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("座位-记录业务对象")
public class SeatRecordBo extends BaseEntity {

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

    /**
     * 会员ID
     */
    @ApiModelProperty(value = "会员ID", required = true)
    @NotNull(message = "会员ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", required = true)
    @NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", required = true)
    @NotNull(message = "结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;


}
