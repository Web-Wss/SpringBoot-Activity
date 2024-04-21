<template>
  <div id="date">
    <el-button type="primary" @click="add()">+ 新增活动时间</el-button>
    <div class="content">
      <el-table :data="tableData" stripe style="width: 100%" border>
        <el-table-column prop="timeId" label="ID" width="180" align="center">
        </el-table-column>
        <el-table-column
          prop="timeName"
          label="时间名称"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="timeContent"
          label="时间段"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="activityId"
          label="活动id"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="personSum"
          label="人数限制"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="isSignIn"
          label="是否开启签到"
          width="180"
          align="center"
        >
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.isSignIn"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="isSignInBtn(scope.row)"
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="editTimeBtn(scope.row)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="deletea(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 添加时间段弹框 -->
    <el-dialog title="添加活动时间" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="时间名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <!-- 时间段 -->
        <el-form-item label="时间日/段" :label-width="formLabelWidth">
          <div class="block">
            <el-date-picker
              v-model="form.date"
              type="daterange"
              placeholder="选择日期"
              format="yyyy 年 MM 月 dd 日"
              value-format="yyyy-MM-dd"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            >
            </el-date-picker>
          </div>
        </el-form-item>
        <el-form-item label="人数限制" :label-width="formLabelWidth">
          <el-input v-model="form.person_sum" autocomplete="off"></el-input>
        </el-form-item>
        <!--  -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addBtn">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改弹窗 -->
    <!-- 修改弹窗 -->
    <el-dialog title="修改分类信息" :visible.sync="dialogFormVisible1">
      <el-form :model="form1">
        <el-form-item label="ID">
          <el-input v-model="form1.id" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="时间名称">
          <el-input v-model="form1.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="人数限制">
          <el-input v-model="form1.sum" autocomplete="off"></el-input>
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
  name: "Date",
  data() {
    return {
      tableData: [
        // {
        //   timeId: 1,
        //   timeName: "活动时间名称",
        //   timeContent: "2023-07-01",
        //   activityId: 1,
        //   personSum: 50,
        //   isSignIn: 0,
        // },
        // {
        //   timeId: 2,
        //   timeName: "活动时间名称",
        //   timeContent: "2023-07-01",
        //   activityId: 1,
        //   personSum: 50,
        //   isSignIn: 0,
        // },
        // {
        //   timeId: 3,
        //   timeName: "活动时间名称",
        //   timeContent: "2023-07-01",
        //   activityId: 1,
        //   personSum: 50,
        //   isSignIn: 0,
        // },
      ],
      // 添加时间段表单
      dialogFormVisible: false,
      formLabelWidth: "120px",
      form: {
        name: "",
        date: "",
        person_sum: 0,
      },
      // 修改弹窗
      dialogFormVisible1: false,
      form1: {
        id: 0,
        name: "",
        sum: 0,
      },
    };
  },
  mounted() {
    // 获取时间列表
    this.getTimeList();
  },
  methods: {
    // 点击签到按钮
    async isSignInBtn(i) {
      console.log(i);
      if (i.isSignIn == true) {
        console.log("发送开启签到请求");
        await this.$http
          .post(`/activity/signin?timeId=${i.timeId}`)
          .then((res) => {
            console.log(res);
          });
      } else {
        console.log("发送关闭签到请求");
        await this.$http
          .post(`/activity/closeissignin?timeId=${i.timeId}`)
          .then((res) => {
            console.log(res);
          });
      }
    },
    // 删除按钮
    async deletea(i) {
      this.$confirm(
        `此操作将永久删除ID为${i.timeId}的这条数据, 是否继续?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          this.$http
            .post(`/activityadmin/deletetime?time_id=${i.timeId}`)
            .then((res) => {
              console.log(res);
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.getTimeList();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    // 点击修改按钮
    editTimeBtn(i) {
      console.log(i);
      this.form1.id = i.timeId;
      this.form1.name = i.timeName;
      this.form1.sum = i.personSum;
      this.dialogFormVisible1 = true;
    },
    async editBtn() {
      // 发送修改请求
      await this.$http
        .post(
          `/activityadmin/edittimeinfo?time_id=${this.form1.id}&time_name=${this.form1.name}&person_sum=${this.form1.sum}`
        )
        .then((res) => {
          console.log(res);
          this.getTimeList();
          this.dialogFormVisible1 = false;
        });
    },
    // 添加时间
    add() {
      this.dialogFormVisible = true;
    },
    async addBtn() {
      let date_date = "";
      this.dialogFormVisible = false;
      for (let i = 0; i < this.form.date.length; i++) {
        date_date = `${this.form.date[0]}~${this.form.date[1]}`;
      }
      await this.$http
        .post(
          `/activityadmin/inserttime?time_name=${this.form.name}&time_content=${date_date}&person_sum=${this.form.person_sum}`
        )
        .then((res) => {
          console.log(res);
          this.form.name = "";
          this.form.date = "";
          this.form.person_sum = 0;
          this.getTimeList();
          this.dialogFormVisible = false;
        });
    },

    // 获取时间列表
    async getTimeList() {
      await this.$http.get(`/activityadmin/gettimelist`).then((res) => {
        console.log(res);
        for (let i = 0; i < res.data.data.length; i++) {
          if (res.data.data[i].isSignIn == 1) {
            res.data.data[i].isSignIn = true;
          } else {
            res.data.data[i].isSignIn = false;
          }
        }
        this.tableData = res.data.data;
      });
    },
  },
};
</script>

<style scoped>
.content {
  margin-top: 10px;
}
</style>
