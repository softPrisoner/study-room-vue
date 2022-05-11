package com.ruoyi.studyroom.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ruoyi.studyroom.domain.CardUser;
import com.ruoyi.studyroom.domain.vo.CardUserVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠卡-会员关联Mapper接口
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
public interface CardUserMapper extends BaseMapperPlus<CardUserMapper, CardUser, CardUserVo> {

    List<CardUserVo> selectVoListByUserId(@Param(Constants.WRAPPER) Wrapper<CardUser> lqw);
}
