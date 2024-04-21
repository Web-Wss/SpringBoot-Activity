<template>
  <div id="staffmanage">
    <div class="header">
      <div class="left">
        <el-input v-model="input" placeholder="输入要搜索的员工"
          ><el-button
            slot="append"
            icon="el-icon-search"
            circle
            @click="searchStaff"
          ></el-button
        ></el-input>
      </div>
      <div class="right">
        <el-button type="primary" @click="addstaff">添加员工</el-button>
      </div>
    </div>
    <!-- 内容 -->
    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column
          prop="staffId"
          label="职工ID"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="staffName"
          label="姓名"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="photo"
          label="职工头像"
          width="80"
          align="center"
        >
          <template slot-scope="scope">
            <img :src="scope.row.staffAvatar" alt="" />
          </template>
        </el-table-column>
        <el-table-column
          prop="staffCard"
          label="身份证号"
          width="180"
          align="center"
        ></el-table-column>

        <el-table-column
          prop="staffPhone"
          label="手机号"
          width="180"
          align="center"
        ></el-table-column>

        <el-table-column
          prop="roleId"
          label="角色ID"
          width="180"
          align="center"
        ></el-table-column>

        <el-table-column
          prop="staffStatus"
          label="员工状态"
          width="180"
          align="center"
        >
          <template slot-scope="scope">
            <el-switch
              v-model="scope.row.staffStatus"
              active-color="#13ce66"
              inactive-color="#ff4949"
              @change="StatusBtn(scope.row)"
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
              @click="editStaffBtn(scope.row)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="deleteStaffBtn(scope.row)"
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

    <!-- 添加员工弹窗 -->
    <el-dialog title="添加员工" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.staffName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="职工头像" :label-width="formLabelWidth">
          <van-uploader v-model="fileList" multiple :max-count="1" />
        </el-form-item>
        <el-form-item label="身份证号" :label-width="formLabelWidth">
          <el-input v-model="form.staffCard" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input v-model="form.staffPhone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户描述" :label-width="formLabelWidth">
          <el-input v-model="form.ms" autocomplete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item :label-width="formLabelWidth"> -->
        <el-form-item label="角色选择" :label-width="formLabelWidth">
          <el-select v-model="form.roleId" placeholder="请选择">
            <el-option
              v-for="item in roleList"
              :key="item.roleId"
              :label="item.roleName"
              :value="item.roleId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!-- </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addStaffInfo">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改员工弹窗 -->
    <el-dialog title="修改员工信息" :visible.sync="dialogFormVisible1">
      <el-form :model="form">
        <el-form-item label="ID" :label-width="formLabelWidth">
          <el-input v-model="form.id" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.staffName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="职工头像" :label-width="formLabelWidth">
          <van-uploader v-model="fileList" multiple :max-count="1" />
        </el-form-item>
        <el-form-item label="身份证号" :label-width="formLabelWidth">
          <el-input v-model="form.staffCard" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="form.staffPhone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户描述" :label-width="formLabelWidth">
          <el-input v-model="form.ms" autocomplete="off"></el-input>
        </el-form-item>
        <!-- <el-form-item :label-width="formLabelWidth"> -->
        <el-form-item label="角色选择" :label-width="formLabelWidth">
          <el-select v-model="form.roleId" placeholder="请选择">
            <el-option
              v-for="item in roleList"
              :key="item.roleId"
              :label="item.roleName"
              :value="item.roleId"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <!-- </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="EditStaffInfoById">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import store from "../../store/index";
