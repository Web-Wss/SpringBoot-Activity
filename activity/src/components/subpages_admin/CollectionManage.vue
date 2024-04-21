<template>
  <div id="collectionmanage">
    <el-table :data="tableData" stripe border style="width: 100%">
      <el-table-column
        prop="collectionId"
        label="收藏ID"
        width="180"
        align="center"
      >
      </el-table-column>
      <el-table-column
        prop="activityId"
        label="活动ID"
        width="180"
        align="center"
      >
      </el-table-column>
      <el-table-column prop="userId" label="用户ID" width="120" align="center">
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="收藏时间"
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
            @click="deleteCollection(scope.row)"
          ></el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: "CollectionManage",
  data() {
    return {
      tableData: [
        {
          collectionId: "1",
          activityId: "1",
          userId: "1",
          createTime: "2023-04-09 16:00:00",
        },
      ],
    };
  },
  mounted() {
    // 获取收藏列表
    this.getCollectionList();
  },
  methods: {
    // 获取收藏列表
    async getCollectionList() {
      await this.$http.get(`/useradmin/getcollectionlist`).then((res) => {
        console.log(res);
        this.tableData = res.data.data;
      });
    },
    // 删除收藏列表
    async deleteCollection(i) {
      console.log(i);
      await this.$http
        .post(`/useradmin/deletecollectionbyid?id=${i.collectionId}`)
        .then((res) => {
          console.log(res);
          this.getCollectionList();
        });
    },
  },
};
</script>

<style></style>
