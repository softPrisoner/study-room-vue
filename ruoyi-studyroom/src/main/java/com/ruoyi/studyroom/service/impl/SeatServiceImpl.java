package com.ruoyi.studyroom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.studyroom.domain.bo.RoomSeatBo;
import com.ruoyi.studyroom.mapper.RoomSeatMapper;
import com.ruoyi.studyroom.service.IRoomSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.studyroom.domain.bo.SeatBo;
import com.ruoyi.studyroom.domain.vo.SeatVo;
import com.ruoyi.studyroom.domain.Seat;
import com.ruoyi.studyroom.mapper.SeatMapper;
import com.ruoyi.studyroom.service.ISeatService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 座位Service业务层处理
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@RequiredArgsConstructor
@Service
public class SeatServiceImpl implements ISeatService {


    private final IRoomSeatService roomSeatService;
    private final SeatMapper baseMapper;
    /**
     * 查询座位
     *
     * @param seatId 座位主键
     * @return 座位
     */
    @Override
    public SeatVo queryById(Long seatId){
        return baseMapper.selectVoById(seatId);
    }

    /**
     * 查询座位列表
     *
     * @param bo 座位
     * @return 座位
     */
    @Override
    public TableDataInfo<SeatVo> queryPageList(SeatBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Seat> lqw = buildQueryWrapper(bo);
        Page<SeatVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询座位列表
     *
     * @param bo 座位
     * @return 座位
     */
    @Override
    public List<SeatVo> queryList(SeatBo bo) {
        LambdaQueryWrapper<Seat> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Seat> buildQueryWrapper(SeatBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Seat> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getRoomId() != null, Seat::getRoomId, bo.getRoomId());
        lqw.eq(bo.getSeatNum() != null, Seat::getSeatNum, bo.getSeatNum());
        lqw.eq(bo.getSeatArea() != null, Seat::getSeatArea, bo.getSeatArea());
        lqw.like(StringUtils.isNotBlank(bo.getAreaName()), Seat::getAreaName, bo.getAreaName());
        return lqw;
    }

    /**
     * 新增座位
     *
     * @param bo 座位
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertByBo(SeatBo bo) {
        Seat add = BeanUtil.toBean(bo, Seat.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        RoomSeatBo roomSeatBo = new RoomSeatBo();
        roomSeatBo.setSeatId(add.getSeatId());
        roomSeatBo.setRoomId(bo.getRoomId());
        roomSeatService.insertByBo(roomSeatBo);
        if (flag) {
            bo.setSeatId(add.getSeatId());
        }
        return flag;
    }

    /**
     * 修改座位
     *
     * @param bo 座位
     * @return 结果
     */
    @Override
    public Boolean updateByBo(SeatBo bo) {
        Seat update = BeanUtil.toBean(bo, Seat.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Seat entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除座位
     *
     * @param ids 需要删除的座位主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
