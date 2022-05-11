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
import com.ruoyi.studyroom.domain.vo.RecordVo;
import com.ruoyi.studyroom.domain.bo.RecordBo;
import com.ruoyi.studyroom.service.IRecordService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 学习记录Controller
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Validated
@Api(value = "学习记录控制器", tags = {"学习记录管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/studyroom/record")
public class RecordController extends BaseController {

    private final IRecordService iRecordService;

    /**
     * 查询学习记录列表
     */
    @ApiOperation("查询学习记录列表")
    @SaCheckPermission("studyroom:record:list")
    @GetMapping("/list")
    public TableDataInfo<RecordVo> list(@Validated(QueryGroup.class) RecordBo bo, PageQuery pageQuery) {
        return iRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出学习记录列表
     */
    @ApiOperation("导出学习记录列表")
    @SaCheckPermission("studyroom:record:export")
    @Log(title = "学习记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated RecordBo bo, HttpServletResponse response) {
        List<RecordVo> list = iRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "学习记录", RecordVo.class, response);
    }

    /**
     * 获取学习记录详细信息
     */
    @ApiOperation("获取学习记录详细信息")
    @SaCheckPermission("studyroom:record:query")
    @GetMapping("/{id}")
    public R<RecordVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return R.ok(iRecordService.queryById(id));
    }

    /**
     * 新增学习记录
     */
    @ApiOperation("新增学习记录")
    @SaCheckPermission("studyroom:record:add")
    @Log(title = "学习记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody RecordBo bo) {
        return toAjax(iRecordService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改学习记录
     */
    @ApiOperation("修改学习记录")
    @SaCheckPermission("studyroom:record:edit")
    @Log(title = "学习记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody RecordBo bo) {
        return toAjax(iRecordService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除学习记录
     */
    @ApiOperation("删除学习记录")
    @SaCheckPermission("studyroom:record:remove")
    @Log(title = "学习记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iRecordService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
