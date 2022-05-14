package com.ruoyi.studyroom.wx.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyV3Result;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.PayConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.OrderNumUtils;
import com.ruoyi.studyroom.domain.bo.OrderBo;
import com.ruoyi.studyroom.domain.bo.OrderCardBo;
import com.ruoyi.studyroom.domain.bo.OrderRechargeBo;
import com.ruoyi.studyroom.domain.bo.SeatRecordBo;
import com.ruoyi.studyroom.domain.vo.OrderCardVo;
import com.ruoyi.studyroom.domain.vo.OrderRechargeVo;
import com.ruoyi.studyroom.service.IOrderCardService;
import com.ruoyi.studyroom.service.IOrderRechargeService;
import com.ruoyi.studyroom.service.IOrderService;
import com.ruoyi.studyroom.service.ISeatRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * @ProjectName: study-room-vue
 * @Author: WenZhengcheng
 * @Date: 2022-5-9 下午 10:08
 * @Desc:
 */

@Api(value = "小程序支付控制器",tags = "小程序支付")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wx/pay")
public class PayController extends BaseController {

    private final WxPayService payService;
    private final IOrderCardService orderCardService;
    private final IOrderRechargeService rechargeService;
    private final IOrderService orderService;


    @ApiOperation("购买优惠卡统一下单，并组装所需支付参数")
    @PostMapping("/createOrder/card")
    public R<Object> payCard(@RequestBody OrderCardBo bo) {
        String orderNo = OrderNumUtils.getOrderNum();
        WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer();
        WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount();
        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
        bo.setUserId(LoginHelper.getUserId());
        bo.setOrderId(orderNo);
        bo.setPayStatus(PayConstants.UNPAID);

        amount.setTotal(Integer.parseInt(bo.getAmountTotal().multiply(Constants.MULTIPLE).toString()));
        request.setPayer(payer.setOpenid(LoginHelper.getWxLoginUser().getOpenid()));
        request.setAmount(amount);
        request.setDescription(bo.getDescription());
        request.setOutTradeNo(bo.getOrderId());
        request.setAttach(Constants.CARD);
        try {
            boolean flag = orderCardService.insertByBo(bo);
            if (!flag){
                return R.fail();
            }
            Object orderV3 = this.payService.createOrderV3(TradeTypeEnum.JSAPI, request);
            return R.ok(orderV3);
        } catch (WxPayException e) {
            return R.fail(e.getReturnMsg());
        }
    }


    @ApiOperation("充值余额统一下单，并组装所需支付参数")
    @PostMapping("/createOrder/recharge")
    public R<Object> payRecharge(@RequestBody OrderRechargeBo bo) {
        String orderNo = OrderNumUtils.getOrderNum();
        System.out.println(bo.getAmountTotal());
        WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer();
        WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount();
        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
        bo.setUserId(LoginHelper.getUserId());
        bo.setOrderId(orderNo);
        bo.setPayStatus(PayConstants.UNPAID);
        BigInteger total = bo.getAmountTotal().multiply(Constants.MULTIPLE).toBigInteger();
        amount.setTotal(total.intValue());

        request.setPayer(payer.setOpenid(LoginHelper.getWxLoginUser().getOpenid()));
        request.setAmount(amount);
        request.setDescription(bo.getDescription());
        request.setOutTradeNo(bo.getOrderId());
        request.setAttach(Constants.RECHARGE);
        try {
            boolean flag = rechargeService.insertByBo(bo);
            if (!flag){
                return R.fail();
            }
            Object orderV3 = this.payService.createOrderV3(TradeTypeEnum.JSAPI, request);
            return R.ok(orderV3);
        } catch (WxPayException e) {
            return R.fail("发生系统错误，请联系管理员");
        }
    }

    @ApiOperation(value = "支付回调通知处理")
    @PostMapping("/notify/order")
    public String parseOrderNotifyResult(@RequestBody String data) throws WxPayException {
        WxPayOrderNotifyV3Result v3Result = this.payService.parseOrderNotifyV3Result((String) data, null);
        String attach = v3Result.getResult().getAttach();
        String outTradeNo = v3Result.getResult().getOutTradeNo();
        //优惠卡充值订单回调
        System.out.println(attach);
        if (Constants.CARD.equals(attach)){
            OrderCardVo vo = orderCardService.queryByOrderId(outTradeNo);
            if (ObjectUtil.isNotNull(vo)){
                OrderCardBo bo = BeanUtil.toBean(vo, OrderCardBo.class);
                bo.setPayStatus(PayConstants.PAID);
                orderCardService.updateByBoAndCard(bo);
            }else {
                return WxPayNotifyResponse.fail("失败");
            }

        }
        //余额充值订单回调
        else if(Constants.RECHARGE.equals(attach)){
            OrderRechargeVo vo = rechargeService.queryByOrderId(outTradeNo);
            if (ObjectUtil.isNotNull(vo)){
                OrderRechargeBo bo = BeanUtil.toBean(vo, OrderRechargeBo.class);
                bo.setPayStatus(PayConstants.PAID);
                rechargeService.updateByBoAndBalance(bo);
            }else {
                WxPayNotifyResponse.fail("失败");
            }
        }

        return WxPayNotifyResponse.success("成功");
    }


    @ApiOperation("预定座位")
    @PostMapping("/order/seat")
    public R<Void> orderSeat(@RequestBody OrderBo orderBo){
        Date startTime = orderBo.getStartTime();
        DateTime now = DateUtil.date();
        //预定时间在当前时间之前，不符合逻辑直接失效
        if (startTime.before(now)){
            return R.fail("请选择正确的时间");
        }
        //插入订单表，支付状态为 未消费
        orderBo.setOrderId(OrderNumUtils.getOrderNum());
        orderBo.setUserId(LoginHelper.getUserId());
        orderBo.setPayStatus(PayConstants.NOT_CONSUMED);
        return toAjax(orderService.insertByBo(orderBo));
    }



    @ApiOperation("消费座位")
    @PutMapping("/order/seat/consumed")
    public R<Object> orderSeatConsumed(@RequestBody OrderBo orderBo){

        //更新订单表，支付状态为 已消费
        orderBo.setPayStatus(PayConstants.CONSUMED);
        orderBo.setUserId(LoginHelper.getUserId());
        try {
            orderService.updateByBoAndRank(orderBo);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.fail(e.getMessage());
        }

    }

}
