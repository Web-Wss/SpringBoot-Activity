<template>
  <div id="enrollmanage">
    <div class="header">
      <template>
        活动名称：
        <el-select
          v-model="value"
          placeholder="选择活动名称"
          @change="getTimeList()"
        >
          <el-option
            v-for="item in activity_name"
            :key="item.activityId"
            :label="item.activityName"
            :value="item.activityId"
          >
          </el-option>
        </el-select>
      </template>
      <template>
        &nbsp; 活动日期：
        <el-select
          v-model="value1"
          placeholder="选择活动日期"
          @change="getActivityListByActivityIdAndTimeId()"
        >
          <el-option
            v-for="item in time_list"
            :key="item.timeId"
            :label="item.timeContent"
            :value="item.timeId"
          >
          </el-option>
        </el-select>
      </template>
      &nbsp;
      <!-- <el-button type="primary" icon="el-icon-search">数据导出</el-button> -->
      &nbsp; 参与者查询：
      <div class="participantsearch">
        <el-input
          v-model="input"
          placeholder="请输入要查询参与者姓名"
        ></el-input>
        &nbsp;&nbsp;
        <el-button
          type="primary"
          icon="el-icon-search"
          circle
          @click="participantSearchClick"
        ></el-button>
      </div>
    </div>

    <div class="content">
      <!--报名表格  -->
      <template>
        <el-table :data="tableData" stripe border style="width: 100%">
          <el-table-column
            prop="enrollId"
            label="报名ID"
            width="80"
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
            label="活动时间"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="users.parentName"
            label="监护人"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="users.phone"
            label="联系方式"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="participant.participantName"
            label="参与者"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="staff.staffName"
            label="活动职工"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="userRemarks"
            label="用户备注"
            width="180"
            align="center"
          >
          </el-table-column>
          <el-table-column
            prop="publishTime"
            label="提交时间"
            width="180"
            align="center"
          >
          </el-table-column>

          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button
                type="primary"
                icon="el-icon-search"
                circle
                @click="lookenroll(scope.row)"
              ></el-button>
              <el-button
                type="danger"
                icon="el-icon-delete"
                circle
                @click="deleteenroll(scope.row)"
              ></el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
      <!-- 分页 -->
      <!-- <div class="fy">
        <el-pagination background layout="prev, pager, next" :total="1000">
        </el-pagination>
      </div> -->
    </div>

    <!-- 查看详细信息 -->
    <el-dialog title="报名详细信息" :visible.sync="dialogTableVisible">
      <el-descriptions class="margin-top">
        <el-descriptions-item label="报名ID">{{ xq.id }}</el-descriptions-item>
        <el-descriptions-item label="活动名称">{{
          xq.activity_name
        }}</el-descriptions-item>
        <el-descriptions-item label="活动时间">{{
          xq.time
        }}</el-descriptions-item>
        <el-descriptions-item label="监护人">{{
          xq.parent
        }}</el-descriptions-item>
        <el-descriptions-item label="联系方式">{{
          xq.phone
        }}</el-descriptions-item>
        <el-descriptions-item label="参与者">{{
          xq.participant_name
        }}</el-descriptions-item>
        <el-descriptions-item label="参与者身高">{{
          xq.participant_height
        }}</el-descriptions-item>
        <el-descriptions-item label="参与者体重">{{
          xq.participant_weight
        }}</el-descriptions-item>
        <el-descriptions-item label="参与者性别">{{
          xq.sex
        }}</el-descriptions-item>
        <el-descriptions-item label="参与者身份证">{{
          xq.card
        }}</el-descriptions-item>
        <el-descriptions-item label="活动职工">{{
          xq.staff
        }}</el-descriptions-item>
        <el-descriptions-item label="备注">
          <el-tag size="small">{{ xq.bz }}</el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="提交时间">{{
          xq.publish
        }}</el-descriptions-item>
      </el-descriptions>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "EnrollManage",
  data() {
    return {
      // 报名详细信息
      dialogTableVisible: false,
      xq: {
        id: "",
        activity_name: "",
        time: "",
        parent: "",
        phone: "",
        participant_name: "",
        participant_height: "",
        participant_weight: "",
        sex: "",
        card: "",
        staff: "",
        bz: "",
        publish: "",
      },

      //
      tableData: [
        // {
        //   id: 7000001,
        //   name: "天狼2023年暑期夏令营",
        //   date: "[2023/07/01-2023/07/07]",
        //   user: "监护人",
        //   phone: "13359074748",
        //   participant: "参与者",
        //   staff: ["白鲨"],
        //   bz: "用户备注",
        //   push: "2023/05/05",
        // },
        // {
        //   id: 7000002,
        //   name: "天狼2023年暑期夏令营",
        //   date: "[2023/07/01-2023/07/07]",
        //   user: "监护人",
        //   phone: "13359074748",
        //   participant: "参与者",
        //   staff: ["白鲨"],
        //   bz: "用户备注",
        //   push: "2023/05/05",
        // },
        // {
        //   id: 7000003,
        //   name: "天狼2023年暑期夏令营",
        //   date: "[2023/07/01-2023/07/07]",
        //   user: "监护人",
        //   phone: "13359074748",
        //   participant: "参与者",
        //   staff: ["白鲨"],
        //   bz: "用户备注",
        //   push: "2023/05/05",
        // },
        // {
        //   id: 7000004,
        //   name: "天狼2023年暑期夏令营",
        //   date: "[2023/07/01-2023/07/07]",
        //   user: "监护人",
        //   phone: "13359074748",
        //   participant: "参与者",
        //   staff: ["白鲨"],
        //   bz: "用户备注",
        //   push: "2023/05/05",
        // },
        // {
        //   id: 7000005,
        //   name: "天狼2023年暑期夏令营",
        //   date: "[2023/07/01-2023/07/07]",
        //   user: "监护人",
        //   phone: "13359074748",
        //   participant: "参与者",
        //   staff: ["白鲨"],
        //   bz: "用户备注",
        //   push: "2023/05/05",
        // },
        // {
        //   id: 7000006,
        //   name: "天狼2023年暑期夏令营",
        //   date: "[2023/07/01-2023/07/07]",
        //   user: "监护人",
        //   phone: "13359074748",
        //   participant: "参与者",
        //   staff: ["白鲨"],
        //   bz: "用户备注",
        //   push: "2023/05/05",
        // },
        // {
        //   id: 7000007,
        //   name: "天狼2023年暑期夏令营",
        //   date: "[2023/07/01-2023/07/07]",
        //   user: "监护人",
        //   phone: "13359074748",
        //   participant: "参与者",
        //   staff: ["白鲨"],
        //   bz: "用户备注",
        //   push: "2023/05/05",
        // },
        // {
        //   id: 7000008,
        //   name: "天狼2023年暑期夏令营",
        //   date: "[2023/07/01-2023/07/07]",
        //   user: "监护人",
        //   phone: "13359074748",
        //   participant: "参与者",
        //   staff: ["白鲨"],
        //   bz: "用户备注",
        //   push: "2023/05/05",
        // },
        // {
        //   id: 7000009,
        //   name: "天狼2023年暑期夏令营",
        //   date: "[2023/07/01-2023/07/07]",
        //   user: "监护人",
        //   phone: "13359074748",
        //   participant: "参与者",
        //   staff: ["白鲨"],
        //   bz: "用户备注",
        //   push: "2023/05/05",
        // },
        // {
        //   id: 7000010,
        //   name: "天狼2023年暑期夏令营",
        //   date: "[2023/07/01-2023/07/07]",
        //   user: "监护人",
        //   phone: "13359074748",
        //   participant: "参与者",
        //   staff: ["白鲨"],
        //   bz: "用户备注",
        //   push: "2023/05/05",
        // },
      ],
      activity_name: [
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
      // 时间liebia
      time_list: [],
      // 活动名称
      value: "",
      // 活动时间
      value1: "",
      // 参与者名称
      input: "",
    };
  },
  mounted() {
    // 获取报名列表
    this.getEnrollList();
    // 获取活动名称
    this.getActivityNameList();
  },
  methods: {
    //参与者查询
    async participantSearchClick() {
      console.log(this.input);
      await this.$http
        .post(`/enrolladmin/participantname?participant_name=${this.input}`)
        .then((res) => {
          console.log(res);
          this.tableData = res.data.data;
        });
    },
    // 获取报名列表根据活动id和时间id
    async getActivityListByActivityIdAndTimeId() {
      console.log(this.value);
      console.log(this.value1);
      await this.$http
        .post(
          `/enrolladmin/getenrolllistbytimeandactivityid?activity_id=${this.value}&time_id=${this.value1}`
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
    // 获取活动名称列表
    async getActivityNameList() {
      await this.$http.get(`/enrolladmin/getactivitynamelist`).then((res) => {
        console.log(res);
        this.activity_name = res.data.data;
      });
    },
    // 获取报名列表
    async getEnrollList() {
      await this.$http.get(`/enrolladmin/getenrolllist`).then((res) => {
        console.log(res);
        this.tableData = res.data.data;
      });
    },
    // 删除报名
    async deleteenroll(i) {
      this.$confirm(
        `此操作将永久删除ID为${i.enrollId}的这条数据, 是否继续?`,
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(() => {
          this.$http
            .post(`/enrolladmin/deleteenrollbyid?id=${i.enrollId}`)
            .then((res) => {
              console.log(res);
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              this.getEnrollList();
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    lookenroll(i) {
      console.log(i);
      this.xq.id = i.enrollId;
      this.xq.activity_name = i.activity.activityName;
      this.xq.time = i.time.timeContent;
      this.xq.parent = i.users.parentName;
      this.xq.phone = i.users.phone;
      this.xq.participant_name = i.participant.participantName;
      this.xq.participant_height = i.participant.participantHeight;
      this.xq.participant_weight = i.participant.participantWeight;
      this.xq.sex = i.participant.participantSex;
      this.xq.card = i.participant.participantCard;
      this.xq.staff = i.staff.staffName;
      this.xq.bz = i.userRemarks;
      this.xq.publish = i.publishTime;
      this.dialogTableVisible = true;
    },
  },
};
</script>

<style scoped>
.content {
  margin-top: 10px;
}

.header {
  display: flex;
  align-items: center;
}
.participantsearch {
  width: 300px;
  display: flex;
}

.fy {
  text-align: center;
  margin: 10px auto;
}
</style>
