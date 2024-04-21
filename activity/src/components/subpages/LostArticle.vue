<template>
  <div id="lostarticle">
    <van-tabs v-model="active" @click="onClick">
      <van-tab title="失物列表">
        <van-swipe-cell v-for="i in lost_list" :key="i.lostArticleId">
          <van-card
            :title="i.lostArticleName"
            class="goods-card"
            :thumb="i.lostArticleImg"
          >
            <template #tags>
              <van-tag plain type="danger" style="margin-top: 5px"
                >联系方式:{{ i.users.phone }}</van-tag
              >
            </template>
            <template #desc>
              <div>
                {{ i.lostArticleDescribe }}
              </div>
            </template>
          </van-card>
        </van-swipe-cell>
      </van-tab>
      <van-tab title="发布失物">
        <div class="fb">
          <van-swipe-cell v-for="i in lost_list_user" :key="i.lostArticleId">
            <van-card
              :title="i.lostArticleName"
              class="goods-card"
              :thumb="i.lostArticleImg"
            >
              <template #desc>
                <div>
                  {{ i.lostArticleDescribe }}
                </div>
              </template>
            </van-card>

            <template #right>
              <van-button
                square
                text="删除"
                type="danger"
                class="delete-button"
                @click="deletel(i)"
              />
            </template>
          </van-swipe-cell>
          <!-- 发布按钮 -->
          <div class="add">
            <van-icon name="add" color="red" @click="gbwp" />
          </div>
        </div>
        <!-- 发布页面 -->
        <van-overlay :show="show">
          <div class="wrapper" @click.stop>
            <div class="block">
              <div class="logo">
                <img src="../../assets/img/sirius.png" alt="" />
              </div>

              <div class="content">
                <div class="title">发布失物招领信息</div>
                <div class="form">
                  <!-- 物品图片 -->
                  <div class="name">物品物品</div>
                  <div class="upload">
                    <van-uploader v-model="fileList" multiple :max-count="1" />
                  </div>

                  <div class="name">物品名称</div>
                  <input type="text" v-model="name" />
                  <div class="card">物品描述</div>
                  <input type="text" v-model="describe" />

                  <!-- 绑定按钮 -->
                  <div class="bind_btn">
                    <van-button type="primary" @click="fb">发布</van-button>
                  </div>
                </div>
                <div class="close" @click="zz()">❌</div>
              </div>
            </div>
          </div>
        </van-overlay>
      </van-tab>
    </van-tabs>
  </div>
</template>

<script>
import storage from "../../utils/storage";
import upLoadImg from "../../utils/upLoadImg.js";
import upLoadImgPublish from "../../utils/upLoadImgPublish.js";
export default {
  name: "LostArticle",
  data() {
    return {
      // 用户
      user: [],
      active: 0,
      show: false,
      // 物品图片
      fileList: [],
      // 失物招领列表
      lost_list: [],
      // 用户自己发布的失物招领
      lost_list_user: [],
      // 物品名称
      name: "",
      // 物品描述
      describe: "",
    };
  },
  mounted() {
    this.user = storage.get("user");
    this.onClick(0);
  },
  methods: {
    gbwp() {
      this.show = true;
    },
    // 发布按钮
    async fb() {
      console.log("文件", this.fileList);
      // await upLoadImg(this.fileList, this.name, this.describe);
      await upLoadImgPublish(
        this.fileList,
        this.user.userId,
        this.name,
        this.describe
      );
      this.fileList = [];
      this.name = "";
      this.describe = "";
      this.show = false;
      this.onClick(1);
    },
    zz() {
      this.show = false;
    },
    // 删除
    async deletel(i) {
      console.log(i);
      await this.$http
        .post(
          `/my/deletelostarticle?user_id=${this.user.userId}&lost_article_id=${i.lostArticleId}`
        )
        .then((res) => {
          console.log(res);
          this.onClick(1);
        });
    },
    async onClick(name, title) {
      if (name == 0) {
        // 发送获取列表
        await this.$http.get(`/my/lostarticle`).then((res) => {
          console.log(res);
          this.lost_list = res.data.data;
        });
      } else if (name == 1) {
        await this.$http
          .post(`/my/getlostarticlelistbyid?user_id=${this.user.userId}`)
          .then((res) => {
            console.log(res);
            this.lost_list_user = res.data.data;
          });
      }
    },
  },
};
</script>

<style scoped>
.goods-card {
  margin: 0;
  background-color: white;
}
.van-card__title {
  font-weight: 600;
}

.delete-button {
  height: 100%;
}
.add {
  width: 100%;
  margin-top: 10px;
  text-align: center;
  font-size: 60px;
}

/* 遮罩层 */
/* 遮罩层 */
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.block {
  position: relative;
  width: 220px;
  height: 550px;
  background-color: #4c4c4c;
}
.block .logo {
  position: relative;
  z-index: 1;
  width: 60px;
  height: 60px;
  margin: 0 auto;
  border-radius: 10px;
  /* background-color: aqua; */
}
.block .logo img {
  width: 100%;
  border-radius: 10px;
}
.upload {
  width: 100%;
  margin-top: 10px;
  text-align: center;
}
.block .content {
  margin-top: -25px;
  width: 100%;
  height: 400px;
  border-radius: 10px;
  border: 1px solid #00a2e6;
  background-color: #fff;
}
.block .content .title {
  margin: 40px auto;
  text-align: center;
  font-size: 14px;
}
.block .content .form .name,
.card {
  font-size: 12px;
  text-align: center;
  color: #9d9d9d;
}
.block .content .form input {
  text-align: center;
  display: block;
  margin: 10px auto;
  width: 80%;
  border: 0;
  border-bottom: 1px solid red;
  /* margin-bottom: 20px; */
}
.form .bind_btn {
  width: 60px;
  margin: 0 auto;
}
.close {
  position: absolute;
  bottom: 0px;
  left: 90px;
  text-align: center;
  line-height: 40px;
  font-size: 25px;
  width: 40px;
  height: 40px;
  border-radius: 50px;
  background-color: rgb(255, 255, 255);
}
</style>
