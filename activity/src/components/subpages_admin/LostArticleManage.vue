<template>
  <div id="lost_article_manage">
    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column
          prop="lostArticleId"
          label="失物招领id"
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
          prop="lostArticleName"
          label="物品名称"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="lostArticleDescribe"
          label="物品描述"
          min-width="120"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="optionTime"
          label="物品图片"
          width="180"
          align="center"
        >
          <template slot-scope="scope">
            <img :src="scope.row.lostArticleImg" alt="" style="width: 100%" />
          </template>
        </el-table-column>
        <el-table-column
          prop="publishTime"
          label="发布时间"
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
              @click="deleteLostArticle(scope.row)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "LostArticleManage",
  data() {
    return {
      tableData: [
        {
          lostArticleId: "1",
          userId: "1",
          lostArticleName: "物品名称",
          lostArticleDescribe:
            "物品描述物品描述物品描述物品描述物品描述物品描述物品描述",
          lostArticleImg:
            "http://localhost:8080/images/lost_article_img/1_36bd4eee-57a0-47a0-bef6-a91c52c278b60.jpg",
          publishTime: "2023-04-09 16:00:00",
        },
      ],
    };
  },
  mounted() {
    // 获取失物招领列表
    this.getLostArticleList();
  },
  methods: {
    // 删除失物招领
    async deleteLostArticle(i) {
      this.$confirm(
        `此操作将永久删除ID为${i.lostArticleId}的这条数据, 是否继续?`,
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
              `/dataadmin/deletelostarticlebyid?id=${i.lostArticleId}&img=${i.lostArticleImg}`
            )
            .then((res) => {
              console.log(res);
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.getLostArticleList();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
      console.log(i);
      // await this.$http
      //   .post(
      //     `/dataadmin/deletelostarticlebyid?id=${i.lostArticleId}&img=${i.lostArticleImg}`
      //   )
      //   .then((res) => {
      //     console.log(res);
      //     this.getLostArticleList();
      //   });
    },
    // 获取失物招领列表
    async getLostArticleList() {
      await this.$http.get(`/dataadmin/getlostarticlelist`).then((res) => {
        console.log(res);
        this.tableData = res.data.data;
      });
    },
  },
};
</script>

<style></style>
