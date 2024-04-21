<template>
  <div id="album">
    <!-- 活动相册列表 -->
    <div class="album_list">
      <div
        class="list_list"
        v-for="i in album_list"
        :key="i.albumId"
        @click="goPicture(i)"
      >
        <!-- 活动图片 -->
        <img :src="i.albumHeaderPath" alt="" />
        <div class="list_bottom">
          <div class="list_bottom_name">活动名称</div>

          <div class="list_bottom_price">活动时间:{{ i.time.timeContent }}</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import storage from "../../utils/storage.js";
export default {
  name: "Album",
  data() {
    return {
      user: [],
      album_list: [],
    };
  },
  mounted() {
    this.user = storage.get("user");
    // 获取相册列表
    this.getAlbum();
  },
  methods: {
    // 获取相册列表
    async getAlbum() {
      await this.$http
        .post(`/my/album?user_id=${this.user.userId}`)
        .then((res) => {
          console.log(res);
          this.album_list = res.data.data;
        });
    },
    goPicture(i) {
      console.log(i);
      this.$router.push({
        path: "/picture",
        query: {
          name: i.activityName,
          time: i.time.timeContent,
          header_path: i.albumHeaderPath,
          album_id: i.albumId,
        },
      });
    },
  },
};
</script>

<style scoped>
/* 活动列表 */
.album_list {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  background-color: rgb(248, 248, 248);
}

.album_list .list_list {
  margin-top: 10px;
  width: 48%;
  background-color: rgb(255, 255, 255);
}
.album_list .list_list img {
  width: 100%;
  height: 130rpx;
}
.album_list .list_list .list_bottom .list_bottom_name {
  display: -webkit-box;
  -webkit-box-orient: vertical;
  text-overflow: ellipsis;
  overflow: hidden;
  -webkit-line-clamp: 2;
  font-weight: 600;
}

.album_list .list_list .list_bottom .list_bottom_price {
  font-size: 16px;
  padding-bottom: 5px;
  color: red;
}
</style>
