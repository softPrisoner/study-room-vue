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
import com.ruoyi.studyroom.domain.vo.UserVo;
import com.ruoyi.studyroom.domain.bo.UserBo;
import com.ruoyi.studyroom.service.IUserService;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiOperation;

/**
 * 会员Controller
 *
 * @author P_Peaceful
 * @date 2022-05-09
 */
@Validated
@Api(value = "会员控制器", tags = {"会员管理"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/user")
public class UserController extends BaseController {

    private final IUserService iUserService;

    /**
     * 查询会员列表
     */
    @ApiOperation("查询会员列表")
    @SaCheckPermission("member:user:list")
    @GetMapping("/list")
    public TableDataInfo<UserVo> list(@Validated(QueryGroup.class) UserBo bo, PageQuery pageQuery) {
        return iUserService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会员列表
     */
    @ApiOperation("导出会员列表")
    @SaCheckPermission("member:user:export")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@Validated UserBo bo, HttpServletResponse response) {
        List<UserVo> list = iUserService.queryList(bo);
        ExcelUtil.exportExcel(list, "会员", UserVo.class, response);
    }

    /**
     * 获取会员详细信息
     */
    @ApiOperation("获取会员详细信息")
    @SaCheckPermission("member:user:query")
    @GetMapping("/{userId}")
    public R<UserVo> getInfo(@ApiParam("主键")
                                                  @NotNull(message = "主键不能为空")
                                                  @PathVariable("userId") Long userId) {
        return R.ok(iUserService.queryById(userId));
    }

    /**
     * 新增会员
     */
    @ApiOperation("新增会员")
    @SaCheckPermission("member:user:add")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody UserBo bo) {
        return toAjax(iUserService.insertByBo(bo) ? 1 : 0);
    }

    /**
     * 修改会员
     */
    @ApiOperation("修改会员")
    @SaCheckPermission("member:user:edit")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@RequestBody UserBo bo) {
        return toAjax(iUserService.updateByBo(bo) ? 1 : 0);
    }

    /**
     * 删除会员
     */
    @ApiOperation("删除会员")
    @SaCheckPermission("member:user:remove")
    @Log(title = "会员", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public R<Void> remove(@ApiParam("主键串")
                                       @NotEmpty(message = "主键不能为空")
                                       @PathVariable Long[] userIds) {
        return toAjax(iUserService.deleteWithValidByIds(Arrays.asList(userIds), true) ? 1 : 0);
    }
}
