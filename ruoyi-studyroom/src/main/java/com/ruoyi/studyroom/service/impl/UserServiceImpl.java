package com.ruoyi.studyroom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.studyroom.domain.bo.BalanceBo;
import com.ruoyi.studyroom.domain.bo.RecordBo;
import com.ruoyi.studyroom.service.IBalanceService;
import com.ruoyi.studyroom.service.IRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.studyroom.domain.bo.UserBo;
import com.ruoyi.studyroom.domain.vo.UserVo;
import com.ruoyi.studyroom.domain.User;
import com.ruoyi.studyroom.mapper.UserMapper;
import com.ruoyi.studyroom.service.IUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会员Service业务层处理
 *
 * @author P_Peaceful
 * @date 2022-05-09
 */
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final UserMapper baseMapper;
    private final IBalanceService balanceService;
    private final IRecordService recordService;

    /**
     * 查询会员
     *
     * @param userId 会员主键
     * @return 会员
     */
    @Override
    public UserVo queryById(Long userId){
        return baseMapper.selectVoById(userId);
    }

    /**
     * 查询会员列表
     *
     * @param bo 会员
     * @return 会员
     */
    @Override
    public TableDataInfo<UserVo> queryPageList(UserBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<User> lqw = buildQueryWrapper(bo);
        Page<UserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询会员列表
     *
     * @param bo 会员
     * @return 会员
     */
    @Override
    public List<UserVo> queryList(UserBo bo) {
        LambdaQueryWrapper<User> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }


    /**
     * 新增会员
     *
     * @param bo 会员
     * @return 结果
     */
    @Override
    public Boolean insertByBo(UserBo bo) {
        User add = BeanUtil.toBean(bo, User.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUserId(add.getUserId());
        }
        return flag;
    }

    /**
     * 修改会员
     *
     * @param bo 会员
     * @return 结果
     */
    @Override
    public Boolean updateByBo(UserBo bo) {
        User update = BeanUtil.toBean(bo, User.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(User entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除会员
     *
     * @param ids 需要删除的会员主键
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
     * 根据 openid 获取 用户信息
     *
     * @param openid
     * @return
     */
    @Override
    public User selectUserByOpenid(String openid) {
        LambdaQueryWrapper<User> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(openid), User::getOpenid, openid);

        return baseMapper.selectVoByOpenid(lqw);
    }

    /**
     * 根据 openid插入新用户
     *
     * @param bo
     * @return
     */
    @Override
    public User insertByOpenid(UserBo bo) {
        User user = BeanUtil.toBean(bo, User.class);
        boolean flag = baseMapper.insert(user) > 0;
        if (flag){
            return user;
        }
        return null;
    }

    /**
     * 增加新用户，同步插入余额信息 以及学习记录排行榜
     *
     * @param bo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public User insertByOpenidAndBalance(UserBo bo) {
        User user = insertByOpenid(bo);
        BalanceBo balanceBo = new BalanceBo();
        balanceBo.setUserId(user.getUserId());
        balanceBo.setBalance(0L);
        RecordBo recordBo = new RecordBo();
        recordBo.setUserId(user.getUserId());
        recordBo.setHours(0.00);
        balanceService.insertByBo(balanceBo);
        recordService.insertByBo(recordBo);
        return user;
    }

    private LambdaQueryWrapper<User> buildQueryWrapper(UserBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<User> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getOpenid()), User::getOpenid, bo.getOpenid());
        lqw.like(StringUtils.isNotBlank(bo.getNickname()), User::getNickname, bo.getNickname());
        lqw.eq(StringUtils.isNotBlank(bo.getAvatarUrl()), User::getAvatarUrl, bo.getAvatarUrl());
        lqw.eq(bo.getSex() != null, User::getSex, bo.getSex());
        lqw.eq(StringUtils.isNotBlank(bo.getPhone()), User::getPhone, bo.getPhone());
        lqw.eq(bo.getStatus() != null, User::getStatus, bo.getStatus());
        return lqw;
    }
}
