package com.ruoyi.studyroom.service;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.XcxLoginUser;
import com.ruoyi.common.core.service.LogininforService;
import com.ruoyi.common.enums.DeviceType;
import com.ruoyi.common.enums.WxUserStatus;
import com.ruoyi.common.exception.user.UserException;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.MessageUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.config.WxMaConfiguration;
import com.ruoyi.studyroom.domain.User;
import com.ruoyi.studyroom.domain.bo.UserBo;
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
    final WxMaService wxMaService = WxMaConfiguration.getMaService("wx1d73696716e9918c");

    public String wxLogin( String code) throws WxErrorException {
        HttpServletRequest request = ServletUtils.getRequest();
        // xcxCode 为 小程序调用 wx.login 授权后获取
        //
        // 校验 appid + appsrcret + xcxCode 调用登录凭证校验接口 获取 session_key 与 openid

        WxMaJscode2SessionResult result = wxMaService.jsCode2SessionInfo(code);
        String openid = result.getOpenid();

        User user = loadUserByOpenid(openid);

        // 此处可根据登录用户的数据不同 自行创建 loginUser
        XcxLoginUser loginUser = new XcxLoginUser();
        loginUser.setUserId(user.getUserId());
        loginUser.setUsername(user.getNickname());
        loginUser.setUserType("app_user");
        loginUser.setOpenid(openid);
        loginUser.setSessionKey(result.getSessionKey());
        // 生成token
        LoginHelper.loginByDevice(loginUser, DeviceType.XCX);

        asyncService.recordLogininfor(user.getNickname(), Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success"), request);

        return StpUtil.getTokenValue();
    }

    /**
     * 获取手机号码
     * @param code
     * @return
     * @throws WxErrorException
     */
    public WxMaPhoneNumberInfo getPhone(String code) throws WxErrorException {
        WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService().getNewPhoneNoInfo(code);
        String phoneNumber = phoneNoInfo.getPhoneNumber();
        User user = userService.selectUserByOpenid(LoginHelper.getWxLoginUser().getOpenid());
        user.setPhone(phoneNumber);
        userService.updateByBo(BeanUtil.toBean(user,UserBo.class));
        return phoneNoInfo;
    }

    public Boolean getUserInfo(String sessionKey, String encryptedData, String ivStr){
        WxMaUserInfo userInfo = wxMaService.getUserService().getUserInfo(sessionKey, encryptedData, ivStr);
        User user = userService.selectUserByOpenid(LoginHelper.getWxLoginUser().getOpenid());
        user.setNickname(userInfo.getNickName());
        user.setAvatarUrl(userInfo.getAvatarUrl());
        user.setSex(Integer.valueOf(userInfo.getGender()));
        if (user.getStatus()== null){
            user.setStatus(1);
        }

        return userService.updateByBo(BeanUtil.toBean(user,UserBo.class));
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

        } else if (WxUserStatus.DISABLE.getCode().equals(String.valueOf(user.getStatus()))) {
            log.info("登录用户：{} 已被停用.", openid);
            throw new UserException("user.blocked",user.getPhone());
        }
        return user;
    }

}
