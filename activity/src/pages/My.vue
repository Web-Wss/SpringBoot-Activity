<template>
  <div id="my">
    <!-- 背景 -->
    <div class="bg">
      <img
        src="https://img2.baidu.com/it/u=1886653989,4125565510&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=313"
        alt=""
      />
    </div>
    <!-- 头像 -->
    <div class="avater_img">
      <img src="https://s1.ax1x.com/2022/03/16/qpRzGT.jpg" alt="" />
      <span>我是昵称</span>
    </div>

    <!-- 列表 -->
    <div class="list">
      <van-cell title="我的报名" is-link value="查看全部报名" />
      <van-cell title="我的收藏" is-link value="共1个" />
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
    <div class="login_out">
      <van-button type="danger" size="large">退出登录</van-button>
    </div>

    <!-- 底部 -->
    <TabBar />
  </div>
</template>

<script>
import TabBar from "../components/common/TabBar.vue";

export default {
  name: "My",
  components: { TabBar },

  data() {
    return {
      // 三个模块
      eight: [
        {
          id: 1,
          name: "意见反馈",
          href: "#icon-yijianfankui",
        },
        {
          id: 2,
          name: "账号安全",
          href: "#icon-anquan",
        },
        {
          id: 3,
          name: "关于我们",
          href: "#icon-guanyu",
        },
        {
          id: 4,
          name: "联系我们",
          href: "#icon-ziyuan",
        },
        {
          id: 5,
          name: "活动评价",
          href: "#icon-shehuipingjia",
        },
        {
          id: 6,
          name: "失物招领",
          href: "#icon-shiwuzhaoling",
        },
        {
          id: 7,
          name: "在线相册",
          href: "#icon-xiangce1",
        },
        {
          id: 8,
          name: "合同信息",
          href: "#icon-hetong",
        },
      ],
    };
  },
};
</script>

<style scoped>
/* 背景 */
.bg img {
  width: 100%;
  height: 200px;
  border-radius: 0 0 70px 70px;
  margin-bottom: 20px;
}

/* 头像 */
.avater_img {
  position: fixed;
  top: 90px;
  left: 30px;
  display: flex;
  width: 160px;
  height: 60px;
}
.avater_img img {
  border-radius: 50px;
  width: 60px;
}
.avater_img span {
  color: white;
  margin-left: 10px;
  display: block;
  font-size: 14px;
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
</style>
