package com.ruoyi.common.enums;

/**
 * @ProjectName: study-room-vue
 * @Author: WenZhengcheng
 * @Date: 2022-5-9 下午 07:40
 * @Desc:
 */

public enum WxUserStatus {
    //微信用户状态值
    DISABLE("0", "停用"),
    OK("1", "正常");

    private final String code;
    private final String info;

    WxUserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
