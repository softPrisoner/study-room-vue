package com.ruoyi.studyroom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.studyroom.domain.bo.BalanceBo;
import com.ruoyi.studyroom.domain.bo.RecordBo;
import com.ruoyi.studyroom.domain.bo.SeatRecordBo;
import com.ruoyi.studyroom.domain.vo.BalanceVo;
import com.ruoyi.studyroom.domain.vo.RecordVo;
import com.ruoyi.studyroom.service.IBalanceService;
import com.ruoyi.studyroom.service.IRecordService;
import com.ruoyi.studyroom.service.ISeatRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.studyroom.domain.bo.OrderBo;
import com.ruoyi.studyroom.domain.vo.OrderVo;
import com.ruoyi.studyroom.domain.Order;
import com.ruoyi.studyroom.mapper.OrderMapper;
import com.ruoyi.studyroom.service.IOrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 订单Service业务层处理
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderMapper baseMapper;
    private final ISeatRecordService seatRecordService;
    private final IBalanceService balanceService;
    private final IRecordService recordService;
    /**
     * 查询订单
     *
     * @param id 订单主键
     * @return 订单
     */
    @Override
    public OrderVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询订单列表
     *
     * @param bo 订单
     * @return 订单
     */
    @Override
    public TableDataInfo<OrderVo> queryPageList(OrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Order> lqw = buildQueryWrapper(bo);
        Page<OrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询订单列表
     *
     * @param bo 订单
     * @return 订单
     */
    @Override
    public List<OrderVo> queryList(OrderBo bo) {
        LambdaQueryWrapper<Order> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Order> buildQueryWrapper(OrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Order> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOrderId() != null, Order::getOrderId, bo.getOrderId());
        lqw.eq(bo.getUserId() != null, Order::getUserId, bo.getUserId());
        lqw.eq(bo.getRoomId() != null, Order::getRoomId, bo.getRoomId());
        lqw.eq(bo.getSeatId() != null, Order::getSeatId, bo.getSeatId());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), Order::getDescription, bo.getDescription());
        lqw.eq(bo.getStartTime() != null, Order::getStartTime, bo.getStartTime());
        lqw.eq(bo.getEndTime() != null, Order::getEndTime, bo.getEndTime());
        lqw.eq(bo.getPayStatus() != null, Order::getPayStatus, bo.getPayStatus());
        return lqw;
    }

    /**
     * 新增订单
     *
     * @param bo 订单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertByBo(OrderBo bo) {
        //使用账户余额预定座位
        if (bo.getFlag() == 0){
            BalanceVo balanceVo = balanceService.queryById(LoginHelper.getUserId());
            BalanceBo balanceBo = BeanUtil.toBean(balanceVo, BalanceBo.class);
            if ( balanceBo.getBalance() >= bo.getTotal()){
                balanceBo.setBalance(balanceBo.getBalance() - bo.getTotal());
                balanceService.updateByBo(balanceBo);
            }else {
                return false;
            }

        }else {
            //todo 使用消费卡预定座位 逻辑
        }
        Order add = BeanUtil.toBean(bo, Order.class);
        SeatRecordBo seatRecordBo = BeanUtil.toBean(bo, SeatRecordBo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        //同步更新座位-记录表
        seatRecordService.insertByBo(seatRecordBo);
        if (flag) {
            bo.setId(add.getId());
        }

        return flag;
    }

    /**
     * 修改订单
     *
     * @param bo 订单
     * @return 结果
     */
    @Override
    public Boolean updateByBo(OrderBo bo) {
        Order update = BeanUtil.toBean(bo, Order.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 更新订单状态为已消费，同步更新学习记录排行榜
     *
     * @param orderBo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateByBoAndRank(OrderBo orderBo) {

        RecordVo recordVo = recordService.queryByUserId(LoginHelper.getUserId());
        RecordBo recordBo = BeanUtil.toBean(recordVo, RecordBo.class);
        recordBo.setHours(recordVo.getHours()+ orderBo.getHours());

        return updateByBo(orderBo).equals(recordService.updateByBo(recordBo));
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Order entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的订单主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
