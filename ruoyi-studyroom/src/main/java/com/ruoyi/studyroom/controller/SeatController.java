package com.ruoyi.studyroom.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import com.ruoyi.studyroom.domain.bo.RoomSeatBo;
import com.ruoyi.studyroom.service.IRoomSeatService;
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
import com.ruoyi.studyroom.domain.vo.SeatVo;
import com.ruoyi.studyroom.domain.bo.SeatBo;
import com.ruoyi.studyroom.service.ISeatService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 座位Controller
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Validated
@Api(value = "座位控制器", tags = {"座位管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/store/seat")
public class SeatController extends BaseController {

    private final ISeatService iSeatService;

    /**
     * 查询座位列表
     */
    @ApiOperation("查询座位列表")
    @SaCheckPermission("store:seat:list")
    @GetMapping("/list")
    public TableDataInfo<SeatVo> list(@Validated(QueryGroup.class) SeatBo bo, PageQuery pageQuery) {
        return iSeatService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出座位列表
     */
    @ApiOperation("导出座位列表")
    @SaCheckPermission("store:seat:export")
    @Log(title = "座位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated SeatBo bo, HttpServletResponse response) {
        List<SeatVo> list = iSeatService.queryList(bo);
        ExcelUtil.exportExcel(list, "座位", SeatVo.class, response);
    }

    /**
     * 获取座位详细信息
     */
    @ApiOperation("获取座位详细信息")
    @SaCheckPermission("store:seat:query")
    @GetMapping("/{seatId}")
    public R<SeatVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("seatId") Long seatId) {
        return R.ok(iSeatService.queryById(seatId));
    }

    /**
     * 新增座位
     */
    @ApiOperation("新增座位")
    @SaCheckPermission("store:seat:add")
    @Log(title = "座位", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SeatBo seatBo) {
        Boolean seat = iSeatService.insertByBo(seatBo);

        return toAjax(seat);
    }

    /**
     * 修改座位
     */
    @ApiOperation("修改座位")
    @SaCheckPermission("store:seat:edit")
    @Log(title = "座位", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SeatBo bo) {
        return toAjax(iSeatService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除座位
     */
    @ApiOperation("删除座位")
    @SaCheckPermission("store:seat:remove")
    @Log(title = "座位", businessType = BusinessType.DELETE)
    @DeleteMapping("/{seatIds}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] seatIds) {
        return toAjax(iSeatService.deleteWithValidByIds(Arrays.asList(seatIds), true) ? 1 : 0);
    }
}
