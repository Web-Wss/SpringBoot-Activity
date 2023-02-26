<template>
  <div id="home_admin">
    <el-container>
      <el-header><HeaderAdmin /></el-header>
      <el-container>
        <el-aside width="200px"><AsideAdmin /></el-aside>
        <el-main>
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <b>数据看板</b>
              <el-button style="float: right; padding: 3px 0" type="text"
                >刷新</el-button
              >
            </div>
            <div class="echarts">
              <!-- 用户数 -->
              <template>
                <div style="min-width: 50%; height: 400px" id="main"></div>
              </template>
              <!-- 新增用户 -->
              <template>
                <div style="min-width: 50%; height: 400px" id="main1"></div>
              </template>
              <!-- 参与者数 -->
              <template>
                <div style="min-width: 50%; height: 400px" id="main2"></div>
              </template>
              <!-- 员工数量 -->
              <template>
                <div style="min-width: 50%; height: 400px" id="main3"></div>
              </template>
              <!-- 活动数量 -->
              <template>
                <div style="min-width: 50%; height: 400px" id="main4"></div>
              </template>
              <!-- 报名数量 -->
              <template>
                <div style="min-width: 50%; height: 400px" id="main5"></div>
              </template>
              <!-- 职工受关注数 -->
              <template>
                <div style="min-width: 50%; height: 400px" id="main6"></div>
              </template>
              <!-- 累计点击 -->
              <template>
                <div style="min-width: 50%; height: 400px" id="main7"></div>
              </template>
            </div>
          </el-card>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import HeaderAdmin from "../components/common_admin/HeaderAdmin.vue";
import AsideAdmin from "../components/common_admin/AsideAdmin.vue";
export default {
  name: "HomeAdmin",
  components: { HeaderAdmin, AsideAdmin },
  data() {
    return {};
  },
  mounted() {
    // 在通过mounted调用即可
    this.echartsInit();
    this.echartsInit1();
    this.echartsInit2();
    this.echartsInit3();
    this.echartsInit4();
    this.echartsInit5();
    this.echartsInit6();
    this.echartsInit7();
  },
  methods: {
    //初始化echarts
    echartsInit() {
      //折线图
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("main")).setOption({
        title: {
          text: "用户数",
          x: "center",
          textStyle: { fontSize: 18, fontWeight: "bolder", color: "red" },
        },
        xAxis: {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: "line",
          },
        ],
      });
    },
    echartsInit2() {
      //折线图
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("main2")).setOption({
        title: {
          text: "用户数",
          x: "center",
          textStyle: { fontSize: 18, fontWeight: "bolder", color: "red" },
        },
        xAxis: {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: "line",
          },
        ],
      });
    },
    echartsInit3() {
      //折线图
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("main3")).setOption({
        xAxis: {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: "line",
          },
        ],
      });
    },
    echartsInit4() {
      //折线图
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("main4")).setOption({
        xAxis: {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: "line",
          },
        ],
      });
    },
    echartsInit5() {
      //折线图
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("main5")).setOption({
        xAxis: {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: "line",
          },
        ],
      });
    },
    echartsInit6() {
      //折线图
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("main6")).setOption({
        xAxis: {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: "line",
          },
        ],
      });
    },
    echartsInit7() {
      //折线图
      //因为初始化echarts 的时候，需要指定的容器 id='main'
      this.$echarts.init(document.getElementById("main7")).setOption({
        xAxis: {
          type: "category",
          data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"],
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: [150, 230, 224, 218, 135, 147, 260],
            type: "line",
          },
        ],
      });
    },
    // 员工收关注度
    echartsInit1() {
      this.$echarts.init(document.getElementById("main1")).setOption({
        legend: {},
        tooltip: {
          trigger: "axis",
          showContent: false,
        },
        dataset: {
          source: [
            ["product", "2012", "2013", "2014", "2015", "2016", "2017"],
            ["Milk Tea", 56.5, 82.1, 88.7, 70.1, 53.4, 85.1],
            ["Matcha Latte", 51.1, 51.4, 55.1, 53.3, 73.8, 68.7],
            ["Cheese Cocoa", 40.1, 62.2, 69.5, 36.4, 45.2, 32.5],
            ["Walnut Brownie", 25.2, 37.1, 41.2, 18, 33.9, 49.1],
          ],
        },
        xAxis: { type: "category" },
        yAxis: { gridIndex: 0 },
        grid: { top: "55%" },
        series: [
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "line",
            smooth: true,
            seriesLayoutBy: "row",
            emphasis: { focus: "series" },
          },
          {
            type: "pie",
            id: "pie",
            radius: "30%",
            center: ["50%", "25%"],
            emphasis: {
              focus: "self",
            },
            label: {
              formatter: "{b}: {@2012} ({d}%)",
            },
            encode: {
              itemName: "product",
              value: "2012",
              tooltip: "2012",
            },
          },
        ],
      });
    },
  },
};
</script>

<style scoped>
.el-header,
.el-footer {
  background-color: #ffffff;
  color: rgb(51, 51, 51);
  line-height: 60px;
}

.el-aside {
  background-color: #ffffff;
  color: #333;
  text-align: center;
  height: calc(100vh - 60px);
}

.el-main {
  background-color: #f2f2f2;
  color: #333;
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
</style>
