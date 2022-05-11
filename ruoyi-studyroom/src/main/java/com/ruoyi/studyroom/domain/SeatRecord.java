package com.ruoyi.studyroom.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 座位-记录对象 sr_seat_record
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sr_seat_record")
public class SeatRecord extends BaseEntity {

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
    /**
     * 会员ID
     */
    private Long userId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

}
