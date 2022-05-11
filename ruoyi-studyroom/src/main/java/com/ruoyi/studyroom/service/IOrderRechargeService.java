package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.OrderRecharge;
import com.ruoyi.studyroom.domain.vo.OrderRechargeVo;
import com.ruoyi.studyroom.domain.bo.OrderRechargeBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 充值订单Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface IOrderRechargeService {

    /**
     * 查询充值订单
     *
     * @param id 充值订单主键
     * @return 充值订单
     */
    OrderRechargeVo queryById(Long id);

    /**
     * 查询充值订单列表
     *
     * @param orderRecharge 充值订单
     * @return 充值订单集合
     */
    TableDataInfo<OrderRechargeVo> queryPageList(OrderRechargeBo bo, PageQuery pageQuery);

    /**
     * 查询充值订单列表
     *
     * @param orderRecharge 充值订单
     * @return 充值订单集合
     */
    List<OrderRechargeVo> queryList(OrderRechargeBo bo);

    /**
     * 修改充值订单
     *
     * @param orderRecharge 充值订单
     * @return 结果
     */
    Boolean insertByBo(OrderRechargeBo bo);

    /**
     * 修改充值订单
     *
     * @param orderRecharge 充值订单
     * @return 结果
     */
    Boolean updateByBo(OrderRechargeBo bo);

    /**
     * 校验并批量删除充值订单信息
     *
     * @param ids 需要删除的充值订单主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 根据订单ID查询充值订单
     * @param outTradeNo
     * @return
     */
    OrderRechargeVo queryByOrderId(String outTradeNo);

    /**
     * 修改充值订单，同步修改余额表
     * @param bo
     * @return
     */
    boolean updateByBoAndBalance(OrderRechargeBo bo);
}
