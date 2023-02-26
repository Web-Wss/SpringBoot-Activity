<template>
  <div id="activitydetails">
    <!-- 轮播图 -->
    <div class="swipe">
      <van-swipe
        height="250"
        class="my-swipe"
        :autoplay="3000"
        indicator-color="white"
      >
        <van-swipe-item v-for="i in image" :key="i.id">
          <img :src="i.path" alt="" />
        </van-swipe-item>
      </van-swipe>
      <!-- 收藏图标 -->
      <div class="collection">
        <!-- <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-shoucang"></use>
        </svg> -->
        <span class="iconfont icon-shoucang"></span>
      </div>
    </div>

    <!-- 内容 -->
    <div class="content">
      <!-- 价格 -->
      <div class="price">￥2980.00</div>
      <div class="name">2023天狼暑期夏令营（鹰隼）</div>

      <!-- 浏览人数 -->
      <div class="look_sum">
        <div class="left">
          <div class="text">浏览次数</div>
          <div class="sum">56948</div>
        </div>
        <div class="img">
          <img src="https://s1.ax1x.com/2022/03/16/qpRzGT.jpg" alt="" />
          <img
            src="https://img1.baidu.com/it/u=4059001623,3671632887&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            alt=""
          />
          <img
            src="https://img1.baidu.com/it/u=2263087356,1917575480&fm=253&fmt=auto"
            alt=""
          />
          <img
            src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fci.xiaohongshu.com%2F5802b95a-5693-6317-a02f-6985607dffc7%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fci.xiaohongshu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1679288297&t=c3db67cc4dba93f8a81ae36ec15ac502"
            alt=""
          />
          <img
            src="https://tupian.qqw21.com/article/UploadPic/2020-11/202011422375131589.png"
            alt=""
          />
          <img
            src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fblog%2F202101%2F05%2F20210105222937_0151d.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1679288210&t=2258329e66bdf77443a1195c6f4bcfb7"
            alt=""
          />
          <img
            src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202004%2F23%2F20200423072218_fsglf.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1679288210&t=a9a0b09138620b5a41e2ee433fa2a1fa"
            alt=""
          />
          <img
            src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fc-ssl.duitang.com%2Fuploads%2Fitem%2F202012%2F23%2F20201223084307_njucc.thumb.1000_0.jpg&refer=http%3A%2F%2Fc-ssl.duitang.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1679288257&t=645743029069fb9d87c8c3f0eae0480b"
            alt=""
          />
        </div>
      </div>

      <!-- 个人中心 -->
      <van-cell is-link>
        <!-- 使用 title 插槽来自定义标题 -->
        <template #title>
          <span class="custom-title"
            ><span style="color: red">|</span> 个人中心</span
          >
        </template>
      </van-cell>

      <!-- 选择日期 -->
      <van-cell is-link to="enrollpage">
        <!-- 使用 title 插槽来自定义标题 -->
        <template #icon>
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-rili"></use>
          </svg>
        </template>
        <template #title>
          <span class="custom-title"> 选择日期</span>&nbsp;
          <van-tag type="danger"> 活动期数:共3期</van-tag>
        </template>
      </van-cell>

      <!-- 活动介绍 -->
      <van-divider>活动介绍</van-divider>
      <div class="details">
        <img src="https://s1.ax1x.com/2022/03/16/qpRzGT.jpg" alt="" />
        <img
          src="https://img1.baidu.com/it/u=2263087356,1917575480&fm=253&fmt=auto"
          alt=""
        />
        <img
          src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fci.xiaohongshu.com%2F5802b95a-5693-6317-a02f-6985607dffc7%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fci.xiaohongshu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1679288297&t=c3db67cc4dba93f8a81ae36ec15ac502"
          alt=""
        />
        文字
      </div>

      <!-- 底部 -->
      <div class="bottom_list">
        <van-goods-action safe-area-inset-bottom>
          <van-goods-action-icon icon="home-o" text="首页" />
          <van-goods-action-icon icon="chat-o" text="客服" color="#ee0a24" />
          <van-goods-action-icon icon="star" text="已收藏" color="#ff5000" />
          <van-goods-action-button type="danger" text="立即报名" />
        </van-goods-action>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ActivityDetails",
  data() {
    return {
      // 轮播活动图
      image: [
        {
          id: 1,
          path: "https://img0.baidu.com/it/u=1218576611,3962421619&fm=253&fmt=auto&app=138&f=PNG?w=641&h=423",
        },
        {
          id: 2,
          path: "https://img1.baidu.com/it/u=3325717417,2556458847&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=375",
        },
      ],
    };
  },
  mounted() {
    console.log(this.$route.query);
  },
};
</script>

<style scoped>
/* icon */
.icon {
  width: 2em;
  height: 1.5em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}

/*  */
#activitydetails {
  background-color: rgb(248, 248, 248);
}
.my-swipe .van-swipe-item {
  color: #fff;
  font-size: 20px;
  line-height: 150px;
  text-align: center;
}

.my-swipe .van-swipe-item img {
  width: 100%;
  height: 100%;
}

.swipe {
  position: relative;
}

.swipe .collection {
  position: absolute;
  top: 10px;
  right: 20px;
}
.swipe .collection span {
  color: white;
  font-size: 30px;
}
/* 内容 */
.content {
  /* width: 100%; */
  padding: 5px;
  margin: 10px auto;
  background-color: rgb(255, 255, 255);
}
.content .price {
  font-size: 22px;
  color: red;
  padding: 10px;
}
.content .name {
  font-size: 20px;
  padding: 15px;
  font-weight: 600;
}

/* 个人中心 */

.custom-title {
  /* margin-right: 4px; */
  vertical-align: middle;
}

/* 浏览次数 */
.look_sum {
  /* border-top: 5px solid rgb(248, 248, 248); */
  width: 100%;
  height: 50px;
  margin: 0 auto;
  /* background-color: aqua; */
  display: flex;
}
.look_sum .left {
  width: 25%;
  height: 50px;
  line-height: 25px;
  text-align: center;
  /* background-color: rgb(36, 143, 49); */
}
.look_sum .left .sum {
  color: rgb(0, 255, 21);
}
.look_sum .img {
  padding-left: 30px;
  width: 65%;
  /* background-color: blueviolet; */
  display: flex;
  align-items: center;
}
.look_sum .img img {
  margin-left: -10px;
  border-radius: 50px;
  width: 40px;
  height: 40px;
}

/* 活动介绍 */
.details {
  margin-bottom: 40px;
}
.details img {
  width: 100%;
}
</style>
