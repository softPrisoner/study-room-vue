package com.ruoyi.studyroom.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ruoyi.studyroom.domain.Record;
import com.ruoyi.studyroom.domain.User;
import com.ruoyi.studyroom.domain.vo.RecordVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学习记录Mapper接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface RecordMapper extends BaseMapperPlus<RecordMapper, Record, RecordVo> {

    /**
     * 小时数降序排列
     * @param lqw
     * @return
     */
    List<RecordVo> selectVoListAndUser(@Param(Constants.WRAPPER) Wrapper<User> lqw);
}
