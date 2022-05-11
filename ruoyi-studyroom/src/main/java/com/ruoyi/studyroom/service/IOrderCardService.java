package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.OrderCard;
import com.ruoyi.studyroom.domain.vo.OrderCardVo;
import com.ruoyi.studyroom.domain.bo.OrderCardBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 购卡管理Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-10
 */
public interface IOrderCardService {

    /**
     * 查询购卡管理
     *
     * @param id 购卡管理主键
     * @return 购卡管理
     */
    OrderCardVo queryById(Long id);

    /**
     * 查询购卡管理列表
     *
     * @param orderCard 购卡管理
     * @return 购卡管理集合
     */
    TableDataInfo<OrderCardVo> queryPageList(OrderCardBo bo, PageQuery pageQuery);

    /**
     * 查询购卡管理列表
     *
     * @param orderCard 购卡管理
     * @return 购卡管理集合
     */
    List<OrderCardVo> queryList(OrderCardBo bo);

    /**
     * 修改购卡管理
     *
     * @param orderCard 购卡管理
     * @return 结果
     */
    Boolean insertByBo(OrderCardBo bo);

    /**
     * 修改购卡管理
     *
     * @param orderCard 购卡管理
     * @return 结果
     */
    Boolean updateByBo(OrderCardBo bo);

    /**
     * 校验并批量删除购卡管理信息
     *
     * @param ids 需要删除的购卡管理主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**根据订单ID查询
     *
     * @param orderId
     * @return
     */
    OrderCardVo queryByOrderId(String orderId);

    /**
     * 更新优惠卡订单 同步更新优惠卡-用户关联表
     * @param bo
     */
    Boolean updateByBoAndCard(OrderCardBo bo);
}
