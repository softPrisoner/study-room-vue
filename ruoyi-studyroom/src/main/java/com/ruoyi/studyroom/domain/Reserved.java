package com.ruoyi.studyroom.domain;

import com.ruoyi.studyroom.domain.vo.SeatVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @ProjectName: study-room-vue
 * @Author: WenZhengcheng
 * @Date: 2022-5-10 上午 12:37
 * @Desc:
 */
@Data
@Builder
@ApiModel("座位预约情况视图")
public class Reserved {


    @ApiModelProperty("所有舒适区座位列表")
    private List<SeatVo> seatVip;

    @ApiModelProperty("所有经济区座位列表")
    private List<SeatVo> seatCom;

    @ApiModelProperty("已预约座位数")
    private Integer reservedNum;


    @ApiModelProperty("未预约座位数")
    private Integer noReservedNum;


}
