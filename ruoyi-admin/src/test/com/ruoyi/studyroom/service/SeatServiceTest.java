package com.ruoyi.studyroom.service;

import cn.dev33.satoken.stp.StpUtil;
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


    @Test
    public void getToken(){
        List<String> keys = StpUtil.searchTokenValue("", -1, -1);
        System.out.println(keys);
        for (String key : keys) {
            String token = key.replace(Constants.LOGIN_TOKEN_KEY, "");
            // 如果已经过期则踢下线
            System.out.println(StpUtil.stpLogic.getTokenActivityTimeoutByToken(token));
            if (StpUtil.stpLogic.getTokenActivityTimeoutByToken(token) < 0) {
                continue;
            }
            Object object = RedisUtils.getCacheObject(Constants.ONLINE_TOKEN_KEY + token);
            System.out.println(object);
        }
    }
}
