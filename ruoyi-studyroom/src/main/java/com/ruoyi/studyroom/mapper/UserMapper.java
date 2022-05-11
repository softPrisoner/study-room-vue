package com.ruoyi.studyroom.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ruoyi.studyroom.domain.User;
import com.ruoyi.studyroom.domain.vo.UserVo;
import com.ruoyi.common.core.mapper.BaseMapperPlus;
import org.apache.ibatis.annotations.Param;

/**
 * 会员Mapper接口
 *
 * @author P_Peaceful
 * @date 2022-05-09
 */
public interface UserMapper extends BaseMapperPlus<UserMapper, User, UserVo> {

    /**
     * 根据 openid 返回 user
     * @param lqw
     * @return UserVo
     */
    User selectVoByOpenid(@Param(Constants.WRAPPER) Wrapper<User> lqw);
}
