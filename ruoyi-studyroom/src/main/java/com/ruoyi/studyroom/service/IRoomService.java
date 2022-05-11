package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.Room;
import com.ruoyi.studyroom.domain.vo.RoomVo;
import com.ruoyi.studyroom.domain.bo.RoomBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 自习室Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface IRoomService {

    /**
     * 查询自习室
     *
     * @param roomId 自习室主键
     * @return 自习室
     */
    RoomVo queryById(Long roomId);

    /**
     * 查询自习室列表
     *
     * @param room 自习室
     * @return 自习室集合
     */
    TableDataInfo<RoomVo> queryPageList(RoomBo bo, PageQuery pageQuery);

    /**
     * 查询自习室列表
     *
     * @param room 自习室
     * @return 自习室集合
     */
    List<RoomVo> queryList(RoomBo bo);

    /**
     * 修改自习室
     *
     * @param room 自习室
     * @return 结果
     */
    Boolean insertByBo(RoomBo bo);

    /**
     * 修改自习室
     *
     * @param room 自习室
     * @return 结果
     */
    Boolean updateByBo(RoomBo bo);

    /**
     * 校验并批量删除自习室信息
     *
     * @param roomIds 需要删除的自习室主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
