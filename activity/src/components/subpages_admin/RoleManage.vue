<template>
  <div id="rolemanage">
    <div class="header">
      <el-button type="primary" @click="addRole()">添加角色</el-button>
    </div>
    <div class="content">
      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column
          prop="roleId"
          label="角色ID"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="roleName"
          label="角色名称"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="roleDescribe"
          label="角色描述"
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
              @click="editRoleInfo(scope.row)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="DeleteRoleById(scope.row)"
            ></el-button>
            &nbsp; 权限名称：
            <el-select
              v-model="tableData[scope.$index].power.powerName"
              placeholder="请选择权限名称"
              @change="
                editpowerinfo(
                  scope.row,
                  tableData[scope.$index].power.powerName
                )
              "
            >
              <el-option
                v-for="i in options"
                :key="i.powerId"
                :label="i.powerName"
                :value="i.powerId"
              >
              </el-option>
            </el-select>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 添加角色弹窗 -->
    <el-dialog title="添加角色" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="角色名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" :label-width="formLabelWidth">
          <el-input v-model="form.describe" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="权限选择" :label-width="formLabelWidth">
          <el-select v-model="form.powerId" placeholder="请选择权限名称">
            <el-option
              v-for="i in options"
              :key="i.powerId"
              :label="i.powerName"
              :value="i.powerId"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRoleBtn">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 修改角色弹窗 -->
    <el-dialog title="修改角色信息" :visible.sync="dialogFormVisible1">
      <el-form :model="form">
        <el-form-item label="角色ID" :label-width="formLabelWidth">
          <el-input v-model="form.id" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="角色名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" :label-width="formLabelWidth">
          <el-input v-model="form.describe" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="EditRoleBtn">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "RoleManage",
  data() {
    return {
      // 添加角色
      dialogFormVisible: false,
      dialogFormVisible1: false,
      formLabelWidth: "120px",
      form: {
        id: 0,
        name: "",
        describe: "",
        powerId: "",
      },
      // 权限
      options: [],
      value: "",
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
    // 获取角色列表
    this.getRoleList();
    // 获取权限列表
    this.getPowerList();
  },
  methods: {
    //修改power信息
    async editpowerinfo(i, j) {
      await this.$http
        .post(`/roleadmin/editrolepoweridinfo?roleId=${i.roleId}&powerId=${j}`)
        .then((res) => {
          console.log(res);
          this.getRoleList();
        });
    },
    // 修改角色信息
    editRoleInfo(i) {
      this.form.id = i.roleId;
      this.form.name = i.roleName;
      this.form.describe = i.roleDescribe;
      this.dialogFormVisible1 = true;
      console.log(i);
    },
    async EditRoleBtn() {
      console.log(this.form);
      await this.$http
        .post(
          `/roleadmin/editroleinfobyid?roleId=${this.form.id}&name=${this.form.name}&describe=${this.form.describe}`
        )
        .then((res) => {
          console.log(res);
          this.getRoleList();
          this.dialogFormVisible1 = false;
        });
    },
    // 添加角色
    addRole() {
      this.dialogFormVisible = true;
    },
    async addRoleBtn() {
      // 发送添加角色请求
      await this.$http
        .post(
          `/roleadmin/addroleinfo?name=${this.form.name}&describe=${this.form.describe}&powerId=${this.form.powerId}`
        )
        .then((res) => {
          console.log(res);
          this.getRoleList();
          this.dialogFormVisible = false;
        });
    },
    // 获取权限列表
    async getPowerList() {
      await this.$http.get(`/roleadmin/getpowerlist`).then((res) => {
        console.log(res);
        this.options = res.data.data;
      });
    },
    // 获取角色列表
    async getRoleList() {
      await this.$http.get(`/roleadmin/getrolelist`).then((res) => {
        console.log(res);
        this.tableData = res.data.data;
      });
    },
    // 删除用户
    async DeleteRoleById(i) {
      console.log(i);
      await this.$http
        .post(`/roleadmin/searchstaffandroleid?role_id=${i.roleId}`)
        .then((res) => {
          console.log(res);
          if (res.data.data != 0) {
            console.log("不能删除");
          } else {
            console.log("发送删除");
            // 发送删除请求
            this.$confirm("此操作将永久删除该条记录, 是否继续?", "提示", {
              confirmButtonText: "继续",
              cancelButtonText: "取消",
              type: "warning",
            })
              .then(() => {
                this.$http
                  .post(`/roleadmin/deleterolebyid?role_id=${i.roleId}`)
                  .then((res) => {
                    console.log(res);
                    this.getRoleList();
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
        });
    },
  },
};
</script>

<style scoped>
.header {
  margin-bottom: 10px;
}
</style>
