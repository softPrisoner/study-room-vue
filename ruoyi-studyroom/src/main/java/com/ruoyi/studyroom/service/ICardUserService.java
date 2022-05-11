package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.CardUser;
import com.ruoyi.studyroom.domain.vo.CardUserVo;
import com.ruoyi.studyroom.domain.bo.CardUserBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 优惠卡-会员关联Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface ICardUserService {

    /**
     * 查询优惠卡-会员关联
     *
     * @param id 优惠卡-会员关联主键
     * @return 优惠卡-会员关联
     */
    CardUserVo queryById(Long id);

    /**
     * 查询优惠卡-会员关联列表
     *
     * @param cardUser 优惠卡-会员关联
     * @return 优惠卡-会员关联集合
     */
    TableDataInfo<CardUserVo> queryPageList(CardUserBo bo, PageQuery pageQuery);

    /**
     * 查询优惠卡-会员关联列表
     *
     * @param cardUser 优惠卡-会员关联
     * @return 优惠卡-会员关联集合
     */
    List<CardUserVo> queryList(CardUserBo bo);

    /**
     * 修改优惠卡-会员关联
     *
     * @param cardUser 优惠卡-会员关联
     * @return 结果
     */
    Boolean insertByBo(CardUserBo bo);

    /**
     * 修改优惠卡-会员关联
     *
     * @param cardUser 优惠卡-会员关联
     * @return 结果
     */
    Boolean updateByBo(CardUserBo bo);

    /**
     * 校验并批量删除优惠卡-会员关联信息
     *
     * @param ids 需要删除的优惠卡-会员关联主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    List<CardUserVo> queryListByUserId(Long userId);
}
