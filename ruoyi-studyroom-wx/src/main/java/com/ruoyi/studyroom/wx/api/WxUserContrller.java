package com.ruoyi.studyroom.wx.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.PayConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.helper.LoginHelper;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.studyroom.domain.bo.OrderBo;
import com.ruoyi.studyroom.domain.bo.OrderRechargeBo;
import com.ruoyi.studyroom.domain.bo.RecordBo;
import com.ruoyi.studyroom.domain.bo.UserBo;
import com.ruoyi.studyroom.domain.vo.*;
import com.ruoyi.studyroom.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ProjectName: study-room-vue
 * @Author: WenZhengcheng
 * @Date: 2022-5-9 下午 08:15
 * @Desc:
 */

@Api(value = "微信用户信息管理器", tags = "微信用户信息")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wx/user")
public class WxUserContrller extends BaseController {

    private final IUserService userService;
    private final ICardUserService cardService;
    private final IBalanceService balanceService;
    private final IOrderService orderService;
    private final IOrderRechargeService rechargeService;
    private final IRecordService recordService;



    @ApiOperation("获取用户信息")
    @GetMapping
    public R<UserVo> user() {

        UserVo userVo = userService.queryById(LoginHelper.getLoginUser().getUserId());

        return R.ok(userVo);
    }

    @ApiOperation("更新用户信息")
    @PutMapping
    public R<Void> user(UserBo bo) {

        return toAjax(userService.updateByBo(bo));
    }

    @ApiOperation("获取用户拥有的的优惠卡")
    @GetMapping("/card")
    public R<Object> card() {

        return R.ok(cardService.queryListByUserId(LoginHelper.getLoginUser().getUserId()));
    }


    @ApiOperation("获取用户余额信息")
    @GetMapping("/balance")
    public R<BalanceVo> balance() {
        return R.ok(balanceService.queryByUserId(LoginHelper.getUserId()));
    }

    @ApiOperation("获取用户订单")
    @GetMapping("/order")
    public R<TableDataInfo<OrderVo>> order(OrderBo bo, PageQuery pageQuery) {
        bo.setUserId(LoginHelper.getUserId());
        return R.ok(orderService.queryPageList(bo, pageQuery));
    }

    @ApiOperation("获取用户已消费订单")
    @GetMapping("/order/paid")
    public R<TableDataInfo<OrderVo>> consumeOrder(OrderBo bo, PageQuery pageQuery) {
        bo.setUserId(LoginHelper.getUserId());
        bo.setPayStatus(PayConstants.CONSUMED);
        return R.ok(orderService.queryPageList(bo, pageQuery));
    }


    @ApiOperation("获取用户充值订单记录")
    @PostMapping("/recharge")
    public R<TableDataInfo<OrderRechargeVo>> recharge(OrderRechargeBo bo, PageQuery pageQuery) {
        bo.setUserId(LoginHelper.getUserId());
        return R.ok(rechargeService.queryPageList(bo, pageQuery));
    }

    @ApiOperation("更新学习记录排行榜")
    @PostMapping("/record")
    @Deprecated
    public R<Void> record(Double hours) {
        RecordVo vo = recordService.queryByUserId(LoginHelper.getUserId());

        RecordBo bo = BeanUtil.toBean(vo, RecordBo.class);
        bo.setHours(bo.getHours() + hours);
        return toAjax(recordService.updateByBo(bo));

    }

    @ApiOperation("获取学习记录排行榜")
    @GetMapping("/record")
    public R<List<RecordVo>> record() {
        if (!RedisUtils.hasKey(Constants.RECORD_RANK)) {
            List<RecordVo> list = recordService.queryDescList();
            RedisUtils.setCacheList(Constants.RECORD_RANK, list);
            RedisUtils.expire(Constants.RECORD_RANK,Constants.timeout);
            return R.ok(list);
        }
       return R.ok(RedisUtils.getCacheList(Constants.RECORD_RANK));
    }
}
