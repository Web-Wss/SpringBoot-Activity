<template>
  <div id="data">
    <!-- <el-card class="box-card"> -->
    <div slot="header" class="clearfix">
      <b>数据看板</b>
    </div>
    <div class="list">
      <div class="list_list">
        <div class="name">用户数量</div>
        <div class="sum">{{ four_list.userSum }}</div>
      </div>
      <div class="list_list">
        <div class="name">员工数量</div>
        <div class="sum">{{ four_list.staffSum }}</div>
      </div>
      <div class="list_list">
        <div class="name">活动数量</div>
        <div class="sum">{{ four_list.activitySum }}</div>
      </div>
      <div class="list_list">
        <div class="name">参与者数量</div>
        <div class="sum">{{ four_list.participantSum }}</div>
      </div>
    </div>
    <div class="echarts">
      <template>
        <div style="min-width: 100%; height: 400px" id="main1"></div>
      </template>
      <template>
        <div style="min-width: 50%; height: 400px" id="main2"></div>
      </template>
      <template>
        <div style="min-width: 50%; height: 400px" id="main3"></div>
      </template>
    </div>
    <!-- </el-card> -->
  </div>
</template>

<script>
export default {
  name: "Data",
  data() {
    return {
      four_list: [],
      // 职工关注度数据
      staff_info: [
        // { value: 432, name: "猎鹰" },
        // { value: 412, name: "飞虎" },
        // { value: 321, name: "山鹰" },
        // { value: 432, name: "云雀" },
        // { value: 534, name: "剑龙" },
        // { value: 543, name: "雪豹" },
        // { value: 123, name: "猛犸" },
        // { value: 534, name: "白鲨" },
        // { value: 123, name: "白鸥" },
        // { value: 543, name: "山猫" },
        // { value: 123, name: "云霄" },
        // { value: 564, name: "狼犬" },
      ],
      // 报名数量
      enroll_info1: [
        // "2023年暑期夏令营",
        // "3月14日一日活动",
        // "3月15日一日活动",
        // "2023年冬令营",
        // "2023年五一活动",
        // "2023年国庆营",
        // "7月1日一日活动",
      ],
      // enroll_info2: [53, 52, 45, 278, 87, 268, 54],
      enroll_info2: [],
      // 新增用户
      // user_info1: [
      //   "2023-03-23",
      //   "2023-03-24",
      //   "2023-03-25",
      //   "2023-03-26",
      //   "2023-03-27",
      //   "2023-03-28",
      //   "2023-03-29",
      // ],
      user_info1: [],
      // user_info2: [32, 123, 43, 234, 64, 3, 6],
      user_info2: [],
    };
  },
  mounted() {
    // setInterval(() => {
    // 获取四个信息
    this.getFourInfoList();
    // 获取职工关注度
    this.getStaffInfo();
    // 获取报名数量
    this.getEnrollInfo();
    // 获取近七天新增用户数
    this.getSevenUserSum();
    // 在通过mounted调用即可

    this.echartsInit1();
    this.echartsInit2();
    this.echartsInit3();
    // }, 2000);
  },
  watch: {
    staff_info(val, value) {
      this.echartsInit3();
    },
    enroll_info1(val, value) {
      this.echartsInit2();
    },
    user_info1(val, value) {
      this.echartsInit1();
    },
  },
  methods: {
    //获取近七天新增用户数
    async getSevenUserSum() {
      await this.$http.get(`/dataadmin/getadduserinfo`).then((res) => {
        console.log(res);
        this.user_info1 = res.data.data.user_info1;
        this.user_info2 = res.data.data.user_info2;
      });
    },
    // 获取报名数量
    async getEnrollInfo() {
      await this.$http.get(`/dataadmin/getenrolllist`).then((res) => {
        console.log(res);
        this.enroll_info1 = res.data.data.enroll_info1;
        this.enroll_info2 = res.data.data.enroll_info2;
      });
    },
    // 获取四个信息
    async getFourInfoList() {
      await this.$http.get(`/dataadmin/getdatalist`).then((res) => {
        console.log(res);
        this.four_list = res.data.data;
      });
    },
    // 获取职工关注度
    async getStaffInfo() {
      await this.$http.get(`/dataadmin/getuserinfo`).then((res) => {
        console.log(res);
        this.staff_info = res.data.data;
      });
    },
    echartsInit1() {
      //折线图
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("main1")).setOption({
        title: {
          text: "新增用户",
          x: "center",
          textStyle: { fontSize: 18, fontWeight: "bolder", color: "red" },
        },
        xAxis: {
          type: "category",
          data: this.user_info1,
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: this.user_info2,
            type: "line",
            label: {
              show: true,
              // position: "button",
              textStyle: {
                color: "black",
                fontSize: 16,
              },
            },
          },
        ],
      });
    },

    echartsInit2() {
      //柱形图
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("main2")).setOption({
        title: {
          text: "报名数量",
          x: "center",
          textStyle: { fontSize: 18, fontWeight: "bolder", color: "red" },
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        grid: {
          left: "3%",
          right: "4%",
          bottom: "3%",
          containLabel: true,
        },
        xAxis: [
          {
            type: "category",
            data: this.enroll_info1,
            axisTick: {
              alignWithLabel: true,
            },
          },
        ],
        yAxis: [
          {
            type: "value",
          },
        ],
        series: [
          {
            name: "报名数量",
            type: "bar",
            barWidth: "50%",
            data: this.enroll_info2,
          },
        ],
      });
    },

    echartsInit3() {
      //饼图
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("main3")).setOption({
        title: {
          text: "职工受关注度",
          x: "center",
          textStyle: { fontSize: 18, fontWeight: "bolder", color: "red" },
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          orient: "vertical",
          left: "left",
        },
        series: [
          {
            name: "Access From",
            type: "pie",
            radius: "50%",
            data: this.staff_info,
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      });
    },
  },
};
</script>

<style scoped>
#data {
  text-align: center;
}
/* 卡片 */
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  overflow-y: scroll;
  width: 99%;
  height: calc(100vh - 120px);
}

/*  */
.echarts {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
}
.echarts div {
  flex-basis: 100px;
  flex-shrink: 0;
}

/* 四个数量 */
.list {
  width: 100%;
  height: 160px;
  display: flex;
  justify-content: space-between;
  /* background-color: aqua; */
}

.list .list_list:nth-child(1) {
  width: 22%;
  background: linear-gradient(to right, #a567c2, #e05b8b);
  border-radius: 25px;
}
.list .list_list:nth-child(2) {
  width: 22%;
  background: linear-gradient(to right, #ba64eb, #40a69e);
  border-radius: 25px;
}
.list .list_list:nth-child(3) {
  width: 22%;
  background: linear-gradient(to right, #5fb6c8, #4886cd);
  border-radius: 25px;
}
.list .list_list:nth-child(4) {
  width: 22%;
  background: linear-gradient(to right, #79d59f, #e76765);
  border-radius: 25px;
}

.list .list_list .name {
  width: 100%;
  height: 80px;
  line-height: 80px;
  font-size: 26px;
}
.list .list_list .sum {
  font-size: 46px;
  color: white;
}
</style>
