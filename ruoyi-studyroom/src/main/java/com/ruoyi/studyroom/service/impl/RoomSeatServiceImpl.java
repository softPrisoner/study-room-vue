package com.ruoyi.studyroom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.studyroom.domain.bo.RoomSeatBo;
import com.ruoyi.studyroom.domain.vo.RoomSeatVo;
import com.ruoyi.studyroom.domain.RoomSeat;
import com.ruoyi.studyroom.mapper.RoomSeatMapper;
import com.ruoyi.studyroom.service.IRoomSeatService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 自习室-座位关联Service业务层处理
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@RequiredArgsConstructor
@Service
public class RoomSeatServiceImpl implements IRoomSeatService {

    private final RoomSeatMapper baseMapper;

    /**
     * 查询自习室-座位关联
     *
     * @param id 自习室-座位关联主键
     * @return 自习室-座位关联
     */
    @Override
    public RoomSeatVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询自习室-座位关联列表
     *
     * @param bo 自习室-座位关联
     * @return 自习室-座位关联
     */
    @Override
    public TableDataInfo<RoomSeatVo> queryPageList(RoomSeatBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<RoomSeat> lqw = buildQueryWrapper(bo);
        Page<RoomSeatVo> result = baseMapper.selectPageBySeat(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询自习室-座位关联列表
     *
     * @param bo 自习室-座位关联
     * @return 自习室-座位关联
     */
    @Override
    public List<RoomSeatVo> queryList(RoomSeatBo bo) {
        LambdaQueryWrapper<RoomSeat> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<RoomSeat> buildQueryWrapper(RoomSeatBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<RoomSeat> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getRoomId() != null, RoomSeat::getRoomId, bo.getRoomId());
        lqw.eq(bo.getSeatId() != null, RoomSeat::getSeatId, bo.getSeatId());
        return lqw;
    }

    /**
     * 新增自习室-座位关联
     *
     * @param bo 自习室-座位关联
     * @return 结果
     */
    @Override
    public Boolean insertByBo(RoomSeatBo bo) {
        RoomSeat add = BeanUtil.toBean(bo, RoomSeat.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改自习室-座位关联
     *
     * @param bo 自习室-座位关联
     * @return 结果
     */
    @Override
    public Boolean updateByBo(RoomSeatBo bo) {
        RoomSeat update = BeanUtil.toBean(bo, RoomSeat.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(RoomSeat entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除自习室-座位关联
     *
     * @param ids 需要删除的自习室-座位关联主键
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
