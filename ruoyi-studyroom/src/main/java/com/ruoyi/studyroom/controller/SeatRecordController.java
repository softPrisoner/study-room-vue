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
import com.ruoyi.studyroom.domain.vo.SeatRecordVo;
import com.ruoyi.studyroom.domain.bo.SeatRecordBo;
import com.ruoyi.studyroom.service.ISeatRecordService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 座位-记录Controller
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Validated
@Api(value = "座位-记录控制器", tags = {"座位-记录管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/studyroom/seatRecord")
public class SeatRecordController extends BaseController {

    private final ISeatRecordService iSeatRecordService;

    /**
     * 查询座位-记录列表
     */
    @ApiOperation("查询座位-记录列表")
    @SaCheckPermission("studyroom:seatRecord:list")
    @GetMapping("/list")
    public TableDataInfo<SeatRecordVo> list(@Validated(QueryGroup.class) SeatRecordBo bo, PageQuery pageQuery) {
        return iSeatRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出座位-记录列表
     */
    @ApiOperation("导出座位-记录列表")
    @SaCheckPermission("studyroom:seatRecord:export")
    @Log(title = "座位-记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated SeatRecordBo bo, HttpServletResponse response) {
        List<SeatRecordVo> list = iSeatRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "座位-记录", SeatRecordVo.class, response);
    }

    /**
     * 获取座位-记录详细信息
     */
    @ApiOperation("获取座位-记录详细信息")
    @SaCheckPermission("studyroom:seatRecord:query")
    @GetMapping("/{id}")
    public R<SeatRecordVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return R.ok(iSeatRecordService.queryById(id));
    }

    /**
     * 新增座位-记录
     */
    @ApiOperation("新增座位-记录")
    @SaCheckPermission("studyroom:seatRecord:add")
    @Log(title = "座位-记录", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SeatRecordBo bo) {
        return toAjax(iSeatRecordService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改座位-记录
     */
    @ApiOperation("修改座位-记录")
    @SaCheckPermission("studyroom:seatRecord:edit")
    @Log(title = "座位-记录", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SeatRecordBo bo) {
        return toAjax(iSeatRecordService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除座位-记录
     */
    @ApiOperation("删除座位-记录")
    @SaCheckPermission("studyroom:seatRecord:remove")
    @Log(title = "座位-记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iSeatRecordService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
