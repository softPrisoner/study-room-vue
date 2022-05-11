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
import com.ruoyi.studyroom.domain.vo.OrderVo;
import com.ruoyi.studyroom.domain.bo.OrderBo;
import com.ruoyi.studyroom.service.IOrderService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 订单Controller
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Validated
@Api(value = "订单控制器", tags = {"订单管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/order/order")
public class OrderController extends BaseController {

    private final IOrderService iOrderService;

    /**
     * 查询订单列表
     */
    @ApiOperation("查询订单列表")
    @SaCheckPermission("order:order:list")
    @GetMapping("/list")
    public TableDataInfo<OrderVo> list(@Validated(QueryGroup.class) OrderBo bo, PageQuery pageQuery) {
        return iOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出订单列表
     */
    @ApiOperation("导出订单列表")
    @SaCheckPermission("order:order:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated OrderBo bo, HttpServletResponse response) {
        List<OrderVo> list = iOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "订单", OrderVo.class, response);
    }

    /**
     * 获取订单详细信息
     */
    @ApiOperation("获取订单详细信息")
    @SaCheckPermission("order:order:query")
    @GetMapping("/{id}")
    public R<OrderVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return R.ok(iOrderService.queryById(id));
    }

    /**
     * 新增订单
     */
    @ApiOperation("新增订单")
    @SaCheckPermission("order:order:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OrderBo bo) {
        return toAjax(iOrderService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改订单
     */
    @ApiOperation("修改订单")
    @SaCheckPermission("order:order:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OrderBo bo) {
        return toAjax(iOrderService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除订单
     */
    @ApiOperation("删除订单")
    @SaCheckPermission("order:order:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iOrderService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
