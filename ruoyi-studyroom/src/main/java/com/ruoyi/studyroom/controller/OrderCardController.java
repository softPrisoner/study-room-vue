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
import com.ruoyi.studyroom.domain.vo.OrderCardVo;
import com.ruoyi.studyroom.domain.bo.OrderCardBo;
import com.ruoyi.studyroom.service.IOrderCardService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 购卡管理Controller
 *
 * @author P_Peaceful
 * @date 2022-05-10
 */
@Validated
@Api(value = "购卡管理控制器", tags = {"购卡管理管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/order/orderCard")
public class OrderCardController extends BaseController {

    private final IOrderCardService iOrderCardService;

    /**
     * 查询购卡管理列表
     */
    @ApiOperation("查询购卡管理列表")
    @SaCheckPermission("order:orderCard:list")
    @GetMapping("/list")
    public TableDataInfo<OrderCardVo> list(@Validated(QueryGroup.class) OrderCardBo bo, PageQuery pageQuery) {
        return iOrderCardService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出购卡管理列表
     */
    @ApiOperation("导出购卡管理列表")
    @SaCheckPermission("order:orderCard:export")
    @Log(title = "购卡管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated OrderCardBo bo, HttpServletResponse response) {
        List<OrderCardVo> list = iOrderCardService.queryList(bo);
        ExcelUtil.exportExcel(list, "购卡管理", OrderCardVo.class, response);
    }

    /**
     * 获取购卡管理详细信息
     */
    @ApiOperation("获取购卡管理详细信息")
    @SaCheckPermission("order:orderCard:query")
    @GetMapping("/{id}")
    public R<OrderCardVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return R.ok(iOrderCardService.queryById(id));
    }

    /**
     * 新增购卡管理
     */
    @ApiOperation("新增购卡管理")
    @SaCheckPermission("order:orderCard:add")
    @Log(title = "购卡管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody OrderCardBo bo) {
        return toAjax(iOrderCardService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改购卡管理
     */
    @ApiOperation("修改购卡管理")
    @SaCheckPermission("order:orderCard:edit")
    @Log(title = "购卡管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody OrderCardBo bo) {
        return toAjax(iOrderCardService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除购卡管理
     */
    @ApiOperation("删除购卡管理")
    @SaCheckPermission("order:orderCard:remove")
    @Log(title = "购卡管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iOrderCardService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
