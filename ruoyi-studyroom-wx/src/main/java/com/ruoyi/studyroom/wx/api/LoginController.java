package com.ruoyi.studyroom.wx.api;

import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.http.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.exception.user.UserException;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.studyroom.service.WxLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: study-room-vue
 * @Author: WenZhengcheng
 * @Date: 2022-5-9 下午 06:37
 * @Desc:
 */

@Api(value = "微信小程序登陆控制器", tags = {"微信小程序登陆"})
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wx")
public class LoginController extends BaseController {

    private final WxLoginService loginService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public R<Object> login(
        @ApiParam("微信用户临时登陆code") @NotBlank(message = "{xcx.code.not.blank}")
        @RequestParam String code) {
        Map<String, Object> ajax = new HashMap<>(1);
        String token;
        try {
            token = loginService.wxLogin(code);
            ajax.put("token", token);
            return R.ok(ajax);
        } catch (UserException e) {
            return R.fail(Integer.parseInt(UserStatus.DISABLE.getCode()),e.getMessage());
        } catch (WxErrorException e){
            return R.fail();
        }
    }

    @ApiOperation(value = "更新用户手机号",
        notes = "调用此接口去更新用户手机号码")
    @PostMapping("/phone")
    public R<WxMaPhoneNumberInfo> phone(
        @ApiParam("动态令牌换取用户手机号") @NotBlank(message = "{xcx.code.not.blank}")
        @RequestParam String code) {
        try {
            WxMaPhoneNumberInfo phone = loginService.getPhone(code);
            return R.ok(phone);
        } catch (WxErrorException e) {
            return R.fail();
        }
    }

    @ApiOperation(value = "获取用户加密信息",
        notes = "调用此接口去更新用户信息只返回成功或失败，若要获取用户信息，请调用微信用户信息组下的相关接口")
    @PostMapping("/userInfo")
    public R<Void> userInfo(@ApiParam("用户加密信息") @RequestParam String encryptedData,
                            @ApiParam("加密算法的初始向量") @RequestParam String ivStr) {
        Boolean userInfo = loginService.getUserInfo(LoginHelper.getWxLoginUser().getSessionKey(), encryptedData, ivStr);

        return toAjax(userInfo);
    }


    @ApiOperation("退出")
    @GetMapping("/logout")
    public R<Object> logout() {
        StpUtil.logout();
        loginService.logout(LoginHelper.getLoginUser().getLoginId());

        return R.ok();
    }
}



