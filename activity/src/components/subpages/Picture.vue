<template>
  <div id="picture">
    <div class="header">
      <img :src="header_path" alt="" />
    </div>
    <div class="name">{{ name }}</div>
    <div class="sum">
      此相册共 <span>{{ picture_list.length }} </span>张照片
    </div>
    <div class="time">{{ time }}</div>
    <div class="picture_list">
      <div
        class="picture_list_list"
        v-for="(i, n) in picture_list"
        :key="i.pictureId"
      >
        <img :src="i.pictureImg" alt="" @click="showImg(i, n)" />
      </div>
    </div>
  </div>
</template>

<script>
import { ImagePreview } from "vant";
export default {
  name: "Picture",
  data() {
    return {
      header_path: "",
      album_id: 0,
      name: "",
      time: "",
      // 相册列表
      picture_list: [],
      // 展示图片开始
      startPosition: 1,
      // 相册列表，用于展示大图
      images: [],
    };
  },
  mounted() {
    this.header_path = this.$route.query.header_path;
    this.album_id = this.$route.query.album_id;
    this.name = this.$route.query.name;
    this.time = this.$route.query.time;
    // 获取相册中图片列表
    this.getPictureList();
  },
  methods: {
    // 点击图片
    showImg(i, n) {
      console.log(i);
      this.startPosition = n;
      ImagePreview({
        images: this.images,
        startPosition: this.startPosition,
      });
    },
    // 获取相册中图片列表
    async getPictureList() {
      await this.$http
        .post(`/my/picture?album_id=${this.album_id}`)
        .then((res) => {
          console.log(res);
          this.picture_list = res.data.data;
          for (let i = 0; i < res.data.data.length; i++) {
            this.images.push(res.data.data[i].pictureImg);
          }
        });
    },
  },
};
</script>

<style scoped>
.header {
  width: 100%;
}
.header img {
  width: 100%;
}

.name {
  font-size: 20px;
  color: rgb(100, 188, 176);
}
.sum {
  font-size: 16px;
}
.sum span {
  color: red;
}
.time {
  font-size: 18px;
  color: rgb(70, 51, 192);
}

/* 图片列表 */
.picture_list {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
}

.picture_list .picture_list_list {
  width: 49%;
  margin-top: 2px;
}
.picture_list .picture_list_list img {
  width: 100%;
  height: 18vh;
  object-fit: cover;
}
</style>
