package com.ruoyi.studyroom.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.studyroom.domain.RoomSeat;
import com.ruoyi.studyroom.domain.vo.RoomSeatVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

/**
 * 自习室-座位关联Mapper接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface RoomSeatMapper extends BaseMapperPlus<RoomSeatMapper, RoomSeat, RoomSeatVo> {
    /**
     * 多表查询 自习室和座位关联
     * @param page
     * @param queryWrapper
     * @return
     */
    Page<RoomSeatVo> selectPageBySeat(@Param("page")Page<RoomSeat> page, @Param(Constants.WRAPPER)Wrapper<RoomSeat> queryWrapper);
}
