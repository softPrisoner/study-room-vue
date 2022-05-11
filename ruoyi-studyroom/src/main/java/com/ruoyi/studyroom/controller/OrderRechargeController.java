package com.ruoyi.studyroom.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.studyroom.domain.vo.OrderRechargeVo;
import com.ruoyi.studyroom.domain.bo.OrderRechargeBo;
import com.ruoyi.studyroom.service.IOrderRechargeService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 充值订单Controller
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Validated
@Api(value = "充值订单控制器", tags = {"充值订单管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/order/recharge")
public class OrderRechargeController extends BaseController {

    private final IOrderRechargeService iOrderRechargeService;

    /**
     * 查询充值订单列表
     */
    @ApiOperation("查询充值订单列表")
    @SaCheckPermission("order:recharge:list")
    @GetMapping("/list")
    public TableDataInfo<OrderRechargeVo> list(@Validated(QueryGroup.class) OrderRechargeBo bo, PageQuery pageQuery) {
        return iOrderRechargeService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出充值订单列表
     */
    @ApiOperation("导出充值订单列表")
    @SaCheckPermission("order:recharge:export")
    @Log(title = "充值订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated OrderRechargeBo bo, HttpServletResponse response) {
        List<OrderRechargeVo> list = iOrderRechargeService.queryList(bo);
        ExcelUtil.exportExcel(list, "充值订单", OrderRechargeVo.class, response);
    }

    /**
     * 获取充值订单详细信息
     */
    @ApiOperation("获取充值订单详细信息")
    @SaCheckPermission("order:recharge:query")
    @GetMapping("/{id}")
    public R<OrderRechargeVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return R.ok(iOrderRechargeService.queryById(id));
    }

    /**
     * 新增充值订单
     */
    @ApiOperation("新增充值订单")
    @SaCheckPermission("order:recharge:add")
    @Log(title = "充值订单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OrderRechargeBo bo) {
        return toAjax(iOrderRechargeService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改充值订单
     */
    @ApiOperation("修改充值订单")
    @SaCheckPermission("order:recharge:edit")
    @Log(title = "充值订单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OrderRechargeBo bo) {
        return toAjax(iOrderRechargeService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除充值订单
     */
    @ApiOperation("删除充值订单")
    @SaCheckPermission("order:recharge:remove")
    @Log(title = "充值订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iOrderRechargeService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
