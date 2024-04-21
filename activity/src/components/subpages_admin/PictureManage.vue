<template>
  <div id="picturemanage">
    <!-- 相册信息 -->
    <div class="album_info">
      <div class="header">
        <img :src="header" alt="" />
      </div>
      <div class="name">{{ name }}</div>
      <div class="time">{{ time }}</div>
    </div>
    <!-- 上传图片 -->
    <div class="upload">
      <el-upload
        name="file"
        class="upload-demo"
        show-file-list
        drag
        action="#"
        :http-request="uploadImage"
        multiple
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip" slot="tip">
          只能上传jpg/png文件，且不超过500kb
        </div>
      </el-upload>
    </div>

    <!-- 照片列表 -->
    <div class="picture_list">
      <div class="img_list" v-for="i in picture_list" :key="i.pictureId">
        <img :src="i.pictureImg" alt="" />
        <span @click="deleteimg(i)">删除</span>
      </div>
    </div>
  </div>
</template>

<script>
import upLoadImgPublishAndAlbumPicture from "../../utils/upLoadImgPublishAndAlbumPicture";
export default {
  name: "PictureManage",
  data() {
    return {
      album_id: 0,
      staff_id: 1,
      header: "",
      name: "",
      time: "",
      // 图片列表
      picture_list: [],
    };
  },
  mounted() {
    this.album_id = this.$route.query.albumId;
    this.header = this.$route.query.header;
    this.name = this.$route.query.name;
    this.time = this.$route.query.time;

    // 获取图片列表
    this.getPictureList();
  },
  methods: {
    // 上传图片
    uploadImage(params) {
      console.log(params.file);
      upLoadImgPublishAndAlbumPicture(
        params.file,
        this.album_id,
        this.staff_id
      );
      setTimeout(() => {
        this.getPictureList();
      }, 1000);
    },
    // 删除图片
    async deleteimg(i) {
      console.log("删除图片", i);
      await this.$http
        .post(
          `/albumadmin/deletepicturebyid?picture_id=${i.pictureId}&picture_img=${i.pictureImg}`
        )
        .then((res) => {
          console.log(res);
          this.getPictureList();
        });
    },
    // 获取图片列表
    async getPictureList() {
      await this.$http
        .post(`/albumadmin/getpicturebyalbumid?album_id=${this.album_id}`)
        .then((res) => {
          console.log(res);
          this.picture_list = res.data.data;
        });
    },
  },
};
</script>

<style scoped>
.album_info {
  width: 100%;
  margin-bottom: 10px;
}
.album_info .header {
  width: 100%;
}
.album_info .header img {
  width: 100%;
}
.name,
.time {
  font-size: 20px;
  text-align: center;
  color: red;
}

.picture_list {
  margin-top: 10px;
}

.picture_list {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-around;
}

.picture_list .img_list {
  margin-top: 10px;
  width: 23%;
  position: relative;
}
.picture_list .img_list img {
  width: 100%;
  height: 30vh;
  object-fit: cover;
}

.picture_list span {
  color: white;
  background-color: rgb(12, 129, 84);
  position: absolute;
  display: block;
  width: 50px;
  height: 30px;
  text-align: center;
  line-height: 30px;
  top: 0;
  right: 0;
  cursor: pointer;
}

.upload {
  width: 100%;
  text-align: center;
}
</style>
