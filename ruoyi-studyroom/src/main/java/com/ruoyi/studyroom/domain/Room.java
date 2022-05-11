package com.ruoyi.studyroom.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 自习室对象 sr_room
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Data
@TableName("sr_room")
public class Room extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 自习室ID
     */
    @TableId(value = "room_id")
    private Long roomId;
    /**
     * 自习室名字
     */
    private String roomName;
    /**
     * 自习室地址
     */
    private String roomAddr;
    /**
     * 自习室联系电话
     */
    private String phone;

}
