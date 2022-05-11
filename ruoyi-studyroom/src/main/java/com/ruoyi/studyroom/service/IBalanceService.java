package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.Balance;
import com.ruoyi.studyroom.domain.vo.BalanceVo;
import com.ruoyi.studyroom.domain.bo.BalanceBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 余额
Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface IBalanceService {

    /**
     * 查询余额

     *
     * @param id 余额
主键
     * @return 余额

     */
    BalanceVo queryById(Long id);

    /**
     * 查询余额
列表
     *
     * @param balance 余额

     * @return 余额
集合
     */
    TableDataInfo<BalanceVo> queryPageList(BalanceBo bo, PageQuery pageQuery);

    /**
     * 查询余额
列表
     *
     * @param balance 余额

     * @return 余额
集合
     */
    List<BalanceVo> queryList(BalanceBo bo);

    /**
     * 修改余额

     *
     * @param balance 余额

     * @return 结果
     */
    Boolean insertByBo(BalanceBo bo);

    /**
     * 修改余额

     *
     * @param balance 余额

     * @return 结果
     */
    Boolean updateByBo(BalanceBo bo);

    /**
     * 校验并批量删除余额信息
     *
     * @param ids 需要删除的余额主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 根据用户ID获取余额信息
     * @param userId
     * @return
     */
    BalanceVo queryByUserId(Long userId);
}
