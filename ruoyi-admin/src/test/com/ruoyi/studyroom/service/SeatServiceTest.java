package com.ruoyi.studyroom.service;

import cn.hutool.core.collection.ListUtil;
import com.ruoyi.RuoYiApplication;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.studyroom.domain.bo.RecordBo;
import com.ruoyi.studyroom.domain.bo.RoomSeatBo;
import com.ruoyi.studyroom.domain.bo.SeatBo;
import com.ruoyi.studyroom.domain.vo.RecordVo;
import com.ruoyi.studyroom.domain.vo.SeatVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RSortedSet;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

/**
 * @ProjectName: study-room-vue
 * @Author: WenZhengcheng
 * @Date: 2022-5-9 下午 05:51
 * @Desc:
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RuoYiApplication.class)
public class SeatServiceTest {

    @Autowired
    private ISeatService service;
    @Autowired
    private IRoomSeatService roomSeatService;
    @Autowired
    private IRecordService recordService;

    @Test
    public void insertSeat() {

        for (int i = 1; i <= 20; i++) {
            SeatBo bo = new SeatBo();
            bo.setRoomId(1523594314784546818L);
            bo.setSeatArea(0);
            bo.setAreaName("W区舒适区");
            bo.setSeatNum(i);
            service.insertByBo(bo);
        }
    }

    @Test
    public void insertRoomSeat() {
        List<SeatVo> list = service.queryList(new SeatBo());
        list.forEach(item -> {
            RoomSeatBo bo = new RoomSeatBo();
            bo.setSeatId(item.getSeatId());
            bo.setRoomId(item.getRoomId());
            roomSeatService.insertByBo(bo);
        });

    }

    @Test
    public void sort() {
        List<RecordVo> list = recordService.queryDescList();
        RedisUtils.setCacheList(Constants.RECORD_RANK, list);
        RedisUtils.expire(Constants.RECORD_RANK,60L);

    }
}
