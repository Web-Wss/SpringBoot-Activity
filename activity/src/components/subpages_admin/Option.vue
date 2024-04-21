<template>
  <div id="option">
    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column
          prop="optionId"
          label="反馈id"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="userId"
          label="用户id"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="optionContent"
          label="反馈内容"
          min-width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="optionTime"
          label="反馈时间"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button
              type="danger"
              icon="el-icon-delete"
              circle
              @click="deleteoption(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "Option",
  data() {
    return {
      tableData: [
        {
          optionId: "1",
          userId: "1",
          optionContent: "意见反馈内容1",
          optionTime: "2023-03-27 15:54:55",
        },
        {
          optionId: "2",
          userId: "1",
          optionContent: "意见反馈内容2",
          optionTime: "2023-03-27 15:54:55",
        },
        {
          optionId: "3",
          userId: "2",
          optionContent: "意见反馈内容3",
          optionTime: "2023-03-27 15:54:55",
        },
      ],
    };
  },
  mounted() {
    // 获取意见反馈列表
    this.getOptionList();
  },
  methods: {
    // 删除意见反馈
    async deleteoption(i) {
      this.$confirm(
        `此操作将永久删除ID为${i.optionId}的这条数据, 是否继续?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          this.$http
            .post(`/dataadmin/deleteoption?id=${i.optionId}`)
            .then((res) => {
              console.log(res);
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              console.log(res);
              this.getOptionList();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });

      // await this.$http
      //   .post(`/dataadmin/deleteoption?id=${i.optionId}`)
      //   .then((res) => {
      //     console.log(res);
      //     this.getOptionList();
      //   });
    },
    // 获取意见反馈列表
    async getOptionList() {
      await this.$http.get(`/dataadmin/getoptionlist`).then((res) => {
        console.log(res);
        this.tableData = res.data.data;
      });
    },
  },
};
</script>

<style></style>
