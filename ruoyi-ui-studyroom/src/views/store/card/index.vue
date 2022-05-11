<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="优惠卡" prop="cardTitle">
        <el-input v-model="queryParams.cardTitle" placeholder="请输入优惠卡" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="价格" prop="price">
        <el-input v-model="queryParams.price" placeholder="请输入价格" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="可用区域" prop="usable">
        <el-select v-model="queryParams.usable" placeholder="请选择可用区域" clearable>
          <el-option v-for="dict in dict.type.area_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="次数(天)" prop="frequency">
        <el-input v-model="queryParams.frequency" placeholder="请输入次数(天)" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="有效期(天)" prop="term">
        <el-input v-model="queryParams.term" placeholder="请输入有效期(天)" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option v-for="dict in dict.type.common_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['store:card:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['store:card:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['store:card:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['store:card:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cardList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="id" :show-overflow-tooltip="true" v-if="false" />
      <el-table-column label="优惠卡" align="center" prop="cardTitle" min-width="100" :show-overflow-tooltip="true" />
      <el-table-column label="价格" align="center" prop="price" min-width="100" :show-overflow-tooltip="true" />
      <el-table-column label="可用区域" align="center" prop="usable">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.area_status" :value="scope.row.usable" />
        </template>
      </el-table-column>
      <el-table-column label="次数(天)" align="center" prop="frequency" min-width="100" :show-overflow-tooltip="true" />
      <el-table-column label="有效期(天)" align="center" prop="term" min-width="100" :show-overflow-tooltip="true" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.status" :active-value="1" :inactive-value="0" active-color="#13ce66"
            inactive-color="#ff4949" @change="handleStatusChange(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" fixed="right" width="120" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['store:card:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['store:card:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改优惠卡对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="优惠卡" prop="cardTitle">
          <el-input v-model="form.cardTitle" placeholder="请输入优惠卡名称" />
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入价格" />
        </el-form-item>
        <el-form-item label="可用区域">
          <el-radio-group v-model="form.usable">
            <el-radio v-for="dict in dict.type.area_status" :key="dict.value" :label="parseInt(dict.value)">
              {{dict.label}}
            </el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="次数(天)" prop="frequency">
          <el-input v-model="form.frequency" placeholder="请输入次数(天)" />
        </el-form-item>
        <el-form-item label="有效期(天)" prop="term">
          <el-input v-model="form.term" placeholder="请输入有效期(天)" />
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in dict.type.common_status" :key="dict.value" :label="parseInt(dict.value)">
              {{dict.label}}
            </el-radio>
          </el-radio-group>
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
    listCard,
    getCard,
    delCard,
    addCard,
    updateCard
  } from "@/api/store/card";

  export default {
    name: "Card",
    dicts: ['common_status', 'area_status'],
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
        // 优惠卡表格数据
        cardList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          cardTitle: undefined,
          price: undefined,
          usable: undefined,
          frequency: undefined,
          term: undefined,
          status: undefined,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          id: [{
            required: true,
            message: "主键ID不能为空",
            trigger: "blur"
          }],
          cardTitle: [{
            required: true,
            message: "优惠卡不能为空",
            trigger: "blur"
          }],
          price: [{
            required: true,
            message: "价格不能为空",
            trigger: "blur"
          }],
          usable: [{
            required: true,
            message: "可用区域不能为空",
            trigger: "blur"
          }],
          frequency: [{
            required: true,
            message: "次数(天)不能为空",
            trigger: "blur"
          }],
          term: [{
            required: true,
            message: "有效期(天)不能为空",
            trigger: "blur"
          }],
          status: [{
            required: true,
            message: "状态不能为空",
            trigger: "blur"
          }],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询优惠卡列表 */
      getList() {
        this.loading = true;
        listCard(this.queryParams).then(response => {
          this.cardList = response.rows;
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
          id: undefined,
          cardTitle: undefined,
          price: undefined,
          usable: 0,
          frequency: undefined,
          term: undefined,
          status: 0,
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
        this.ids = selection.map(item => item.id)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加优惠卡";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.loading = true;
        this.reset();
        const id = row.id || this.ids
        getCard(id).then(response => {
          this.loading = false;
          this.form = response.data;
          this.open = true;
          this.title = "修改优惠卡";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            this.buttonLoading = true;
            if (this.form.id != null) {
              updateCard(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }).finally(() => {
                this.buttonLoading = false;
              });
            } else {
              addCard(this.form).then(response => {
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
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除优惠卡编号为"' + ids + '"的数据项？').then(() => {
          this.loading = true;
          return delCard(ids);
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
        this.download('store/card/export', {
          ...this.queryParams
        }, `card_${new Date().getTime()}.xlsx`)
      },
      handleStatusChange(row) {
        let text = row.status === 1 ? "启用" : "停用";
        this.$modal.confirm('确认要 ' + text + ' 吗？').then(function() {
          return updateCard(row);
        }).then(() => {
          this.$modal.msgSuccess(text + "成功");
        }).catch(function() {
          row.status = row.status === 0 ? 1 : 0;
        });
      }
    }
  };
</script>
