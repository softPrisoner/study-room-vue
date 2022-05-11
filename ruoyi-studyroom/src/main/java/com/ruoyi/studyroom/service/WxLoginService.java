package com.ruoyi.studyroom.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.config.WxMaConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.XcxLoginUser;
import com.ruoyi.common.core.service.LogininforService;
import com.ruoyi.common.enums.DeviceType;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.enums.WxUserStatus;
import com.ruoyi.common.exception.user.UserException;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.config.WxMaConfiguration;
import com.ruoyi.studyroom.domain.User;
import com.ruoyi.studyroom.domain.bo.UserBo;
import com.ruoyi.studyroom.domain.vo.UserVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: study-room-vue
 * @Author: WenZhengcheng
 * @Date: 2022-5-9 下午 06:44
 * @Desc:
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class WxLoginService {

    private final IUserService userService;
    private final LogininforService asyncService;


    public String wxLogin(String appid, String code) throws WxErrorException {
        HttpServletRequest request = ServletUtils.getRequest();
        // xcxCode 为 小程序调用 wx.login 授权后获取
        // todo 以下自行实现
        // 校验 appid + appsrcret + xcxCode 调用登录凭证校验接口 获取 session_key 与 openid
        final WxMaService wxMaService = WxMaConfiguration.getMaService(appid);
        WxMaJscode2SessionResult result = wxMaService.jsCode2SessionInfo(code);
        String openid = result.getOpenid();

        User user = loadUserByOpenid(openid);

        // 此处可根据登录用户的数据不同 自行创建 loginUser
        XcxLoginUser loginUser = new XcxLoginUser();
        loginUser.setUserId(user.getUserId());
        loginUser.setUsername(user.getNickname());
        loginUser.setUserType("app_user");
        loginUser.setOpenid(openid);
        // 生成token
        LoginHelper.loginByDevice(loginUser, DeviceType.XCX);

        asyncService.recordLogininfor(user.getNickname(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"), request);

        return StpUtil.getTokenValue();
    }


    public void logout(String userName){
        asyncService.recordLogininfor(userName,Constants.LOGOUT,MessageUtils.message("user.logout.success"),ServletUtils.getRequest());
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId, String nickname) {
        UserBo bo = new UserBo();
        bo.setUserId(userId);
        bo.setNickname(nickname);
        userService.updateByBo(bo);
    }



    private User loadUserByOpenid(String openid) {
        // 使用 openid 查询绑定用户 如未绑定用户 则根据业务自行处理 例如 创建默认用户

        User user = userService.selectUserByOpenid(openid);
        if (ObjectUtil.isNull(user)) {
            log.info("登录用户：{} 不存在.", openid);
            user = new User();
            user.setOpenid(openid);
            UserBo bo = BeanUtil.toBean(user, UserBo.class);
            return userService.insertByOpenidAndBalance(bo);
            // todo 用户不存在 业务逻辑自行实现
        } else if (WxUserStatus.DISABLE.getCode().equals(String.valueOf(user.getStatus()))) {
            log.info("登录用户：{} 已被停用.", openid);
            throw new UserException("您的账号已被停用，请联系店家。");
            // todo 用户已被停用 业务逻辑自行实现
        }
        return user;
    }

}
