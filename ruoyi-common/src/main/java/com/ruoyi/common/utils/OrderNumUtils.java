package com.ruoyi.common.utils;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.IdUtil;

import java.util.Date;

/**
 * @ProjectName: study-room-vue
 * @Author: WenZhengcheng
 * @Date: 2022-5-10 下午 11:45
 * @Desc:
 */

public class OrderNumUtils {
    /**
     * 构建订单唯一编号 年月日+雪花ID
     * @return
     */
    public static String getOrderNum(){
        return new DateTime(new Date()).toString("yyyyMMdd") + IdUtil.getSnowflake(1, 15).nextId();
    }
}
