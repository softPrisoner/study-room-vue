package com.ruoyi.studyroom.wx.api;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.studyroom.domain.Reserved;
import com.ruoyi.studyroom.domain.bo.RoomBo;
import com.ruoyi.studyroom.domain.bo.SeatBo;
import com.ruoyi.studyroom.domain.bo.SeatRecordBo;
import com.ruoyi.studyroom.domain.bo.SwiperBo;
import com.ruoyi.studyroom.domain.vo.RoomVo;
import com.ruoyi.studyroom.domain.vo.SeatRecordVo;
import com.ruoyi.studyroom.domain.vo.SeatVo;
import com.ruoyi.studyroom.domain.vo.SwiperVo;
import com.ruoyi.studyroom.service.IRoomService;
import com.ruoyi.studyroom.service.ISeatRecordService;
import com.ruoyi.studyroom.service.ISeatService;
import com.ruoyi.studyroom.service.ISwiperService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @ProjectName: study-room-vue
 * @Author: WenZhengcheng
 * @Date: 2022-5-9 下午 08:24
 * @Desc:
 */

@Api(value = "通用资源请求控制器",tags = "通用资源请求")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wx/common/")
public class CommonController {


    private final IRoomService roomService;
    private final ISwiperService swiperService;
    private final ISeatRecordService seatRecordService;
    private final ISeatService seatService;

    @ApiOperation("门店信息")
    @GetMapping("store")
    public R<TableDataInfo<RoomVo>> roomList(RoomBo bo, PageQuery pageQuery){

        return R.ok(roomService.queryPageList(bo,pageQuery));
    }


    @ApiOperation("轮播图列表")
    @GetMapping("swiper")
    public R<List<SwiperVo>> swiper(){
        SwiperBo swiperBo = new SwiperBo();
        swiperBo.setStatus(1);
        return R.ok(swiperService.queryList(swiperBo));
    }



    @ApiOperation("座位详情")
    @PostMapping("/seat")
    public R<Reserved> seat(@RequestBody SeatRecordBo bo){
        List<SeatVo> seatVip = new ArrayList<>();
        List<SeatVo> seatCom = new ArrayList<>();
        //查询在开始结束时间段不可预定的座位列表
        List<SeatRecordVo> list = seatRecordService.queryListByTime(bo);
        SeatBo seatBo = new SeatBo();
        seatBo.setRoomId(bo.getRoomId());
        //根据roomId查询所有座位
        List<SeatVo> seatVos = seatService.queryList(seatBo);
        //根据不可预定座位列表更改seatVos中status字段
        // status：true未预定，false 已预定
        if (!list.isEmpty()){
            seatVos.forEach(item -> {
                for (SeatRecordVo seatRecordVo : list) {
                    if (item.getSeatId().equals(seatRecordVo.getSeatId())){
                        item.setStatus(false);
                        break;
                    }else{
                        item.setStatus(true);
                    }
                }
                boolean b = Constants.SEAT_COM.equals(item.getSeatArea()) ? seatCom.add(item) : seatVip.add(item);
            });
        }else{
            seatVos.forEach(item -> {
                item.setStatus(true);
                boolean b = Constants.SEAT_COM.equals(item.getSeatArea()) ? seatCom.add(item) : seatVip.add(item);
            });
        }
        return R.ok(Reserved.builder()
                .seatCom(seatCom)
                .seatVip(seatVip)
                .reservedNum(list.size())
                .noReservedNum(seatVos.size() - list.size())
                .build());
    }

}
