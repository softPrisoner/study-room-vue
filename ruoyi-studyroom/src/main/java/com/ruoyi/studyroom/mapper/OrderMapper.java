package com.ruoyi.studyroom.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.studyroom.domain.Order;
import com.ruoyi.studyroom.domain.vo.OrderVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

/**
 * 订单Mapper接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface OrderMapper extends BaseMapperPlus<OrderMapper, Order, OrderVo> {

    /**
     * 联表查询
     * @param page
     * @param lqw
     * @return
     */
    Page<OrderVo> selectVoAndSeatPage(@Param("page") Page<Order> page,@Param(Constants.WRAPPER) Wrapper<Order> lqw);
}
