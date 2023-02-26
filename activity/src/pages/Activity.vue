<template>
  <div id="activity">
    <!-- 搜索 -->
    <div class="position_top">
      <Search />
      <van-dropdown-menu>
        <van-dropdown-item v-model="value1" :options="option1" />
        <van-dropdown-item title="年龄" ref="item">
          <van-cell center title="年龄区间">
            <template #label>
              <van-slider
                max="60"
                @click="onChange(value)"
                v-model="value"
                range
                active-color="#ee0a24"
              >
                <template #left-button>
                  <div class="custom-button">{{ value[0] }}</div>
                </template>
                <template #right-button>
                  <div class="custom-button">{{ value[1] }}</div>
                </template>
              </van-slider>
            </template>
          </van-cell>
        </van-dropdown-item>
        <van-dropdown-item v-model="value2" :options="option2" />
        <van-dropdown-item v-model="value3" :options="option3" />
      </van-dropdown-menu>
    </div>

    <!-- 活动列表 -->
    <div class="activity">
      <div class="activity_list" v-for="i in 5" :key="i">
        <div class="list_img">
          <img
            src="https://img1.baidu.com/it/u=458399916,2714823325&fm=253&fmt=auto&app=138&f=JPEG?w=667&h=500"
            alt=""
          />
        </div>
        <div class="list_center">
          <div class="list_name">2023天狼暑期夏令营（鹰隼）</div>
          <div class="list_price">￥2980.00</div>
        </div>
        <div class="list_qs">
          活动期数(共3期):06-25~08-25、06-25~08-25、06-25~08-25
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
      value1: 0,
      value2: "a",
      value3: "a",
      option1: [
        { text: "分类", value: 0 },
        { text: "夏令营", value: 1 },
        { text: "冬令营", value: 2 },
        { text: "节假日", value: 3 },
      ],
      option2: [
        { text: "价格", value: "a" },
        { text: "价格升序", value: "b" },
        { text: "价格降序", value: "c" },
      ],
      option3: [
        { text: "日期", value: "a" },
        { text: "6.25~7.25", value: "b" },
        { text: "6.25~7.25", value: "c" },
        { text: "6.25~7.25", value: "d" },
        { text: "6.25~7.25", value: "e" },
      ],
    };
  },
  methods: {
    onChange(value) {
      alert("当前值：" + value);
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
