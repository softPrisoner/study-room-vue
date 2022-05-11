package com.ruoyi.framework.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * wxpay pay properties.
 *
 * @author Binary Wang
 */
@Data
@ConfigurationProperties(prefix = "wx.pay")
public class WxPayProperties {
    /**
     * 设置微信公众号或者小程序等的appid
     */
    private String appId;

    /**
     * 微信支付商户号
     */
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;

    private String apiV3Key;

    /**
     * apiclient_key.pem文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    private String privateKeyPath;


    /**
     * apiclient_cert.pem文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    private String privateCertPath;

    /**
     * 回调地址
     */
    private String notifyUrl;



}
