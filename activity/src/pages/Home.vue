<template>
  <div id="home">
    <van-swipe
      height="250"
      class="my-swipe"
      :autoplay="3000"
      indicator-color="white"
    >
      <van-swipe-item v-for="i in image" :key="i.id" @click="swipeGoDetails(i)">
        <img :src="i.headerList" alt="" />
      </van-swipe-item>
    </van-swipe>

    <!-- 通知栏 -->
    <van-notice-bar left-icon="volume-o" :text="notice.noticeContent" />
    <!-- 搜索 -->

    <div @click="goActivity">
      <Search />
    </div>

    <!-- 四大模块 -->

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

    <!-- 活动列表 -->
    <div class="activity_list">
      <div
        class="list_list"
        v-for="i in activity_list"
        :key="i.activityId"
        @click="goDetails(i)"
      >
        <!-- 活动图片 -->
        <img :src="i.headerList" alt="" />
        <div class="list_bottom">
          <div class="list_bottom_name">{{ i.activityName }}</div>
          <div class="list_bottom_bottom">
            活动期数:共{{ i.timeId.length }}期
          </div>
          <div class="list_bottom_price">￥{{ i.activityPrice }}</div>
        </div>
      </div>
    </div>

    <TabBar />
  </div>
</template>

<script>
import TabBar from "../components/common/TabBar.vue";
import Search from "../components/common/Search.vue";
export default {
  name: "Home",
  components: { TabBar, Search },
  data() {
    return {
      // 通知栏信息
      notice: "",
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
      // 三个模块
      eight: [
        {
          id: 1,
          name: "活动报名",
          href: "#icon-baoming",
          to: "/activity",
        },
        // {
        //   id: 2,
        //   name: "报名指南",
        //   href: "#icon-zhinan",
        //   to: "/guide",
        // },
        {
          id: 2,
          name: "在线相册",
          href: "#icon-xiangce1",
          to: "/album",
        },
        {
          id: 3,
          name: "报名查询",
          href: "#icon-xinxichaxun",
          to: "/enrollsearch",
        },
        {
          id: 4,
          name: "成员绑定",
          href: "#icon-bangdingpingtai",
          to: "/bind",
        },
      ],

      // 活动列表
      activity_list: [
        {
          activity_id: 1,
          name: "2023年暑期夏令营",
          img: "https://img0.baidu.com/it/u=161177238,3366283862&fm=253&fmt=auto&app=138&f=JPEG?w=750&h=500",
          time: ["6.25~7.25", "7.25~8.25"],
          price: 3999,
          timeId: [],
          // timeList: [],
        },
        {
          activity_id: 2,
          name: "2023年暑期夏令营",
          img: "https://img0.baidu.com/it/u=1844749691,2659758486&fm=253&fmt=auto&app=138&f=JPEG?w=742&h=500",
          time: ["6.25~8.25"],
          price: 3999,
          timeId: [],
          // timeList: [],
        },
      ],
    };
  },
  mounted() {
    // 获取活动列表
    this.getActivityList();
    // 获取通知栏信息
    this.getNotice();
  },
  methods: {
    // 获取通知栏信息
    async getNotice() {
      await this.$http.get(`/home/getnotice`).then((res) => {
        // console.log(res);
        this.notice = res.data.data;
      });
    },
    // 获取活动列表
    async getActivityList() {
      await this.$http.get(`/home/activitylist`).then((res) => {
        console.log(res);
        for (let i = 0; i < res.data.data.length; i++) {
          // res.data.data[i].timeId = res.data.data[i].timeId.split(",").length;
          res.data.data[i].headerList =
            res.data.data[i].headerList[0].headerPath;
          res.data.data[i].imgList = res.data.data[i].imgList[0].imgPath;
          res.data.data[i].timeId = JSON.parse(res.data.data[i].timeId);
        }
        // for (let i = 0; i < res.data.data.length; i++) {}
        this.activity_list = res.data.data;

        // 赋值到轮播图列表
        this.image = res.data.data;
      });
    },
    // 去活动详情页
    goDetails(i) {
      // console.log(i);
      this.$router.push({
        path: "/activitydetails",
        query: { activity_id: i.activityId },
      });
    },
    // 点击轮播图去详情页
    swipeGoDetails(i) {
      // console.log(i);
      this.$router.push({
        path: "/activitydetails",
        query: { activity_id: i.activityId },
      });
    },
    goActivity() {
      this.$router.push({
        name: "activity",
      });
    },

    // 去四大模块
    goPage(i) {
      console.log(i);
      this.$router.push({
        path: i.to,
      });
    },
  },
};
</script>

<style scoped>
/*  */
.icon {
  font-size: 25px;
  width: 1em;
  height: 2em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}

/* 八大模块 */
.eight {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
}
.eight_list {
  text-align: center;
  width: 25%;
}
.eight_list:hover {
  background-color: rgb(232, 232, 232);
}
.eight_list .eight_bottom {
  font-size: 13px;
}

/*  */

.my-swipe .van-swipe-item {
  color: #fff;
  font-size: 20px;
  line-height: 150px;
  text-align: center;
  /* background-color: #39a9ed; */
}

.my-swipe .van-swipe-item img {
  width: 100%;
  height: 100%;
  /* object-fit: cover; */
  /* object-fit: none; */
}

/* 活动列表 */
.activity_list {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;
  background-color: rgb(248, 248, 248);
}

.activity_list .list_list {
  margin-top: 10px;
  width: 48%;
  background-color: rgb(255, 255, 255);
}
.activity_list .list_list img {
  width: 100%;
  height: 130rpx;
}
.activity_list .list_list .list_bottom .list_bottom_name {
  font-weight: 600;
}
.activity_list .list_list .list_bottom_bottom {
  font-size: 14px;
  color: rgb(167, 167, 167);
}
.activity_list .list_list .list_bottom .list_bottom_price {
  font-size: 18px;
  padding-bottom: 5px;
  color: red;
}
</style>
