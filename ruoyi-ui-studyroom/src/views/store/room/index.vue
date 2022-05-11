<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名字" prop="roomName">
        <el-input v-model="queryParams.roomName" placeholder="请输入自习室名字" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="地址" prop="roomAddr">
        <el-input v-model="queryParams.roomAddr" placeholder="请输入自习室地址" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="联系电话" prop="phone">
        <el-input v-model="queryParams.phone" placeholder="请输入自习室联系电话" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['store:room:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['store:room:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['store:room:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['store:room:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="roomList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自习室ID" align="center" prop="roomId" :show-overflow-tooltip="true" v-if="false" />
      <el-table-column label="自习室名字" align="center" prop="roomName" min-width="100" :show-overflow-tooltip="true" />
      <el-table-column label="自习室地址" align="center" prop="roomAddr" min-width="100" :show-overflow-tooltip="true" />
      <el-table-column label="自习室联系电话" align="center" prop="phone" min-width="100" :show-overflow-tooltip="true" />
      <el-table-column label="操作" fixed="right" width="120" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['store:room:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['store:room:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改自习室对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名字" prop="roomName">
          <el-input v-model="form.roomName" placeholder="请输入自习室名字" />
        </el-form-item>
        <el-form-item label="地址" prop="roomAddr">
          <el-input v-model="form.roomAddr" placeholder="请输入自习室地址" />
        </el-form-item>
        <el-form-item label="联系电话" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入自习室联系电话" />
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
    listRoom,
    getRoom,
    delRoom,
    addRoom,
    updateRoom
  } from "@/api/store/room";

  export default {
    name: "Room",
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
        // 自习室表格数据
        roomList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          roomName: undefined,
          roomAddr: undefined,
          phone: undefined,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          roomId: [{
            required: true,
            message: "自习室ID不能为空",
            trigger: "blur"
          }],
          roomName: [{
            required: true,
            message: "自习室名字不能为空",
            trigger: "blur"
          }],
          roomAddr: [{
            required: true,
            message: "自习室地址不能为空",
            trigger: "blur"
          }],
          phone: [{
            required: true,
            message: "自习室联系电话不能为空",
            trigger: "blur"
          }],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询自习室列表 */
      getList() {
        this.loading = true;
        listRoom(this.queryParams).then(response => {
          this.roomList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          roomId: undefined,
          roomName: undefined,
          roomAddr: undefined,
          phone: undefined,
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
        this.ids = selection.map(item => item.roomId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加自习室";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.loading = true;
        this.reset();
        const roomId = row.roomId || this.ids
        getRoom(roomId).then(response => {
          this.loading = false;
          this.form = response.data;
          this.open = true;
          this.title = "修改自习室";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.buttonLoading = true;
            if (this.form.roomId != null) {
              updateRoom(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }).finally(() => {
                this.buttonLoading = false;
              });
            } else {
              addRoom(this.form).then(response => {
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
        const roomIds = row.roomId || this.ids;
        this.$modal.confirm('是否确认删除自习室编号为"' + roomIds + '"的数据项？').then(() => {
          this.loading = true;
          return delRoom(roomIds);
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
        this.download('store/room/export', {
          ...this.queryParams
        }, `room_${new Date().getTime()}.xlsx`)
      }
    }
  };
</script>
