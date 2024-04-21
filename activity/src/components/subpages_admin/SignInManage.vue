<template>
  <div id="signinmanage">
    <div class="header">
      活动选择：
      <el-select
        v-model="value"
        placeholder="请选择活动名称"
        @change="getTimeList()"
      >
        <el-option
          v-for="item in activity_list"
          :key="item.activityId"
          :label="item.activityName"
          :value="item.activityId"
        >
        </el-option>
      </el-select>
      时间选择：
      <el-select
        v-model="value1"
        placeholder="请选择活动名称"
        @change="getSignInListByTimeIdAndActivityId()"
      >
        <el-option
          v-for="item in time_list"
          :key="item.timeId"
          :label="item.timeContent"
          :value="item.timeId"
        >
        </el-option>
      </el-select>
      <!-- 状态
      <el-select v-model="value" placeholder="请选择活动名称">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
        >
        </el-option>
      </el-select> -->
    </div>
    <div class="content">
      <el-table :data="tableData" stripe border style="width: 100%">
        <el-table-column
          prop="signInId"
          label="签到id"
          width="180"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="activity.activityId"
          label="活动ID"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="activity.activityName"
          label="活动名称"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="time.timeContent"
          label="活动日期"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="participant.participantName"
          label="参与者姓名"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="participant.participantCard"
          label="参与者身份证"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column
          prop="isSignIn"
          label="签到状态"
          width="180"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="signInTime" label="签到时间" align="center">
        </el-table-column>

        <!-- <el-table-column label="操作" align="center">
          <el-button type="primary" icon="el-icon-edit" circle></el-button>
          <el-button type="danger" icon="el-icon-delete" circle></el-button>
        </el-table-column> -->
      </el-table>

      <!-- 分页 -->
      <!-- <div class="fy">
        <el-pagination background layout="prev, pager, next" :total="1000">
        </el-pagination>
      </div> -->
    </div>
  </div>
</template>

<script>
export default {
  name: "SignInManage",
  data() {
    return {
      activity_list: [
        // {
        //   value: "选项1",
        //   label: "黄金糕",
        // },
        // {
        //   value: "选项2",
        //   label: "双皮奶",
        // },
        // {
        //   value: "选项3",
        //   label: "蚵仔煎",
        // },
        // {
        //   value: "选项4",
        //   label: "龙须面",
        // },
        // {
        //   value: "选项5",
        //   label: "北京烤鸭",
        // },
      ],
      time_list: [],
      value: "",
      value1: "",
      // 内容
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
        {
          date: "2016-05-04",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1517 弄",
        },
        {
          date: "2016-05-01",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1519 弄",
        },
        {
          date: "2016-05-03",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1516 弄",
        },
      ],
    };
  },
  mounted() {
    // 获取签到列表
    this.getSignInList();
    // 获取活动名称
    this.getActivityName();
  },
  methods: {
    //获取签到列表根据时间id和活动id
    async getSignInListByTimeIdAndActivityId() {
      await this.$http
        .post(
          `/signinadmin/getsigninlistbytimeidandactivityid?activity_id=${this.value}&time_id=${this.value1}`
        )
        .then((res) => {
          console.log(res);
          this.tableData = res.data.data;
        });
    },
    // 获取时间列表
    async getTimeList() {
      console.log(this.value);
      await this.$http
        .post(`/enrolladmin/gettimelistbyactivityid?activity_id=${this.value}`)
        .then((res) => {
          console.log(res);
          this.time_list = res.data.data;
          this.value1 = "";
        });
    },
    // 获取活动名称
    async getActivityName() {
      await this.$http.get(`/enrolladmin/getactivitynamelist`).then((res) => {
        console.log(res);
        this.activity_list = res.data.data;
      });
    },
    // 获取签到列表
    async getSignInList() {
      await this.$http.get(`/signinadmin/getsigninlist`).then((res) => {
        console.log(res);
        this.tableData = res.data.data;
      });
    },
  },
};
</script>

<style scoped>
.header {
  margin-bottom: 10px;
}

.fy {
  text-align: center;
  margin: 10px auto;
}
</style>
