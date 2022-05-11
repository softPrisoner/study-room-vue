package com.ruoyi.studyroom.controller;

import java.util.List;
import java.util.Arrays;

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
import com.ruoyi.studyroom.domain.vo.RoomSeatVo;
import com.ruoyi.studyroom.domain.bo.RoomSeatBo;
import com.ruoyi.studyroom.service.IRoomSeatService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 自习室-座位关联Controller
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Validated
@Api(value = "自习室-座位关联控制器", tags = {"自习室-座位关联管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/studyroom/roomSeat")
public class RoomSeatController extends BaseController {

    private final IRoomSeatService iRoomSeatService;

    /**
     * 查询自习室-座位关联列表
     * @return
     */
    @ApiOperation("查询自习室-座位关联列表")
    @SaCheckPermission("studyroom:roomSeat:list")
    @GetMapping("/list")
    public TableDataInfo<RoomSeatVo> list(@Validated(QueryGroup.class) RoomSeatBo bo, PageQuery pageQuery) {
        return iRoomSeatService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出自习室-座位关联列表
     */
    @ApiOperation("导出自习室-座位关联列表")
    @SaCheckPermission("studyroom:roomSeat:export")
    @Log(title = "自习室-座位关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated RoomSeatBo bo, HttpServletResponse response) {
        List<RoomSeatVo> list = iRoomSeatService.queryList(bo);
        ExcelUtil.exportExcel(list, "自习室-座位关联", RoomSeatVo.class, response);
    }

    /**
     * 获取自习室-座位关联详细信息
     */
    @ApiOperation("获取自习室-座位关联详细信息")
    @SaCheckPermission("studyroom:roomSeat:query")
    @GetMapping("/{id}")
    public R<RoomSeatVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("id") Long id) {
        return R.ok(iRoomSeatService.queryById(id));
    }

    /**
     * 新增自习室-座位关联
     */
    @ApiOperation("新增自习室-座位关联")
    @SaCheckPermission("studyroom:roomSeat:add")
    @Log(title = "自习室-座位关联", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody RoomSeatBo bo) {
        return toAjax(iRoomSeatService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改自习室-座位关联
     */
    @ApiOperation("修改自习室-座位关联")
    @SaCheckPermission("studyroom:roomSeat:edit")
    @Log(title = "自习室-座位关联", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody RoomSeatBo bo) {
        return toAjax(iRoomSeatService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除自习室-座位关联
     */
    @ApiOperation("删除自习室-座位关联")
    @SaCheckPermission("studyroom:roomSeat:remove")
    @Log(title = "自习室-座位关联", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] ids) {
        return toAjax(iRoomSeatService.deleteWithValidByIds(Arrays.asList(ids), true) ? 1 : 0);
    }
}
