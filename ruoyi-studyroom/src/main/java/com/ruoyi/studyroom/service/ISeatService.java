package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.Seat;
import com.ruoyi.studyroom.domain.vo.SeatVo;
import com.ruoyi.studyroom.domain.bo.SeatBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 座位Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface ISeatService {

    /**
     * 查询座位
     *
     * @param seatId 座位主键
     * @return 座位
     */
    SeatVo queryById(Long seatId);

    /**
     * 查询座位列表
     *
     * @param seat 座位
     * @return 座位集合
     */
    TableDataInfo<SeatVo> queryPageList(SeatBo bo, PageQuery pageQuery);

    /**
     * 查询座位列表
     *
     * @param seat 座位
     * @return 座位集合
     */
    List<SeatVo> queryList(SeatBo bo);

    /**
     * 修改座位
     *
     * @param seat 座位
     * @return 结果
     */
    Boolean insertByBo(SeatBo bo);

    /**
     * 修改座位
     *
     * @param seat 座位
     * @return 结果
     */
    Boolean updateByBo(SeatBo bo);

    /**
     * 校验并批量删除座位信息
     *
     * @param seatIds 需要删除的座位主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
