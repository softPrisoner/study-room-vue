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
import com.ruoyi.studyroom.domain.vo.BalanceVo;
import com.ruoyi.studyroom.domain.bo.BalanceBo;
import com.ruoyi.studyroom.service.IBalanceService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 余额
Controller
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Validated
@Api(value = "余额控制器", tags = {"余额管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/studyroom/balance")
public class BalanceController extends BaseController {

    private final IBalanceService iBalanceService;

    /**
     * 查询余额
列表
     */
    @ApiOperation("查询余额列表")
    @SaCheckPermission("studyroom:balance:list")
    @GetMapping("/list")
    public TableDataInfo<BalanceVo> list(@Validated(QueryGroup.class) BalanceBo bo, PageQuery pageQuery) {
        return iBalanceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出余额
列表
     */
    @ApiOperation("导出余额列表")
    @SaCheckPermission("studyroom:balance:export")
    @Log(title = "余额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated BalanceBo bo, HttpServletResponse response) {
        List<BalanceVo> list = iBalanceService.queryList(bo);
        ExcelUtil.exportExcel(list, "余额", BalanceVo.class, response);
    }

    /**
     * 获取余额
详细信息
     */
    @ApiOperation("获取余额详细信息")
    @SaCheckPermission("studyroom:balance:query")
    @GetMapping("/{id}")
    public R<BalanceVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return R.ok(iBalanceService.queryById(id));
    }

    /**
     * 新增余额

     */
    @ApiOperation("新增余额")
    @SaCheckPermission("studyroom:balance:add")
    @Log(title = "余额", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BalanceBo bo) {
        return toAjax(iBalanceService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改余额

     */
    @ApiOperation("修改余额")
    @SaCheckPermission("studyroom:balance:edit")
    @Log(title = "余额", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BalanceBo bo) {
        return toAjax(iBalanceService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除余额

     */
    @ApiOperation("删除余额")
    @SaCheckPermission("studyroom:balance:remove")
    @Log(title = "余额", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iBalanceService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
