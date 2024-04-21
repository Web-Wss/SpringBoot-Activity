<template>
  <div id="mycollection">
    <div class="list" v-for="i in collectionList" :key="i.collectionId">
      <div class="img">
        <img :src="i.headerList[0].headerPath" alt="" />
      </div>
      <div class="nr">
        <div class="left">
          <div class="name">{{ i.activity.activityName }}</div>
          <div class="data">{{ i.createTime }}</div>
        </div>
        <div class="right">
          <div class="btn" @click="deleteCollection(i)">移除收藏</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import storage from "../../utils/storage";
export default {
  name: "MyCollection",
  data() {
    return {
      // 用户
      user: [],
      // 收藏列表
      collectionList: [],
    };
  },
  mounted() {
    this.user = storage.get("user");
    // 获取我的收藏
    this.getMyCollectionList();
  },
  methods: {
    // 删除收藏
    async deleteCollection(i) {
      console.log(i);
      await this.$http
        .post(`/my/deletecollection?collection_id=${i.collectionId}`)
        .then((res) => {
          console.log(res);
          this.getMyCollectionList();
        });
    },
    // 获取我的收藏
    async getMyCollectionList() {
      await this.$http
        .post(`/my/collection?user_id=${this.user.userId}`)
        .then((res) => {
          console.log(res);
          this.collectionList = res.data.data;
        });
    },
  },
};
</script>

<style scoped>
.list {
  width: 100%;
  height: 250px;
  position: relative;
  margin-bottom: 10px;
  /* background-color: rgb(35, 213, 64); */
}
.list .img {
  width: 100%;
  height: 250px;
  overflow: hidden;
  position: relative;
  /* background-size: cover; */
}
.list img {
  position: absolute;
  max-width: 100%;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
}
.list .nr {
  /* background-color: aqua; */
  color: white;
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 80px;
  display: flex;
}
.list .nr .left {
  /* background-color: bisque; */
  width: 70%;
  padding-left: 10px;
}
.list .nr .left .name {
  font-size: 20px;
  line-height: 40px;
}
.list .nr .left .data {
  font-size: 14px;
}
.list .nr .right {
  /* background-color: aquamarine; */
  font-size: 14px;
  flex: 1;
}
.list .nr .right .btn {
  margin: 20px auto;
  border-radius: 10px;
  width: 70px;
  text-align: center;
  line-height: 30px;
  height: 30px;
  border: 1px solid rgb(255, 255, 255);
}
</style>
