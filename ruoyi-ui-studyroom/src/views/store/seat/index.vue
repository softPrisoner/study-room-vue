<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input v-model="roomName" placeholder="请输入自习室名称" clearable size="small" prefix-icon="el-icon-search"
            style="margin-bottom: 20px" />
        </div>
        <div class="head-container">
          <el-tree :data="roomList" :props="defaultProps" :expand-on-click-node="false" :filter-node-method="filterNode"
            ref="tree" default-expand-all @node-click="handleNodeClick" :highlight-current="true" />
        </div>
      </el-col>

      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
          label-width="68px">
          <el-form-item label="座位号" prop="seatNum">
            <el-input v-model="queryParams.seatNum" placeholder="请输入座位号" clearable @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item label="座位区域" prop="seatArea">
            <el-select v-model="queryParams.seatArea" placeholder="请选择座位区域" clearable>
              <el-option v-for="dict in dict.type.area_status" :key="dict.value" :label="dict.label"
                :value="dict.value" />
            </el-select>
          </el-form-item>
          <el-form-item label="区域名字" prop="areaName">
            <el-input v-model="queryParams.areaName" placeholder="请输入区域名字" clearable
              @keyup.enter.native="handleQuery" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
              v-hasPermi="['store:seat:add']">新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
              v-hasPermi="['store:seat:edit']">修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
              v-hasPermi="['store:seat:remove']">删除</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
              v-hasPermi="['store:seat:export']">导出</el-button>
          </el-col>
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="seatList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" align="center" />
          <el-table-column label="座位ID" align="center" prop="seatId" :show-overflow-tooltip="true" v-if="false" />
          <el-table-column label="座位号" align="center" prop="seatNum" min-width="100" :show-overflow-tooltip="true" />
          <el-table-column label="座位区域" align="center" prop="seatArea">
            <template slot-scope="scope">
              <dict-tag :options="dict.type.area_status" :value="scope.row.seatArea" />
            </template>
          </el-table-column>
          <el-table-column label="区域名字" align="center" prop="areaName" min-width="100" :show-overflow-tooltip="true" />
          <el-table-column label="操作" fixed="right" width="120" align="center" class-name="small-padding fixed-width">
            <template slot-scope="scope">
              <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                v-hasPermi="['store:seat:edit']">修改</el-button>
              <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                v-hasPermi="['store:seat:remove']">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
          @pagination="getList" />
      </el-col>
    </el-row>


    <!-- 添加或修改座位对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="座位号" prop="seatNum">
          <el-input v-model="form.seatNum" placeholder="请输入座位号" />
        </el-form-item>
        <el-form-item label="座位区域">
          <el-radio-group v-model="form.seatArea">
            <el-radio v-for="dict in dict.type.area_status" :key="dict.value" :label="parseInt(dict.value)">
              {{dict.label}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="区域名字" prop="areaName">
          <el-input v-model="form.areaName" placeholder="请输入区域名字" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    listSeat,
    getSeat,
    delSeat,
    addSeat,
    updateSeat
  } from "@/api/store/seat";
  import {
    listRoom,
    getRoom,
    delRoom,
    addRoom,
    updateRoom
  } from "@/api/store/room";
  import {
    listRoomSeat,
    getRoomSeat,
    delRoomSeat,
    addRoomSeat,
    updateRoomSeat
  } from "@/api/studyroom/roomSeat";
  export default {
    name: "Seat",
    dicts: ['area_status'],
    data() {
      return {
        // 按钮loading
        buttonLoading: false,
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 座位表格数据
        seatList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          seatNum: undefined,
          seatArea: undefined,
          areaName: undefined,
        },
        roomId: undefined,
        roomName: "",
        roomList: undefined,
        defaultProps: {
          label: "roomName"
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          seatId: [{
            required: true,
            message: "座位ID不能为空",
            trigger: "blur"
          }],
          seatNum: [{
            required: true,
            message: "座位号不能为空",
            trigger: "blur"
          }],
          seatArea: [{
            required: true,
            message: "座位区域不能为空",
            trigger: "blur"
          }],
          areaName: [{
            required: true,
            message: "区域名字不能为空",
            trigger: "blur"
          }],
        }
      };
    },
    watch: {
      // 根据名称筛选部门树
      roomName(val) {
        this.$refs.tree.filter(val);
      }
    },
    created() {
      this.getRoomList()
      this.getList();
    },
    methods: {
      /** 查询座位列表 */
      getList() {
        this.loading = true;
        listSeat(this.queryParams).then(response => {
          this.seatList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      getRoomList() {
        listRoom(this.roomName).then(response => {
          this.roomList = response.rows

        })
      },
      filterNode(value, data) {
        if (!value) return true;
        return data.roomName.indexOf(value) !== -1;
      },
      handleNodeClick(data) {
        this.roomId = data.roomId;
        this.queryParams.roomId = data.roomId;
        this.handleQuery();
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          seatId: undefined,
          seatNum: undefined,
          seatArea: 0,
          areaName: undefined,
          createBy: undefined,
          createTime: undefined,
          updateBy: undefined,
          updateTime: undefined
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.seatId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加座位";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.loading = true;
        this.reset();
        const seatId = row.seatId || this.ids
        getSeat(seatId).then(response => {
          this.loading = false;
          this.form = response.data;
          this.open = true;
          this.title = "修改座位";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.buttonLoading = true;
            if (this.form.seatId != null) {
              updateSeat(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }).finally(() => {
                this.buttonLoading = false;
              });
            } else {
              this.form.roomId = this.roomId
              addSeat(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }).finally(() => {
                this.buttonLoading = false;
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const seatIds = row.seatId || this.ids;
        this.$modal.confirm('是否确认删除座位编号为"' + seatIds + '"的数据项？').then(() => {
          this.loading = true;
          return delSeat(seatIds);
        }).then(() => {
          this.loading = false;
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).finally(() => {
          this.loading = false;
        });
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('store/seat/export', {
          ...this.queryParams
        }, `seat_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
