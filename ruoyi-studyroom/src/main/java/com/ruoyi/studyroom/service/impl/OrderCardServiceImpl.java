package com.ruoyi.studyroom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.studyroom.domain.bo.CardUserBo;
import com.ruoyi.studyroom.service.ICardUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.studyroom.domain.bo.OrderCardBo;
import com.ruoyi.studyroom.domain.vo.OrderCardVo;
import com.ruoyi.studyroom.domain.OrderCard;
import com.ruoyi.studyroom.mapper.OrderCardMapper;
import com.ruoyi.studyroom.service.IOrderCardService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 购卡管理Service业务层处理
 *
 * @author P_Peaceful
 * @date 2022-05-10
 */
@RequiredArgsConstructor
@Service
public class OrderCardServiceImpl implements IOrderCardService {

    private final OrderCardMapper baseMapper;
    private final ICardUserService cardUserService;
    /**
     * 查询购卡管理
     *
     * @param id 购卡管理主键
     * @return 购卡管理
     */
    @Override
    public OrderCardVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询购卡管理列表
     *
     * @param bo 购卡管理
     * @return 购卡管理
     */
    @Override
    public TableDataInfo<OrderCardVo> queryPageList(OrderCardBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<OrderCard> lqw = buildQueryWrapper(bo);
        Page<OrderCardVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询购卡管理列表
     *
     * @param bo 购卡管理
     * @return 购卡管理
     */
    @Override
    public List<OrderCardVo> queryList(OrderCardBo bo) {
        LambdaQueryWrapper<OrderCard> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<OrderCard> buildQueryWrapper(OrderCardBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<OrderCard> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, OrderCard::getUserId, bo.getUserId());
        lqw.eq(bo.getCardId() != null, OrderCard::getCardId, bo.getCardId());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderId()), OrderCard::getOrderId, bo.getOrderId());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), OrderCard::getDescription, bo.getDescription());
        lqw.eq(bo.getAmountTotal() != null, OrderCard::getAmountTotal, bo.getAmountTotal());
        lqw.eq(bo.getPayStatus() != null, OrderCard::getPayStatus, bo.getPayStatus());
        return lqw;
    }

    /**
     * 新增购卡管理
     *
     * @param bo 购卡管理
     * @return 结果
     */
    @Override
    public Boolean insertByBo(OrderCardBo bo) {
        OrderCard add = BeanUtil.toBean(bo, OrderCard.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改购卡管理
     *
     * @param bo 购卡管理
     * @return 结果
     */
    @Override
    public Boolean updateByBo(OrderCardBo bo) {
        OrderCard update = BeanUtil.toBean(bo, OrderCard.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateByBoAndCard(OrderCardBo bo) {


        CardUserBo cardUserBo = new CardUserBo();
        cardUserBo.setUserId(bo.getUserId());
        cardUserBo.setCardId(bo.getCardId());

        return updateByBo(bo).equals(cardUserService.insertByBo(cardUserBo));
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(OrderCard entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除购卡管理
     *
     * @param ids 需要删除的购卡管理主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 根据订单ID查询
     *
     * @param orderId
     * @return
     */
    @Override
    public OrderCardVo queryByOrderId(String orderId) {
        LambdaQueryWrapper<OrderCard> lqw = Wrappers.lambdaQuery();
        lqw.eq(OrderCard::getOrderId, orderId);
        return baseMapper.selectVoOne(lqw);
    }
}
