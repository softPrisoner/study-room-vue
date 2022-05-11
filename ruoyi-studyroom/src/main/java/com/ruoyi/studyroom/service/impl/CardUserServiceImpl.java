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
import com.ruoyi.studyroom.domain.bo.CardUserBo;
import com.ruoyi.studyroom.domain.vo.CardUserVo;
import com.ruoyi.studyroom.domain.CardUser;
import com.ruoyi.studyroom.mapper.CardUserMapper;
import com.ruoyi.studyroom.service.ICardUserService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 优惠卡-会员关联Service业务层处理
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@RequiredArgsConstructor
@Service
public class CardUserServiceImpl implements ICardUserService {

    private final CardUserMapper baseMapper;

    /**
     * 查询优惠卡-会员关联
     *
     * @param id 优惠卡-会员关联主键
     * @return 优惠卡-会员关联
     */
    @Override
    public CardUserVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询优惠卡-会员关联列表
     *
     * @param bo 优惠卡-会员关联
     * @return 优惠卡-会员关联
     */
    @Override
    public TableDataInfo<CardUserVo> queryPageList(CardUserBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CardUser> lqw = buildQueryWrapper(bo);
        Page<CardUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询优惠卡-会员关联列表
     *
     * @param bo 优惠卡-会员关联
     * @return 优惠卡-会员关联
     */
    @Override
    public List<CardUserVo> queryList(CardUserBo bo) {
        LambdaQueryWrapper<CardUser> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CardUser> buildQueryWrapper(CardUserBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CardUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, CardUser::getUserId, bo.getUserId());
        lqw.eq(bo.getCardId() != null, CardUser::getCardId, bo.getCardId());
        return lqw;
    }

    /**
     * 新增优惠卡-会员关联
     *
     * @param bo 优惠卡-会员关联
     * @return 结果
     */
    @Override
    public Boolean insertByBo(CardUserBo bo) {
        CardUser add = BeanUtil.toBean(bo, CardUser.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改优惠卡-会员关联
     *
     * @param bo 优惠卡-会员关联
     * @return 结果
     */
    @Override
    public Boolean updateByBo(CardUserBo bo) {
        CardUser update = BeanUtil.toBean(bo, CardUser.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(CardUser entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除优惠卡-会员关联
     *
     * @param ids 需要删除的优惠卡-会员关联主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public List<CardUserVo> queryListByUserId(Long userId) {
        LambdaQueryWrapper<CardUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(CardUser::getUserId, userId);
        return baseMapper.selectVoListByUserId(lqw);

    }
}
