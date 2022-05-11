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
import com.ruoyi.studyroom.domain.vo.RoomVo;
import com.ruoyi.studyroom.domain.bo.RoomBo;
import com.ruoyi.studyroom.service.IRoomService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 自习室Controller
 *
 * @author P_Peaceful
 * @date 2022-05-08
 */
@Validated
@Api(value = "自习室控制器", tags = {"自习室管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/store/room")
public class RoomController extends BaseController {

    private final IRoomService iRoomService;

    /**
     * 查询自习室列表
     */
    @ApiOperation("查询自习室列表")
    @SaCheckPermission("store:room:list")
    @GetMapping("/list")
    public TableDataInfo<RoomVo> list(@Validated(QueryGroup.class) RoomBo bo, PageQuery pageQuery) {
        return iRoomService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出自习室列表
     */
    @ApiOperation("导出自习室列表")
    @SaCheckPermission("store:room:export")
    @Log(title = "自习室", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated RoomBo bo, HttpServletResponse response) {
        List<RoomVo> list = iRoomService.queryList(bo);
        ExcelUtil.exportExcel(list, "自习室", RoomVo.class, response);
    }

    /**
     * 获取自习室详细信息
     */
    @ApiOperation("获取自习室详细信息")
    @SaCheckPermission("store:room:query")
    @GetMapping("/{roomId}")
    public R<RoomVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("roomId") Long roomId) {
        return R.ok(iRoomService.queryById(roomId));
    }

    /**
     * 新增自习室
     */
    @ApiOperation("新增自习室")
    @SaCheckPermission("store:room:add")
    @Log(title = "自习室", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody RoomBo bo) {
        return toAjax(iRoomService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改自习室
     */
    @ApiOperation("修改自习室")
    @SaCheckPermission("store:room:edit")
    @Log(title = "自习室", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody RoomBo bo) {
        return toAjax(iRoomService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除自习室
     */
    @ApiOperation("删除自习室")
    @SaCheckPermission("store:room:remove")
    @Log(title = "自习室", businessType = BusinessType.DELETE)
    @DeleteMapping("/{roomIds}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] roomIds) {
        return toAjax(iRoomService.deleteWithValidByIds(Arrays.asList(roomIds), true) ? 1 : 0);
    }
}
