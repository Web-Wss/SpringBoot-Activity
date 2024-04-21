<template>
  <div id="activity">
    <!-- 搜索 -->
    <div class="position_top">
      <Search @data="getdata" />
      <van-dropdown-menu>
        <van-dropdown-item v-model="value1" :options="option1" />
        <van-dropdown-item v-model="value2" :options="option2" />
        <!-- <van-dropdown-item v-model="value3" :options="option3" /> -->
      </van-dropdown-menu>
    </div>

    <!-- 活动列表 -->
    <div class="activity">
      <div
        class="activity_list"
        v-for="i in activity_list"
        :key="i.activityId"
        @click="goActivityDetail(i)"
      >
        <div class="list_img">
          <img :src="i.headerList" alt="" />
        </div>
        <div class="list_center">
          <div class="list_name">{{ i.activityName }}</div>
          <div class="list_price">￥{{ i.activityPrice }}</div>
        </div>
        <div class="list_qs">
          活动描述: {{ i.activityDescribe }}
          <span v-for="ii in i.timeList"
            >{{ ii.timeContent }}
            <span v-if="i.timeList.length > 1">、</span>
          </span>
        </div>
      </div>
    </div>

    <!-- 底部 -->
    <TabBar />
  </div>
</template>

<script>
import TabBar from "../components/common/TabBar.vue";
import Search from "../components/common/Search.vue";

export default {
  name: "Activity",
  components: { TabBar, Search },

  data() {
    return {
      // 双滑块模式时，值必须是数组
      value: [7, 14],
      // 分类
      value1: 0,
      // 分类名称
      value2: "a",
      value3: 0,
      option1: [{ text: "分类", value: 0 }],
      option2: [
        { text: "价格", value: "a" },
        { text: "价格升序", value: "b" },
        { text: "价格降序", value: "c" },
      ],
      option3: [{ text: "日期", value: 0 }],
      // 活动列表
      activity_list: [],
    };
  },
  watch: {
    async value1(newVal, oldVal) {
      console.log(newVal, oldVal);
      console.log("发送请求", newVal);
      await this.$http
        .post(
          `/activity/searchlistbyclassification?classification_id=${newVal}`
        )
        .then((res) => {
          console.log(res);
          for (let i = 0; i < res.data.data.length; i++) {
            // res.data.data[i].timeId = res.data.data[i].timeId.split(",").length;
            res.data.data[i].headerList =
              res.data.data[i].headerList[0].headerPath;
            res.data.data[i].imgList = res.data.data[i].imgList[0].imgPath;
            // 处理活动日期
            // for (let j = 0; j < res.data.data[i].timeList.length; j++) {
            //   console.log(res.data.data[i].timeList[j].timeContent);
            //   res.data.data[i].timeList[j] =
            //     res.data.data[i].timeList[j].timeContent;
            // }
            res.data.data[i].timeId = JSON.parse(res.data.data[i].timeId);
          }
          this.activity_list = res.data.data;
        });
    },
    // 价格排序
    value2(newVal, oldVal) {
      // console.log(newVal, oldVal);
      if (newVal == "b") {
        console.log("升");
        var p = {};
        for (let i = 0; i < this.activity_list.length; i++) {
          p[parseInt(this.activity_list[i].activityPrice)] =
            this.activity_list[i];
        }
        console.log(p);
        let i = 0;
        for (let key of Object.keys(p)) {
          console.log(key, p[key]);
          this.activity_list[i] = p[key];
          i += 1;
        }
      } else if (newVal == "c") {
        console.log("降");
        var p = {};
        for (let i = 0; i < this.activity_list.length; i++) {
          p[parseInt(this.activity_list[i].activityPrice)] =
            this.activity_list[i];
        }
        console.log(p);
        let list = Object.values(p);
        console.log(list);
        for (let i = 0, j = list.length - 1; i < list.length; i++, j--) {
          this.activity_list[i] = list[j];
        }
      } else {
        console.log("重新获取列表");
      }
    },
  },
  mounted() {
    // 获取活动列表
    this.getActivityList();
    // 获取活动分类
    this.getActivityClassification();
    // 获取日期
    this.getActivityTIme();
  },
  methods: {
    getdata(d) {
      console.log(d);
      this.activity_list = d;
    },
    // 去活动详情页
    goActivityDetail(i) {
      console.log(i);
      this.$router.push({
        path: "/activitydetails",
        query: { activity_id: i.activityId },
      });
    },
    // 获取活动列表
    async getActivityList() {
      await this.$http.get(`/activity/getactivitylist`).then((res) => {
        console.log(res);
        for (let i = 0; i < res.data.data.length; i++) {
          // res.data.data[i].timeId = res.data.data[i].timeId.split(",").length;
          res.data.data[i].headerList =
            res.data.data[i].headerList[0].headerPath;
          res.data.data[i].imgList = res.data.data[i].imgList[0].imgPath;
          // 处理活动日期
          // for (let j = 0; j < res.data.data[i].timeList.length; j++) {
          //   console.log(res.data.data[i].timeList[j].timeContent);
          //   res.data.data[i].timeList[j] =
          //     res.data.data[i].timeList[j].timeContent;
          // }
          res.data.data[i].timeId = JSON.parse(res.data.data[i].timeId);
        }
        for (let i = 0; i < res.data.data.length; i++) {}

        this.activity_list = res.data.data;

        // 赋值到轮播图列表
        // this.image = res.data.data;
      });
    },
    // 获取活动分类
    async getActivityClassification() {
      await this.$http.get(`/activity/getclassificationlist`).then((res) => {
        console.log(res);
        for (let i = 0; i < res.data.data.length; i++) {
          this.option1.push({
            text: res.data.data[i].classificationName,
            value: res.data.data[i].classificationId,
          });
        }
      });
    },
    // 获取日期
    async getActivityTIme() {
      await this.$http.get(`/activity/gettimelist`).then((res) => {
        console.log(res);
        for (let i = 0; i < res.data.data.length; i++) {
          this.option3.push({
            text: res.data.data[i].timeContent,
            value: i + 1,
          });
        }
      });
    },
  },
};
</script>

<style scoped>
.van-cell__title {
  text-align: center;
}
.custom-button {
  width: 26px;
  color: #fff;
  font-size: 10px;
  line-height: 18px;
  text-align: center;
  background-color: #ee0a24;
  border-radius: 100px;
}
#activity {
  overflow: hidden;
}
.position_top {
  width: 100%;
  position: fixed;
}

/* 活动列表 */
.activity {
  background-color: #f6f6f6;
  width: 100%;
  margin-top: 102px;
}

.activity .activity_list {
  background-color: white;
  margin-bottom: 10px;
}

.activity .activity_list img {
  width: 100%;
}

.activity_list .list_center {
  width: 96%;
  margin: 0 auto;
  display: flex;
  align-items: center;
  line-height: 50px;
}
.activity_list .list_center .list_name {
  font-size: 17px;
  font-weight: 600;
  border-right: 1px dotted rgb(188, 188, 188);
  width: 63%;
}
.activity_list .list_center .list_price {
  font-size: 18px;
  width: 33%;
  color: red;
  text-align: center;
}
.activity_list .list_qs {
  width: 96%;
  margin: 0 auto;
  font-size: 14px;
  color: #959595;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  padding-bottom: 10px;
}
</style>
