package com.ruoyi.studyroom.service;

import com.ruoyi.studyroom.domain.vo.RoomSeatVo;
import com.ruoyi.studyroom.domain.bo.RoomSeatBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 自习室-座位关联Service接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface IRoomSeatService {

    /**
     * 查询自习室-座位关联
     *
     * @param id 自习室-座位关联主键
     * @return 自习室-座位关联
     */
    RoomSeatVo queryById(Long id);

    /**
     * 查询自习室-座位关联列表
     *
     * @param roomSeat 自习室-座位关联
     * @return 自习室-座位关联集合
     */
    TableDataInfo<RoomSeatVo> queryPageList(RoomSeatBo bo, PageQuery pageQuery);

    /**
     * 查询自习室-座位关联列表
     *
     * @param roomSeat 自习室-座位关联
     * @return 自习室-座位关联集合
     */
    List<RoomSeatVo> queryList(RoomSeatBo bo);

    /**
     * 修改自习室-座位关联
     *
     * @param roomSeat 自习室-座位关联
     * @return 结果
     */
    Boolean insertByBo(RoomSeatBo bo);

    /**
     * 修改自习室-座位关联
     *
     * @param roomSeat 自习室-座位关联
     * @return 结果
     */
    Boolean updateByBo(RoomSeatBo bo);

    /**
     * 校验并批量删除自习室-座位关联信息
     *
     * @param ids 需要删除的自习室-座位关联主键集合
     * @param isValid 是否校验,true-删除前校验,false-不校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