import axios from "axios";
import { Notify } from "vant";
import { Dialog } from "vant";
export default {
  name: "StaffManage",
  data() {
    return {
      input: "",
      // 添加职工
      dialogFormVisible: false,
      dialogFormVisible1: false,
      formLabelWidth: "150px",
      fileList: [
        { url: "http://localhost:8080/images/staff_avatar/github.png" },
        // Uploader 根据文件后缀来判断是否为图片文件
        // 如果图片 URL 中不包含类型信息，可以添加 isImage 标记来声明
        // { url: "https://cloud-image", isImage: true },
      ],
      oldavatar: "",
      roleList: [],
      form: {
        id: 0,
        staffName: "",
        staffCard: "",
        staffPhone: "",
        roleId: "",
        ms: "",
        password: "",
      },
      // 内容
      tableData: [
        // {
        //   date: "2016-05-02",
        //   name: "王小虎",
        //   address: "上海市普陀区金沙江路 1518 弄",
        // },
        // {
        //   date: "2016-05-04",
        //   name: "王小虎",
        //   address: "上海市普陀区金沙江路 1517 弄",
        // },
        // {
        //   date: "2016-05-01",
        //   name: "王小虎",
        //   address: "上海市普陀区金沙江路 1519 弄",
        // },
        // {
        //   date: "2016-05-03",
        //   name: "王小虎",
        //   address: "上海市普陀区金沙江路 1516 弄",
        // },
      ],
    };
  },
  mounted() {
    // 获取职工列表
    this.getStaffList();
    // 获取角色列表
    this.getRoleList();
  },
  methods: {
    // 修改职工信息
    editStaffBtn(i) {
      this.form.id = i.staffId;
      this.form.staffName = i.staffName;
      this.form.staffCard = i.staffCard;
      this.form.staffPhone = i.staffPhone;
      this.form.roleId = i.roleId;
      this.form.ms = i.staffInfo;
      this.fileList[0].url = i.staffAvatar;
      const old = i.staffAvatar;
      const sp = old.lastIndexOf("/");
      const olda = old.slice(sp + 1);
      console.log(olda);
      this.oldavatar = olda;
      this.dialogFormVisible1 = true;
    },
    EditStaffInfoById() {
      let params = new FormData();
      params.append("id", this.form.id);
      params.append("name", this.form.staffName);
      params.append("card", this.form.staffCard);
      params.append("phone", this.form.staffPhone);
      params.append("oldavatar", this.oldavatar);
      params.append("ms", this.form.ms);
      params.append("role", this.form.roleId);
      let config = {
        headers: {
          // 添加请求头
          "Content-Type": "multipart/form-data",
          Authorization: store.getters.getToken,
        },
      };
      this.fileList.forEach((item, i) => {
        params.append("uploadFile", item.file);
      });
      axios
        .post("/api/useradmin/editstaffinfobyid", params, config)
        .then((res) => {
          console.log(res);
          this.fileList = [];
          this.show = false;
          this.getStaffList();
          this.dialogFormVisible1 = false;
          // Dialog.alert({
          //   title: "提示",
          //   message: "用户头像添加成功",
          // }).then(() => {
          //   // on close
          // });
          if (res.status == 200) {
            resolve(res.data);
          } else {
            reject(res.data.data);
          }
        })
        .catch((err) => {
          Toast.file("系统异常");
          reject(err);
        });
    },
    // 添加职工信息
    addstaff() {
      this.dialogFormVisible = true;
    },
    async addStaffInfo() {
      let params = new FormData();
      params.append("name", this.form.staffName);
      params.append("card", this.form.staffCard);
      params.append("phone", this.form.staffPhone);
      params.append("ms", this.form.ms);
      params.append("role", this.form.roleId);
      params.append("password", this.form.password);
      let config = {
        headers: {
          // 添加请求头
          "Content-Type": "multipart/form-data",
          Authorization: store.getters.getToken,
        },
      };
      this.fileList.forEach((item, i) => {
        params.append("uploadFile", item.file);
      });
      new Promise((resolve, reject) => {
        axios
          .post("/api/useradmin/addstaffinfo", params, config)
          .then((res) => {
            console.log(res);
            this.fileList = [];
            this.show = false;
            Dialog.alert({
              title: "提示",
              message: "用户头像添加成功",
            }).then(() => {
              // on close
            });
            if (res.status == 200) {
              resolve(res.data);
            } else {
              reject(res.data.data);
            }
          })
          .catch((err) => {
            Toast.file("系统异常");
            reject(err);
          });
      });
    },
    //获取角色列表
    async getRoleList() {
      console.log(1);
      await this.$http.get(`/useradmin/getrolelist`).then((res) => {
        console.log(res);
        this.roleList = res.data.data;
      });
    },
    //

    // 搜索员工
    async searchStaff() {
      console.log(this.input);
      await this.$http
        .post(`/useradmin/getstaffbyname?staff_name=${this.input}`)
        .then((res) => {
          console.log(res);
          for (let i = 0; i < res.data.data.length; i++) {
            if (res.data.data[i].staffStatus == 1) {
              res.data.data[i].staffStatus = true;
            } else {
              res.data.data[i].staffStatus = false;
            }
          }
          this.tableData = res.data.data;
        });
    },
    // 获取职工列表
    async getStaffList() {
      await this.$http.get(`/useradmin/getstafflist`).then((res) => {
        console.log(res);
        for (let i = 0; i < res.data.data.length; i++) {
          if (res.data.data[i].staffStatus == 1) {
            res.data.data[i].staffStatus = true;
          } else {
            res.data.data[i].staffStatus = false;
          }
        }
        this.tableData = res.data.data;
      });
    },
    // 删除职工
    async deleteStaffBtn(i) {
      console.log(i);
      await this.$http
        .post(
          `/useradmin/deletestaffinfobyid?staff_id=${i.staffId}&avatar=${i.staffAvatar}`
        )
        .then((res) => {
          console.log(res);
          this.getStaffList();
        });
    },
    // 修改职工状态
    async StatusBtn(i) {
      console.log(i);
      if (i.staffStatus == true) {
        console.log("开启状态");
        await this.$http
          .post(`/useradmin/editstaffstatus?staff_id=${i.staffId}&status=${1}`)
          .then((res) => {
            console.log(res);
          });
      } else {
        console.log("关闭状态");
        await this.$http
          .post(`/useradmin/editstaffstatus?staff_id=${i.staffId}&status=${0}`)
          .then((res) => {
            console.log(res);
          });
      }
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

/* 用户头像上传 */
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
/*  */
</style>
