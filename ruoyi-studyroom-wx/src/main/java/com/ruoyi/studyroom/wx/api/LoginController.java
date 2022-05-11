package com.ruoyi.studyroom.wx.api;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.studyroom.service.WxLoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/wx/")
public class LoginController {

    private final WxLoginService loginService;


    @ApiOperation("登录")
    @PostMapping("login")
    public R<Object> login(
            @ApiParam("微信小程序appid") @NotBlank(message = "{xcx.appid.not.blank}") String appid,
            @ApiParam("微信用户临时登陆code") @NotBlank(message = "{xcx.code.not.blank}") String code){
        Map<String,Object> ajax = new HashMap<>(1);
        String token = null;
        try {
            token = loginService.wxLogin(appid, code);
            ajax.put("token", token);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return R.ok(ajax);
    }

    @ApiOperation("退出")
    @GetMapping("/logout")
    public R<Object> logout() {
        try {
            StpUtil.logout();
            loginService.logout(LoginHelper.getLoginUser().getLoginId());
        } catch (NotLoginException e) {
            return R.fail(e.getMessage());
        }
        return R.ok();
    }
}



