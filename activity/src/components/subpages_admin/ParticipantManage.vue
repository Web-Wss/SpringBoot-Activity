<template>
  <div id="participantmanage">
    <div class="header">
      <div class="left">
        <el-input v-model="input" placeholder="输入要搜索的参与者姓名"
          ><el-button
            slot="append"
            icon="el-icon-search"
            circle
            @click="searchParticipant"
          ></el-button
        ></el-input>
      </div>
      <div class="right">
        <el-button type="primary" @click="addParticipantBtn"
          >添加参与者</el-button
        >
      </div>
    </div>
    <!-- 内容 -->
    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column
          prop="participantId"
          label="参与者ID"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="userId"
          label="绑定的用户ID"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="participantName"
          label="参与者姓名"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="participantCard"
          label="参与者身份证"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="participantHeight"
          label="参与者身高"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="participantWeight"
          label="参与者体重"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="participantSex"
          label="参与者性别"
          width="180"
          align="center"
        >
        </el-table-column>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              type="primary"
              icon="el-icon-edit"
              circle
              @click="EditParticipantByIdBtn(scope.row)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="DeleteParticipantById(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 分页 -->
      <!-- <div class="fy">
        <el-pagination background layout="prev, pager, next" :total="1000">
        </el-pagination>
      </div> -->

      <!-- 添加参与者弹窗 -->
      <el-dialog title="添加参与者" :visible.sync="dialogFormVisible">
        <el-form :model="form">
          <el-form-item label="姓名" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="身份证号" :label-width="formLabelWidth">
            <el-input v-model="form.card" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="身高" :label-width="formLabelWidth">
            <el-input v-model="form.height" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="体重" :label-width="formLabelWidth">
            <el-input v-model="form.weight" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别" :label-width="formLabelWidth">
            <el-input v-model="form.sex" autocomplete="off"></el-input>
          </el-form-item>

          <!-- </el-form-item> -->
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="AddParticipant">确 定</el-button>
        </div>
      </el-dialog>

      <!-- 修改参与者弹窗 -->
      <el-dialog title="修改参与者" :visible.sync="dialogFormVisible1">
        <el-form :model="form">
          <el-form-item label="ID" :label-width="formLabelWidth">
            <el-input v-model="form.id" autocomplete="off" disabled></el-input>
          </el-form-item>
          <el-form-item label="姓名" :label-width="formLabelWidth">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="身份证号" :label-width="formLabelWidth">
            <el-input v-model="form.card" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="身高" :label-width="formLabelWidth">
            <el-input v-model="form.height" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="体重" :label-width="formLabelWidth">
            <el-input v-model="form.weight" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别" :label-width="formLabelWidth">
            <el-input v-model="form.sex" autocomplete="off"></el-input>
          </el-form-item>

          <!-- </el-form-item> -->
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible1 = false">取 消</el-button>
          <el-button type="primary" @click="EditParticipantById"
            >确 定</el-button
          >
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import store from "../../store/index";
import axios from "axios";
export default {
  name: "ParticipantManage",
  data() {
    return {
      input: "",
      // 添加参与者
      dialogFormVisible: false,
      dialogFormVisible1: false,
      formLabelWidth: "150px",
      form: {
        id: "",
        name: "",
        card: "",
        height: "",
        weight: "",
        sex: "",
      },
      // 内容
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄",
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄",
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄",
        },
      ],
    };
  },
  mounted() {
    // 获取参与者列表
    this.getParticipantList();
  },
  methods: {
    // 删除参与者
    async DeleteParticipantById(i) {
      console.log(i.userId);
      if (i.userId !== null) {
        this.$alert("需先在用户管理中解绑该参与者，才能删除", "删除失败提示", {
          confirmButtonText: "确定",
        });
      } else {
        // 发送删除请求
        this.$confirm("此操作将永久删除该条记录, 是否继续?", "提示", {
          confirmButtonText: "继续",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            this.$http
              .post(
                `/useradmin/deleteparticipantbyid?participant_id=${i.participantId}`
              )
              .then((res) => {
                console.log(res);
                this.getParticipantList();
              });
            this.$message({
              type: "success",
              message: "删除成功!",
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          });
      }
    },
    // 修改参与者
    EditParticipantByIdBtn(i) {
      this.form.id = i.participantId;
      this.form.name = i.participantName;
      this.form.height = i.participantHeight;
      this.form.weight = i.participantWeight;
      this.form.card = i.participantCard;
      this.form.sex = i.participantSex;
      this.dialogFormVisible1 = true;
    },
    async EditParticipantById() {
      const params = new FormData();
      params.append("id", this.form.id);
      params.append("name", this.form.name);
      params.append("card", this.form.card);
      params.append("height", this.form.height);
      params.append("weight", this.form.weight);
      params.append("sex", this.form.sex);
      let config = {
        headers: {
          // 添加请求头
          "Content-Type": "multipart/form-data",
          Authorization: store.getters.getToken,
        },
      };
      await this.$http
        .post(`/useradmin/editparticipantinfobyid`, params, config)
        .then((res) => {
          console.log(res);
          this.getParticipantList();
          this.dialogFormVisible1 = false;
        });
      console.log(1);
    },
    //添加参与者按钮
    addParticipantBtn() {
      this.dialogFormVisible = true;
    },
    async AddParticipant() {
      const params = new FormData();
      params.append("name", this.form.name);
      params.append("card", this.form.card);
      params.append("height", this.form.height);
      params.append("weight", this.form.weight);
      params.append("sex", this.form.sex);
      let config = {
        headers: {
          // 添加请求头
          "Content-Type": "multipart/form-data",
          Authorization: store.getters.getToken,
        },
      };
      await this.$http
        .post(`/useradmin/addparticipantinfo`, params, config)
        .then((res) => {
          console.log(res);
          this.getParticipantList();
          this.dialogFormVisible = false;
        });
    },
    // 搜索参与者
    async searchParticipant() {
      console.log(this.input);
      await this.$http
        .post(
          `/useradmin/getparticipantbyparticipantname?participant_name=${this.input}`
        )
        .then((res) => {
          console.log(res);
          this.tableData = res.data.data;
        });
    },
    // 获取参与者列表
    async getParticipantList() {
      await this.$http.get(`/useradmin/getparticipantlist`).then((res) => {
        console.log(res);
        this.tableData = res.data.data;
      });
    },
  },
};
</script>

<style scoped>
.header {
  display: flex;
  margin-bottom: 10px;
}
.header .left {
  width: 280px;
  margin-right: 10px;
}

.fy {
  text-align: center;
  margin: 10px auto;
}
</style>
