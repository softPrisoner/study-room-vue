package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.Order;
import com.ruoyi.studyroom.domain.vo.OrderVo;
import com.ruoyi.studyroom.domain.bo.OrderBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 订单Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface IOrderService {

    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    OrderVo queryById(Long id);

    /**
     * 查询订单列表
     *
     * @param order 订单
     * @return 订单集合
     */
    TableDataInfo<OrderVo> queryPageList(OrderBo bo, PageQuery pageQuery);

    /**
     * 查询订单列表
     *
     * @param order 订单
     * @return 订单集合
     */
    List<OrderVo> queryList(OrderBo bo);

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 结果
     */
    Boolean insertByBo(OrderBo bo);

    /**
     * 修改订单
     *
     * @param order 订单
     * @return 结果
     */
    Boolean updateByBo(OrderBo bo);

    /**
     * 校验并批量删除订单信息
     *
     * @param ids 需要删除的订单主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 更新订单状态为已消费，同步更新学习记录排行榜
     * @param orderBo
     * @return
     */
    Boolean updateByBoAndRank(OrderBo orderBo);
}
