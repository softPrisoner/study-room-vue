package com.ruoyi.studyroom.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 座位对象 sr_seat
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@TableName("sr_seat")
public class Seat extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 座位ID
     */
    @TableId(value = "seat_id")
    private Long seatId;
    /**
     * 座位号
     */
    private Integer seatNum;
    /**
     * 座位区域
     */
    private Integer seatArea;
    /**
     * 区域名字
     */
    private String areaName;

    private Long roomId;
}
