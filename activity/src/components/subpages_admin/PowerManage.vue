<template>
  <div id="powermanage">
    <div class="header">
      <el-button type="primary" @click="addPower">添加权限</el-button>
    </div>
    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column
          prop="powerId"
          label="权限ID"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="powerName"
          label="权限名称"
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
              @click="editPower(scope.row)"
            ></el-button>
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="DeletePowerById(scope.row)"
            ></el-button>
            <el-button type="warning" @click="fpModule(scope.row)"
              >分配模块</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 分配模块弹框 -->
    <el-dialog title="分配模块" :visible.sync="dialogTableVisible">
      <div style="width: 80%; margin: 0 auto">
        <el-transfer
          :titles="titles"
          v-model="value"
          :data="data"
          @change="editmoduleidbypowerid"
        ></el-transfer>
      </div>
    </el-dialog>

    <!-- 添加权限弹窗 -->
    <el-dialog title="添加权限" :visible.sync="dialogFormVisible1">
      <el-form :model="form">
        <el-form-item label="权限名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible1 = false">取 消</el-button>
        <el-button type="primary" @click="addPowerBtn">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 编辑权限弹窗 -->
    <el-dialog title="编辑权限" :visible.sync="dialogFormVisible2">
      <el-form :model="form">
        <el-form-item label="权限名称" :label-width="formLabelWidth">
          <el-input v-model="form.id" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="权限名称" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取 消</el-button>
        <el-button type="primary" @click="editPowerBtn">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "PowerManage",
  data() {
    return {
      // 添加权限
      dialogFormVisible1: false,
      // 编辑权限
      dialogFormVisible2: false,
      formLabelWidth: "120px",
      form: {
        id: 0,
        name: "",
      },
      // 分配模块
      dialogTableVisible: false,
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
      data: [
        { key: 1, label: "1" },
        { key: 2, label: "2" },
        { key: 3, label: "3" },
        { key: 4, label: "4" },
        { key: 5, label: "1" },
        { key: 6, label: "2" },
        { key: 7, label: "3" },
        { key: 8, label: "4" },
        { key: 9, label: "1" },
        { key: 10, label: "2" },
        { key: 11, label: "3" },
        { key: 12, label: "4" },
        { key: 13, label: "1" },
        { key: 14, label: "2" },
        { key: 15, label: "3" },
      ],
      // data: generateData(),
      value: [],
      // 权限标题
      titles: ["不可访问模块", "可访问模块"],
    };
  },
  mounted() {
    // 获取模块列表
    this.getModuleList();
    //  获取权限列表
    this.getPowerList();
  },
  methods: {
    // 编辑权限
    editPower(i) {
      this.form.id = i.powerId;
      this.form.name = i.powerName;
      this.dialogFormVisible2 = true;
    },
    async editPowerBtn() {
      await this.$http
        .post(
          `/roleadmin/editpowerinfobyid?id=${this.form.id}&name=${this.form.name}`
        )
        .then((res) => {
          console.log(res);
          this.getPowerList();
          this.dialogFormVisible2 = false;
        });
    },
    // 添加权限
    addPower() {
      this.dialogFormVisible1 = true;
    },
    async addPowerBtn() {
      console.log(11);
      await this.$http
        .post(`/roleadmin/addpowerinfo?name=${this.form.name}`)
        .then((res) => {
          console.log(res);
          this.getPowerList();
          this.dialogFormVisible1 = false;
        });
    },
    // 点击分配按钮
    async fpModule(i) {
      this.form.id = i.powerId;
      console.log("点击的权限ID", i.powerId);
      this.value = []; //将value初始化[]
      // 查询当前权限有哪些模块
      await this.$http
        .post(`/roleadmin/getmodulebypowerid?power_id=${i.powerId}`)
        .then((res) => {
          console.log("查询到当前权限ID有哪些模块", res);
          this.getModuleList(); //获取平台所有模块
          for (let i = 0; i < res.data.data.length; i++) {
            // this.data[i].key = res.data.data[i].moduleId;
            // this.data[i].label = res.data.data[i].moduleName;
            this.value.push(res.data.data[i].moduleId);
          }
        });
      this.dialogTableVisible = true;
    },
    async editmoduleidbypowerid() {
      console.log(this.value);
      const formData = new FormData();
      formData.append("id", this.form.id);
      formData.append("module", this.value);
      await this.$http
        .post(`/roleadmin/editpowermodulelist`, formData, {
          Headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          console.log(res);
        });
    },
    // 获取权限列表
    async getPowerList() {
      await this.$http.get(`/roleadmin/getpowerlist`).then((res) => {
        console.log(res);
        this.tableData = res.data.data;
      });
    },
    // 获取模块列表
    async getModuleList() {
      await this.$http.get(`/roleadmin/getmodulelist`).then((res) => {
        console.log(res);
        this.data = res.data.data;
        for (let i = 0; i < res.data.data.length; i++) {
          this.data[i].key = res.data.data[i].moduleId;
          this.data[i].label = res.data.data[i].moduleName;
        }
      });
    },
    // 删除权限
    async DeletePowerById(i) {
      console.log(i);
      // 根据权限查询角色表中是否有绑定
      await this.$http
        .post(`/roleadmin/searchroleispowerid?power_id=${i.powerId}`)
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
                  .post(`/roleadmin/deletepowerbyid?power_id=${i.powerId}`)
                  .then((res) => {
                    console.log(res);
                    this.getPowerList();
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
