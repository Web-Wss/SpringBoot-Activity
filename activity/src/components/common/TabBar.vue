<template>
  <div id="tabbar">
    <van-tabbar route v-model="active" fixed placeholder>
      <van-tabbar-item class="iconfont icon-shouye" to="/home"
        >首页</van-tabbar-item
      >
      <van-tabbar-item class="iconfont icon-huodong-copy" to="/activity"
        >活动</van-tabbar-item
      >
      <!-- <van-tabbar-item class="iconfont icon-bangding">信息</van-tabbar-item> -->
      <van-tabbar-item class="iconfont icon-wode" to="/my"
        >我的</van-tabbar-item
      >
    </van-tabbar>
  </div>
</template>

<script>
export default {
  name: "TabBar",
  data() {
    return {
      active: 0,
    };
  },
};
</script>

<style>
/* Font class样式 */
.van-tabbar-item {
  font-size: 13px;
}
.van-tabbar-item::before {
  font-size: 22px;
}
/* Symbol样式 */
.icon {
  width: 4em;
  height: 3em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
</style>
