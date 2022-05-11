package com.ruoyi.studyroom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.studyroom.domain.bo.CardBo;
import com.ruoyi.studyroom.domain.vo.CardVo;
import com.ruoyi.studyroom.domain.Card;
import com.ruoyi.studyroom.mapper.CardMapper;
import com.ruoyi.studyroom.service.ICardService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 优惠卡Service业务层处理
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@RequiredArgsConstructor
@Service
public class CardServiceImpl implements ICardService {

    private final CardMapper baseMapper;

    /**
     * 查询优惠卡
     *
     * @param id 优惠卡主键
     * @return 优惠卡
     */
    @Override
    public CardVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询优惠卡列表
     *
     * @param bo 优惠卡
     * @return 优惠卡
     */
    @Override
    public TableDataInfo<CardVo> queryPageList(CardBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Card> lqw = buildQueryWrapper(bo);
        Page<CardVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询优惠卡列表
     *
     * @param bo 优惠卡
     * @return 优惠卡
     */
    @Override
    public List<CardVo> queryList(CardBo bo) {
        LambdaQueryWrapper<Card> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Card> buildQueryWrapper(CardBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Card> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getCardTitle()), Card::getCardTitle, bo.getCardTitle());
        lqw.eq(bo.getPrice() != null, Card::getPrice, bo.getPrice());
        lqw.eq(bo.getUsable() != null, Card::getUsable, bo.getUsable());
        lqw.eq(bo.getFrequency() != null, Card::getFrequency, bo.getFrequency());
        lqw.eq(bo.getTerm() != null, Card::getTerm, bo.getTerm());
        lqw.eq(bo.getStatus() != null, Card::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增优惠卡
     *
     * @param bo 优惠卡
     * @return 结果
     */
    @Override
    public Boolean insertByBo(CardBo bo) {
        Card add = BeanUtil.toBean(bo, Card.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改优惠卡
     *
     * @param bo 优惠卡
     * @return 结果
     */
    @Override
    public Boolean updateByBo(CardBo bo) {
        Card update = BeanUtil.toBean(bo, Card.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Card entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除优惠卡
     *
     * @param ids 需要删除的优惠卡主键
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
