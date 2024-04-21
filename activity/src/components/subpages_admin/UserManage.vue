<template>
  <div id="usermanage">
    <div class="header">
      <div class="left">
        <el-input v-model="input" placeholder="输入要搜索的用户手机号"
          ><el-button
            slot="append"
            icon="el-icon-search"
            circle
            @click="getUserByPhone"
          ></el-button
        ></el-input>
      </div>
      <div class="right">
        <el-button type="primary" @click="addBtn">添加用户</el-button>
      </div>
    </div>
    <!-- 内容 -->
    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column prop="userId" label="ID" width="180" align="center">
        </el-table-column>
        <el-table-column
          prop="username"
          label="用户名"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="parentName"
          label="姓名"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="phone" label="手机号" width="180" align="center">
        </el-table-column>
        <el-table-column
          prop="participantId"
          label="参与者ID"
          width="180"
          align="center"
        >
        </el-table-column>

        <el-table-column prop="photo" label="照片" width="80" align="center">
          <template slot-scope="scope">
            <img :src="scope.row.userAvatar" alt="" />
          </template>
        </el-table-column>
        <el-table-column
          prop="userStatus"
          label="用户状态"
          width="180"
          align="center"
        >
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.userStatus"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="EditUserStatusBtn(scope.row)"
            >
            </el-switch>
          </template>
        </el-table-column>
        <el-table-column
          prop="userTime"
          label="注册时间"
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
              @click="editUserInfoBtn(scope.row)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="DeleteUserById(scope.row)"
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

    <!-- 添加用户信息 -->
    <el-dialog title="添加用户" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.parentName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addUserBtn">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改用户信息 -->
    <el-dialog title="修改用户" :visible.sync="dialogFormVisible1">
      <el-form :model="form">
        <el-form-item label="用户ID" :label-width="formLabelWidth">
          <el-input v-model="form.id" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.parentName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="选择要绑定的参与者" :label-width="formLabelWidth">
          <el-select
            v-model="form.participantList"
            multiple
            placeholder="请选择"
          >
            <el-option
              v-for="item in participantListList"
              :key="item.participantId"
              :label="item.participantName"
              :value="item.participantId"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="editUserInfo">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "UserManage",
  data() {
    return {
      // 添加用户
      dialogFormVisible: false,
      formLabelWidth: "160px",
      form: {
        id: "",
        username: "",
        password: "",
        parentName: "",
        phone: "",
        // 参与者
        participantList: [],
      },
      // 修改用户
      dialogFormVisible1: false,
      // oldparticipantList: "", //用户原来参与者
      // 未绑定用户的参与者列表
      participantListList: [{ participantId: 1, participantName: "参与者666" }],

      input: "",
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
    // 获取用户列表
    this.getUserList();
    // 获取未被绑定的参与者列表
    this.getNoBindParticipantList();
  },
  methods: {
    //
    async getNoBindParticipantList() {
      await this.$http
        .get(`/useradmin/getnobindparticipantlist`)
        .then((res) => {
          console.log(res);
          this.participantListList = res.data.data;
        });
    },
    // 修改用户信息
    async editUserInfoBtn(i) {
      this.participantListList = [];
      this.getNoBindParticipantList();
      console.log(i);
      this.form.id = i.userId;
      this.form.username = i.username;
      this.form.password = i.password;
      this.form.parentName = i.parentName;
      // this.oldparticipantList = i.participantId;
      this.form.phone = i.phone;
      this.dialogFormVisible1 = true;
      // 将用户当前的参与者id发送到后端查询信息，再赋值
      const formData = new FormData();
      formData.append("userId", i.userId);
      await this.$http
        .post(`/useradmin/searchparticipantinfobyid`, formData, {
          Headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          console.log(res);
          this.form.participantList = [];
          // this.participantListList = [];
          for (let i = 0; i < res.data.data.length; i++) {
            this.form.participantList.push(res.data.data[i].participantId);
            this.participantListList.push(res.data.data[i]);
          }
        });
    },
    async editUserInfo() {
      const formData = new FormData();
      formData.append("username", this.form.username);
      formData.append("userId", this.form.id);
      formData.append("password", this.form.password);
      formData.append("parentName", this.form.parentName);
      formData.append("phone", this.form.phone);
      formData.append("participantId", this.form.participantList);
      // formData.append("oldparticipantId", this.oldparticipantList);
      await this.$http
        .post(`/useradmin/edituserinfo`, formData, {
          Headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          console.log(res);
          this.getUserList();
          this.dialogFormVisible1 = false;
        });
    },
    // 添加用户
    addBtn() {
      this.dialogFormVisible = true;
    },
    async addUserBtn() {
      await this.$http
        .post(
          `/useradmin/adduserinfo?username=${this.form.username}&password=${this.form.password}&parentName=${this.form.parentName}&phone=${this.form.phone}`
        )
        .then((res) => {
          console.log(res);
          this.getUserList();
          this.dialogFormVisible = false;
        });
    },
    // 获取用户信息根据手机号
    async getUserByPhone() {
      console.log(this.input);
      await this.$http
        .post(`/useradmin/getuserlistbyphone?phone=${this.input}`)
        .then((res) => {
          console.log(res);
          for (let i = 0; i < res.data.data.length; i++) {
            if (res.data.data[i].userStatus == 1) {
              res.data.data[i].userStatus = true;
            } else {
              res.data.data[i].userStatus = false;
            }
          }
          this.tableData = res.data.data;
        });
    },
    // 获取用户列表
    async getUserList() {
      await this.$http.get(`/useradmin/getuserlist`).then((res) => {
        console.log(res);
        for (let i = 0; i < res.data.data.length; i++) {
          if (res.data.data[i].userStatus == 1) {
            res.data.data[i].userStatus = true;
          } else {
            res.data.data[i].userStatus = false;
          }
        }
        this.tableData = res.data.data;
      });
    },
    // 修改用户状态
    async EditUserStatusBtn(i) {
      console.log(i);
      if (i.userStatus == true) {
        console.log("开启状态");
        await this.$http
          .post(`/useradmin/edituserstatus?user_id=${i.userId}&status=${1}`)
          .then((res) => {
            console.log(res);
          });
      } else {
        console.log("关闭状态");
        await this.$http
          .post(`/useradmin/edituserstatus?user_id=${i.userId}&status=${0}`)
          .then((res) => {
            console.log(res);
          });
      }
    },
    // 删除用户
    DeleteUserById(i) {
      console.log(i);
      // 发送删除请求
      this.$confirm("此操作将永久删除该条记录, 是否继续?", "提示", {
        confirmButtonText: "继续",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .post(`/useradmin/deleteuserbyuserid?user_id=${i.userId}`)
            .then((res) => {
              console.log(res);
              this.getUserList();
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
img {
  width: 100%;
}
.fy {
  text-align: center;
  margin: 10px auto;
}
</style>
