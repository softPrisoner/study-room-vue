package com.ruoyi.studyroom.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 自习室-座位关联对象 sr_room_seat
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@TableName("sr_room_seat")
public class RoomSeat extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 自习室ID
     */
    private Long roomId;
    /**
     * 座位ID
     */
    private Long seatId;


}
