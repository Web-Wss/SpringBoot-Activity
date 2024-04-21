<template>
  <div id="my">
    <!-- 背景 -->
    <div class="bg">
      <img
        src="https://img95.699pic.com/element/40143/0717.png_300.png"
        alt=""
      />
    </div>
    <!-- 头像 -->
    <div class="avatar_img" v-show="user.length != 1" @click="editInfo()">
      <img :src="user.userAvatar" alt="" />
      <span>{{ user.username }}</span>
    </div>
    <div class="avatar_img" v-show="user.length == 1">
      <span @click="goLogin()">请登录！</span>
    </div>

    <!-- 修改用户名和头像 -->
    <van-action-sheet v-model="show" title="修改信息">
      <div class="content">
        <!-- 修改头像 -->
        <div class="user_avatar">
          <van-uploader v-model="fileList" multiple :max-count="1" />
          <van-button type="primary" @click="editUserAvatarClick"
            >确认修改头像</van-button
          >
        </div>
        <br />

        <!-- 修改昵称 -->
        <div class="nickname">
          <van-field
            v-model="editUserName"
            center
            clearable
            label="修改用户名"
            placeholder="请输入用户名"
          >
            <template #button>
              <van-button
                size="small"
                type="primary"
                @click="editUserNameMethod()"
                >确认修改</van-button
              >
            </template>
          </van-field>
          <br />
        </div>
      </div>
    </van-action-sheet>
    <!-- 列表 -->
    <div class="list">
      <van-cell
        title="我的报名"
        @click="goMyEnroll"
        is-link
        value="查看全部报名"
      />
      <van-cell
        title="我的收藏"
        @click="goMyCollection"
        is-link
        :value="'共' + myCollection.length + '个'"
      />
      <van-cell title="工具与服务" />
      <div class="eight">
        <div class="eight_list" v-for="i in eight" @click="goPage(i)">
          <div class="eight_top">
            <svg class="icon" aria-hidden="true">
              <use :xlink:href="i.href"></use>
            </svg>
          </div>
          <div class="eight_bottom">{{ i.name }}</div>
        </div>
      </div>
    </div>

    <!-- 退出登录 -->
    <div class="login_out" v-show="user.length != 1">
      <van-button type="danger" size="large" @click="login_out"
        >退出登录</van-button
      >
    </div>

    <!-- 底部 -->
    <TabBar />
  </div>
</template>

<script>
import { Notify } from "vant";
import { Dialog } from "vant";
import TabBar from "../components/common/TabBar.vue";
import storage from "../utils/storage.js";
import store from "../store/index";
import axios from "axios";

