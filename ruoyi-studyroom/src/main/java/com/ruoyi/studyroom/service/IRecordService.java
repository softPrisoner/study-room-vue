package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.Record;
import com.ruoyi.studyroom.domain.vo.RecordVo;
import com.ruoyi.studyroom.domain.bo.RecordBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 学习记录Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface IRecordService {

    /**
     * 查询学习记录
     *
     * @param id 学习记录主键
     * @return 学习记录
     */
    RecordVo queryById(Long id);

    /**
     * 查询学习记录列表
     *
     * @param record 学习记录
     * @return 学习记录集合
     */
    TableDataInfo<RecordVo> queryPageList(RecordBo bo, PageQuery pageQuery);

    /**
     * 查询学习记录列表
     *
     * @param record 学习记录
     * @return 学习记录集合
     */
    List<RecordVo> queryList(RecordBo bo);

    /**
     * 修改学习记录
     *
     * @param record 学习记录
     * @return 结果
     */
    Boolean insertByBo(RecordBo bo);

    /**
     * 修改学习记录
     *
     * @param record 学习记录
     * @return 结果
     */
    Boolean updateByBo(RecordBo bo);

    /**
     * 校验并批量删除学习记录信息
     *
     * @param ids 需要删除的学习记录主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    RecordVo queryByUserId(Long userId);

    List<RecordVo> queryDescList();
}
