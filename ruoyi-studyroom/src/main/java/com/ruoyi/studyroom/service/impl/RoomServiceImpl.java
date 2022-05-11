package com.ruoyi.studyroom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.studyroom.domain.bo.RoomBo;
import com.ruoyi.studyroom.domain.vo.RoomVo;
import com.ruoyi.studyroom.domain.Room;
import com.ruoyi.studyroom.mapper.RoomMapper;
import com.ruoyi.studyroom.service.IRoomService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 自习室Service业务层处理
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements IRoomService {

    private final RoomMapper baseMapper;

    /**
     * 查询自习室
     *
     * @param roomId 自习室主键
     * @return 自习室
     */
    @Override
    public RoomVo queryById(Long roomId){
        return baseMapper.selectVoById(roomId);
    }

    /**
     * 查询自习室列表
     *
     * @param bo 自习室
     * @return 自习室
     */
    @Override
    public TableDataInfo<RoomVo> queryPageList(RoomBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Room> lqw = buildQueryWrapper(bo);
        Page<RoomVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询自习室列表
     *
     * @param bo 自习室
     * @return 自习室
     */
    @Override
    public List<RoomVo> queryList(RoomBo bo) {
        LambdaQueryWrapper<Room> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Room> buildQueryWrapper(RoomBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Room> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getRoomName()), Room::getRoomName, bo.getRoomName());
        lqw.eq(StringUtils.isNotBlank(bo.getRoomAddr()), Room::getRoomAddr, bo.getRoomAddr());
        lqw.eq(StringUtils.isNotBlank(bo.getPhone()), Room::getPhone, bo.getPhone());
        return lqw;
    }

    /**
     * 新增自习室
     *
     * @param bo 自习室
     * @return 结果
     */
    @Override
    public Boolean insertByBo(RoomBo bo) {
        Room add = BeanUtil.toBean(bo, Room.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRoomId(add.getRoomId());
        }
        return flag;
    }

    /**
     * 修改自习室
     *
     * @param bo 自习室
     * @return 结果
     */
    @Override
    public Boolean updateByBo(RoomBo bo) {
        Room update = BeanUtil.toBean(bo, Room.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Room entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除自习室
     *
     * @param roomIds 需要删除的自习室主键
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
