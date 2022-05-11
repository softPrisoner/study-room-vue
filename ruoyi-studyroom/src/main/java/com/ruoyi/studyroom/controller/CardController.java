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
import com.ruoyi.studyroom.domain.vo.CardVo;
import com.ruoyi.studyroom.domain.bo.CardBo;
import com.ruoyi.studyroom.service.ICardService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 优惠卡Controller
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Validated
@Api(value = "优惠卡控制器", tags = {"优惠卡管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/store/card")
public class CardController extends BaseController {

    private final ICardService iCardService;

    /**
     * 查询优惠卡列表
     */
    @ApiOperation("查询优惠卡列表")
    @SaCheckPermission("store:card:list")
    @GetMapping("/list")
    public TableDataInfo<CardVo> list(@Validated(QueryGroup.class) CardBo bo, PageQuery pageQuery) {
        return iCardService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出优惠卡列表
     */
    @ApiOperation("导出优惠卡列表")
    @SaCheckPermission("store:card:export")
    @Log(title = "优惠卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated CardBo bo, HttpServletResponse response) {
        List<CardVo> list = iCardService.queryList(bo);
        ExcelUtil.exportExcel(list, "优惠卡", CardVo.class, response);
    }

    /**
     * 获取优惠卡详细信息
     */
    @ApiOperation("获取优惠卡详细信息")
    @SaCheckPermission("store:card:query")
    @GetMapping("/{id}")
    public R<CardVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return R.ok(iCardService.queryById(id));
    }

    /**
     * 新增优惠卡
     */
    @ApiOperation("新增优惠卡")
    @SaCheckPermission("store:card:add")
    @Log(title = "优惠卡", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CardBo bo) {
        return toAjax(iCardService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改优惠卡
     */
    @ApiOperation("修改优惠卡")
    @SaCheckPermission("store:card:edit")
    @Log(title = "优惠卡", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CardBo bo) {
        return toAjax(iCardService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除优惠卡
     */
    @ApiOperation("删除优惠卡")
    @SaCheckPermission("store:card:remove")
    @Log(title = "优惠卡", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iCardService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