export default {
  name: "My",
  components: { TabBar },

  data() {
    return {
      // 修改用户名和头像
      show: false,
      editUserName: "",
      // 用户头像信息
      fileList: [],
      // 用户信息
      user: [
        {
          userAvatar:
            "https://img2.baidu.com/it/u=2064684749,2471246240&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1679763600&t=4eaeb78bc2c53ac07e35c31cf5eeab6d",
        },
      ],
      // 是否登录
      login: true,
      // 我的收藏列表
      myCollection: [],
      // 三个模块
      eight: [
        {
          id: 1,
          name: "意见反馈",
          href: "#icon-yijianfankui",
          go: "option",
        },
        {
          id: 2,
          name: "密码修改",
          href: "#icon-anquan",
          go: "setting",
        },
        // {
        //   id: 3,
        //   name: "关于我们",
        //   href: "#icon-guanyu",
        // },
        // {
        //   id: 4,
        //   name: "联系我们",
        //   href: "#icon-ziyuan",
        // },
        // {
        //   id: 5,
        //   name: "活动评价",
        //   href: "#icon-shehuipingjia",
        // },
        {
          id: 6,
          name: "失物招领",
          href: "#icon-shiwuzhaoling",
          go: "lostarticle",
        },
        // {
        //   id: 7,
        //   name: "在线相册",
        //   href: "#icon-xiangce1",
        // },
        {
          id: 8,
          name: "合同信息",
          href: "#icon-hetong",
          go: "contract",
        },
      ],
    };
  },
  mounted() {
    // this.user = storage.get("user");
    // 判断是否登录
    console.log(storage.get("user"));
    if (storage.get("user") !== null) {
      this.user = storage.get("user");
      this.getMyCollection();
      // this.login = true;
    }
    // if (this.user != null) {
    //   // 获取我的收藏
    //   this.getMyCollection();
    // }
  },
  methods: {
    // 修改用户头像
    async editUserAvatarClick() {
      let params = new FormData();
      let config = {
        headers: {
          // 添加请求头
          "Content-Type": "multipart/form-data",
          Authorization: store.getters.getToken,
        },
      };
      this.fileList.forEach((item, i) => {
        params.append("uploadFile", item.file);
      });
      params.append("user_id", this.user.userId);
      new Promise((resolve, reject) => {
        axios
          .post("/api/my/edituseravatar", params, config)
          .then((res) => {
            console.log(res);
            // 修改成功
            this.fileList = [];
            this.show = false;
            Dialog.alert({
              title: "提示",
              message: "用户头像改成功\n重新登录即可刷新头像",
            }).then(() => {
              // on close
            });
            if (res.status == 200) {
              resolve(res.data);
            } else {
              reject(res.data.data);
            }
          })
          .catch((err) => {
            Toast.file("系统异常");
            reject(err);
          });
      });
    },
    // 修改用户名和密码
    async editUserNameMethod() {
      if (this.editUserName != "") {
        // 修改
        await this.$http
          .post(
            `/my/editusername?user_id=${this.user.userId}&user_name=${this.editUserName}`
          )
          .then((res) => {
            console.log(res);
            if (res.data.data == 1) {
              this.editUserName = "";
              this.show = false;
              Dialog.alert({
                title: "提示",
                message: "用户名修改成功\n重新登录即可刷新用户名",
              }).then(() => {
                // on close
              });
            }
          });
      } else {
        Notify("用户名不能为空!!!");
      }
    },
    // 修改信息
    editInfo() {
      this.show = true;
    },
    // 去登录
    goLogin() {
      this.$router.push({
        path: "/login",
      });
    },
    // 退出登录
    login_out() {
      // storage.removeAll();
      // window.location.reload();
      store.commit("userLogout");
      // window.location.href = "/login";
      this.$router.push({
        path: "/home",
      });
    },
    // 获取我的收藏
    async getMyCollection() {
      await this.$http
        .post(`/my/collection?user_id=${this.user.userId}`)
        .then((res) => {
          console.log(res);
          this.myCollection = res.data.data;
        });
    },
    goMyCollection() {
      this.$router.push({
        name: "mycollection",
      });
    },
    goMyEnroll() {
      this.$router.push({
        name: "enrollsearch",
      });
    },
    goPage(i) {
      console.log(i);
      this.$router.push({
        name: i.go,
      });
    },
  },
};
</script>

<style scoped>
/* 背景 */
.bg img {
  width: 100%;
  height: 200px;
  /* border-radius: 0 0 70px 70px; */
  margin-bottom: 20px;
}

/* 头像 */
.avatar_img {
  position: fixed;
  top: 130px;
  left: 30px;
  display: flex;
  width: 160px;
  height: 60px;
}
.avatar_img img {
  border-radius: 50px;
  width: 60px;
}
.avatar_img span {
  /* color: white; */
  margin-left: 10px;
  display: block;
  font-size: 16px;
  height: 60px;
  line-height: 60px;
}

/* 八大模块 */
.icon {
  font-size: 25px;
  width: 1em;
  height: 2em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
.eight {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}
.eight_list {
  text-align: center;
  width: 25%;
}
.eight_list .eight_bottom {
  font-size: 13px;
}

/* 退出登录 */
.login_out {
  width: 80%;
  margin: 60px auto;
}
.login_out button {
  border-radius: 50px;
}

/* 修改用户名和头像 */
.content {
  text-align: center;
  width: 100%;
  /* background-color: red; */
}
</style>
