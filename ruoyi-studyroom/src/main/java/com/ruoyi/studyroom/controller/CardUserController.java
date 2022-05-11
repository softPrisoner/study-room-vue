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
import com.ruoyi.studyroom.domain.vo.CardUserVo;
import com.ruoyi.studyroom.domain.bo.CardUserBo;
import com.ruoyi.studyroom.service.ICardUserService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 优惠卡-会员关联Controller
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Validated
@Api(value = "优惠卡-会员关联控制器", tags = {"优惠卡-会员关联管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/studyroom/cardUser")
public class CardUserController extends BaseController {

    private final ICardUserService iCardUserService;

    /**
     * 查询优惠卡-会员关联列表
     */
    @ApiOperation("查询优惠卡-会员关联列表")
    @SaCheckPermission("studyroom:cardUser:list")
    @GetMapping("/list")
    public TableDataInfo<CardUserVo> list(@Validated(QueryGroup.class) CardUserBo bo, PageQuery pageQuery) {
        return iCardUserService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出优惠卡-会员关联列表
     */
    @ApiOperation("导出优惠卡-会员关联列表")
    @SaCheckPermission("studyroom:cardUser:export")
    @Log(title = "优惠卡-会员关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated CardUserBo bo, HttpServletResponse response) {
        List<CardUserVo> list = iCardUserService.queryList(bo);
        ExcelUtil.exportExcel(list, "优惠卡-会员关联", CardUserVo.class, response);
    }

    /**
     * 获取优惠卡-会员关联详细信息
     */
    @ApiOperation("获取优惠卡-会员关联详细信息")
    @SaCheckPermission("studyroom:cardUser:query")
    @GetMapping("/{id}")
    public R<CardUserVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return R.ok(iCardUserService.queryById(id));
    }

    /**
     * 新增优惠卡-会员关联
     */
    @ApiOperation("新增优惠卡-会员关联")
    @SaCheckPermission("studyroom:cardUser:add")
    @Log(title = "优惠卡-会员关联", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CardUserBo bo) {
        return toAjax(iCardUserService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改优惠卡-会员关联
     */
    @ApiOperation("修改优惠卡-会员关联")
    @SaCheckPermission("studyroom:cardUser:edit")
    @Log(title = "优惠卡-会员关联", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CardUserBo bo) {
        return toAjax(iCardUserService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除优惠卡-会员关联
     */
    @ApiOperation("删除优惠卡-会员关联")
    @SaCheckPermission("studyroom:cardUser:remove")
    @Log(title = "优惠卡-会员关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iCardUserService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
