package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.User;
import com.ruoyi.studyroom.domain.vo.UserVo;
import com.ruoyi.studyroom.domain.bo.UserBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 会员Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-09
 */
public interface IUserService {

    /**
     * 查询会员
     *
     * @param userId 会员主键
     * @return 会员
     */
    UserVo queryById(Long userId);

    /**
     * 查询会员列表
     *
     * @param user 会员
     * @return 会员集合
     */
    TableDataInfo<UserVo> queryPageList(UserBo bo, PageQuery pageQuery);

    /**
     * 查询会员列表
     *
     * @param user 会员
     * @return 会员集合
     */
    List<UserVo> queryList(UserBo bo);

    /**
     * 修改会员
     *
     * @param user 会员
     * @return 结果
     */
    Boolean insertByBo(UserBo bo);

    /**
     * 修改会员
     *
     * @param user 会员
     * @return 结果
     */
    Boolean updateByBo(UserBo bo);

    /**
     * 校验并批量删除会员信息
     *
     * @param userIds 需要删除的会员主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 根据 openid 获取 用户信息
     * @param openid
     * @return
     */
    User selectUserByOpenid(String openid);

    /**
     * 根据 openid插入新用户
     * @param bo
     * @return
     */
    User insertByOpenid(UserBo bo);

    /**
     * 增加新用户，同步插入余额信息
     * @param bo
     * @return
     */
    User insertByOpenidAndBalance(UserBo bo);
}
