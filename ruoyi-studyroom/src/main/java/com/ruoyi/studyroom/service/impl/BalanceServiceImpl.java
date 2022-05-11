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
import com.ruoyi.studyroom.domain.bo.BalanceBo;
import com.ruoyi.studyroom.domain.vo.BalanceVo;
import com.ruoyi.studyroom.domain.Balance;
import com.ruoyi.studyroom.mapper.BalanceMapper;
import com.ruoyi.studyroom.service.IBalanceService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 余额
Service业务层处理
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@RequiredArgsConstructor
@Service
public class BalanceServiceImpl implements IBalanceService {

    private final BalanceMapper baseMapper;

    /**
     * 查询余额

     *
     * @param id 余额
主键
     * @return 余额

     */
    @Override
    public BalanceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询余额
列表
     *
     * @param bo 余额

     * @return 余额

     */
    @Override
    public TableDataInfo<BalanceVo> queryPageList(BalanceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Balance> lqw = buildQueryWrapper(bo);
        Page<BalanceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询余额
列表
     *
     * @param bo 余额

     * @return 余额

     */
    @Override
    public List<BalanceVo> queryList(BalanceBo bo) {
        LambdaQueryWrapper<Balance> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Balance> buildQueryWrapper(BalanceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Balance> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, Balance::getUserId, bo.getUserId());
        lqw.eq(bo.getBalance() != null, Balance::getBalance, bo.getBalance());
        return lqw;
    }

    /**
     * 新增余额

     *
     * @param bo 余额

     * @return 结果
     */
    @Override
    public Boolean insertByBo(BalanceBo bo) {
        Balance add = BeanUtil.toBean(bo, Balance.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改余额

     *
     * @param bo 余额

     * @return 结果
     */
    @Override
    public Boolean updateByBo(BalanceBo bo) {
        Balance update = BeanUtil.toBean(bo, Balance.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Balance entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除余额

     *
     * @param ids 需要删除的余额
主键
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
     * 根据用户ID获取余额信息
     *
     * @param userId
     * @return
     */
    @Override
    public BalanceVo queryByUserId(Long userId) {
        LambdaQueryWrapper<Balance> lqw = Wrappers.lambdaQuery();
        lqw.eq(Balance::getUserId, userId);
        return baseMapper.selectVoOne(lqw);
    }
}
