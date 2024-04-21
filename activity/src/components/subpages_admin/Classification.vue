<template>
  <div id="classification">
    <el-button type="primary" @click="add">+ 新增分类</el-button>

    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column
          prop="classificationId"
          label="ID"
          width="200"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="classificationName"
          label="分类名称"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="classificationName"
          label="分类描述"
          min-width="180"
          align="center"
        >
        </el-table-column>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              type="primary"
              @click="edit(scope.row)"
              icon="el-icon-edit"
              circle
            ></el-button>
            <el-button
              type="danger"
              @click="deletea(scope.row)"
              icon="el-icon-delete"
              circle
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <!-- 分页 -->
    <!-- <div class="fy">
      <el-pagination background layout="prev, pager, next" :total="1000">
      </el-pagination>
    </div> -->

    <!-- 添加活动分类弹窗 -->
    <el-dialog title="新增活动分类" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="活动分类名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="活动分类描述">
          <el-input v-model="form.describe" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addClassificationBtn"
          >确 定</el-button
        >
      </div>
    </el-dialog>

    <!-- 修改弹窗 -->
    <el-dialog title="修改分类信息" :visible.sync="dialogFormVisible1">
      <el-form :model="form1">
        <el-form-item label="ID">
          <el-input v-model="form1.id" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="分类名称">
          <el-input v-model="form1.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="分类描述">
          <el-input v-model="form1.describe" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="editBtn()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  neme: "Classfication",
  data() {
    return {
      input: "",
      // 新增对话框
      dialogFormVisible: false,
      // 修改对话框
      dialogFormVisible1: false,

      form: {
        name: "",
        describe: "",
      },
      form1: {
        id: "",
        name: "",
        describe: "",
      },

      tableData: [],
    };
  },
  mounted() {
    // 获取活动分类列表
    this.getClassificationList();
  },
  methods: {
    // 获取活动分类列表
    async getClassificationList() {
      await this.$http
        .get(`/activityadmin/getclassificationlist`)
        .then((res) => {
          console.log(res);
          this.tableData = res.data.data;
        });
    },
    // 删除按钮
    async deletea(i) {
      this.$confirm(
        `此操作将永久删除ID为${i.classificationId}的这条数据, 是否继续?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          this.$http
            .post(
              `/activityadmin/deleteclassification?classification_id=${i.classificationId}`
            )
            .then((res) => {
              console.log(res);
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.getClassificationList();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    // 编辑按钮
    async edit(i) {
      console.log(i);
      this.dialogFormVisible1 = true;
      this.form1.id = i.classificationId;
    },
    // 确认修改
    async editBtn() {
      // 发送修改请求
      await this.$http
        .post(
          `/activityadmin/editclassification?classification_id=${this.form1.id}&classification_name=${this.form1.name}&classification_explain=${this.form1.describe}`
        )
        .then((res) => {
          console.log(res);
          this.getClassificationList();
          this.dialogFormVisible1 = false;
        });
    },

    // 新增分类按钮
    add() {
      this.dialogFormVisible = true;
    },
    // 发送添加分类请求
    async addClassificationBtn() {
      this.dialogFormVisible = false;
      await this.$http
        .post(
          `/activityadmin/insertclassification?classification_name=${this.form.name}&classification_explain=${this.form.describe}`
        )
        .then((res) => {
          console.log(res);
          this.getClassificationList();
        });
    },
  },
};
</script>

<style scoped>
.header {
  width: 100%;
  height: 40px;
  line-height: 40px;
  /* background-color: aqua; */
  display: flex;
}
.header .left {
  display: flex;
  width: 80%;
}

.input {
  width: 300px;
  margin-right: 10px;
}
.header .right {
  flex: 1;
  /* background-color: bisque; */
  text-align: right;
}

.content {
  margin-top: 10px;
}

.fy {
  text-align: center;
  margin: 10px auto;
}
</style>
