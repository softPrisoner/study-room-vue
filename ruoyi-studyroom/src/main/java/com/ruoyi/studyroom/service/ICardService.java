package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.Card;
import com.ruoyi.studyroom.domain.vo.CardVo;
import com.ruoyi.studyroom.domain.bo.CardBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 优惠卡Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface ICardService {

    /**
     * 查询优惠卡
     *
     * @param id 优惠卡主键
     * @return 优惠卡
     */
    CardVo queryById(Long id);

    /**
     * 查询优惠卡列表
     *
     * @param card 优惠卡
     * @return 优惠卡集合
     */
    TableDataInfo<CardVo> queryPageList(CardBo bo, PageQuery pageQuery);

    /**
     * 查询优惠卡列表
     *
     * @param card 优惠卡
     * @return 优惠卡集合
     */
    List<CardVo> queryList(CardBo bo);

    /**
     * 修改优惠卡
     *
     * @param card 优惠卡
     * @return 结果
     */
    Boolean insertByBo(CardBo bo);

    /**
     * 修改优惠卡
     *
     * @param card 优惠卡
     * @return 结果
     */
    Boolean updateByBo(CardBo bo);

    /**
     * 校验并批量删除优惠卡信息
     *
     * @param ids 需要删除的优惠卡主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
