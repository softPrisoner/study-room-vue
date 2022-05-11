package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.SeatRecord;
import com.ruoyi.studyroom.domain.vo.SeatRecordVo;
import com.ruoyi.studyroom.domain.bo.SeatRecordBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 座位-记录Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface ISeatRecordService {

    /**
     * 查询座位-记录
     *
     * @param id 座位-记录主键
     * @return 座位-记录
     */
    SeatRecordVo queryById(Long id);

    /**
     * 查询座位-记录列表
     *
     * @param seatRecord 座位-记录
     * @return 座位-记录集合
     */
    TableDataInfo<SeatRecordVo> queryPageList(SeatRecordBo bo, PageQuery pageQuery);

    /**
     * 查询座位-记录列表
     *
     * @param seatRecord 座位-记录
     * @return 座位-记录集合
     */
    List<SeatRecordVo> queryList(SeatRecordBo bo);

    /**
     * 修改座位-记录
     *
     * @param seatRecord 座位-记录
     * @return 结果
     */
    Boolean insertByBo(SeatRecordBo bo);

    /**
     * 修改座位-记录
     *
     * @param seatRecord 座位-记录
     * @return 结果
     */
    Boolean updateByBo(SeatRecordBo bo);

    /**
     * 校验并批量删除座位-记录信息
     *
     * @param ids 需要删除的座位-记录主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 查询不可以预定的座位ID
     * @param bo
     * @return
     */
    List<SeatRecordVo> queryListByTime(SeatRecordBo bo);
}
