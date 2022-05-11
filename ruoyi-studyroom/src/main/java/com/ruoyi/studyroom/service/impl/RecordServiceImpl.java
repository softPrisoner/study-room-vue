package com.ruoyi.studyroom.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.ruoyi.studyroom.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.studyroom.domain.bo.RecordBo;
import com.ruoyi.studyroom.domain.vo.RecordVo;
import com.ruoyi.studyroom.domain.Record;
import com.ruoyi.studyroom.mapper.RecordMapper;
import com.ruoyi.studyroom.service.IRecordService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 学习记录Service业务层处理
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@RequiredArgsConstructor
@Service
public class RecordServiceImpl implements IRecordService {

    private final RecordMapper baseMapper;

    /**
     * 查询学习记录
     *
     * @param id 学习记录主键
     * @return 学习记录
     */
    @Override
    public RecordVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询学习记录列表
     *
     * @param bo 学习记录
     * @return 学习记录
     */
    @Override
    public TableDataInfo<RecordVo> queryPageList(RecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Record> lqw = buildQueryWrapper(bo);
        Page<RecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询学习记录列表
     *
     * @param bo 学习记录
     * @return 学习记录
     */
    @Override
    public List<RecordVo> queryList(RecordBo bo) {
        LambdaQueryWrapper<Record> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Record> buildQueryWrapper(RecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Record> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, Record::getUserId, bo.getUserId());
        lqw.eq(bo.getHours() != null, Record::getHours, bo.getHours());
        return lqw;
    }

    /**
     * 新增学习记录
     *
     * @param bo 学习记录
     * @return 结果
     */
    @Override
    public Boolean insertByBo(RecordBo bo) {
        Record add = BeanUtil.toBean(bo, Record.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改学习记录
     *
     * @param bo 学习记录
     * @return 结果
     */
    @Override
    public Boolean updateByBo(RecordBo bo) {
        Record update = BeanUtil.toBean(bo, Record.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(Record entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除学习记录
     *
     * @param ids 需要删除的学习记录主键
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public RecordVo queryByUserId(Long userId) {
        LambdaQueryWrapper<Record> lqw = Wrappers.lambdaQuery();
        lqw.eq(Record::getUserId, userId);
        return baseMapper.selectVoOne(lqw);
    }

    /**
     * 根据小时数降序
     * @param recordBo
     * @return
     */
    @Override
    public List<RecordVo> queryDescList() {
        LambdaQueryWrapper<User> lqw = Wrappers.lambdaQuery();
        lqw.eq(User::getStatus,1);

        return baseMapper.selectVoListAndUser(lqw);
    }
}
