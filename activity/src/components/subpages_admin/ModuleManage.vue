<template>
  <div id="modulemanage">
    <div class="header">
      <!-- <el-button type="primary">添加模块</el-button> -->
    </div>
    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column
          prop="moduleId"
          label="模块ID"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="moduleName"
          label="模块名称"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="modulePath"
          label="模块路径"
          min-width="50"
          align="center"
        >
        </el-table-column>

        <!-- <el-table-column label="操作" align="center">
          <el-button type="primary" icon="el-icon-edit" circle></el-button>
          <el-button type="danger" icon="el-icon-delete" circle></el-button>
        </el-table-column> -->
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModuleManage",
  data() {
    return {
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
    // 获取模块列表
    this.getModuleList();
  },
  methods: {
    // 获取模块列表
    async getModuleList() {
      await this.$http.get(`/roleadmin/getmodulelist`).then((res) => {
        console.log(res);
        this.tableData = res.data.data;
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
