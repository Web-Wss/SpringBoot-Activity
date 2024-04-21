<template>
  <div id="enrollpage">
    <!-- 活动名称 -->
    <div class="name">{{ activity_name }}</div>
    <!-- 选择日期 -->
    <div class="wz">
      <van-divider
        >选择日期<span> &nbsp;剩余总名额:{{ totalSum }}人</span></van-divider
      >
    </div>

    <div class="date">
      <van-radio-group
        v-model="form.timeId"
        :max="1"
        v-for="i in date_list"
        :key="i.timeId"
      >
        <van-radio :name="i.timeId" @click="changeDatee(i)"
          >{{ i.timeContent
          }}<span>剩余名额:{{ i.personSum - i.enrollSum }}</span></van-radio
        ><br />
      </van-radio-group>
    </div>
    <!-- 参与者选择 -->
    <div class="participant">
      <van-divider>选择参与者</van-divider>
      <van-checkbox-group
        v-model="form.participantId"
        :max="max_participant"
        v-for="i in participant_list"
        :key="i.participantId"
      >
        <van-checkbox :name="i.participantId" @click="changeParticipantt(i)"
          >{{ i.participantName }} - {{ i.participantCard }}</van-checkbox
        ><br />
      </van-checkbox-group>
    </div>

    <!-- 教官选择 -->
    <van-divider>选择教官</van-divider>
    <div class="staff_change">
      <div
        class="staff"
        v-for="(i, index) in staff_list"
        :key="i.staffId"
        :class="{ active: currentIndex === index }"
        @click="changeStafff(i, index)"
      >
        <div class="img">
          <img :src="i.staffAvatar" alt="" />
        </div>
        <div class="introduce">
          <div class="dh"><b>代号:</b>{{ i.staffName }}</div>
          <b>简介:</b>{{ i.staffInfo }}
        </div>
        <div class="more">更多介绍>></div>
      </div>
    </div>

    <!-- 协议 -->
    <div class="xy">
      <van-checkbox v-model="checked" checked-color="#ee0a24"
        >已阅读并同意<span><<天狼教育夏令营家长委托协议>></span></van-checkbox
      >
    </div>

    <!-- 用户备注 -->
    <van-divider>报名备注</van-divider>
    <van-field
      v-model="form.userRemarks"
      rows="2"
      autosize
      label="备注"
      type="textarea"
      maxlength="50"
      placeholder="请输入备注"
      show-word-limit
    />

    <!-- 提交报名 -->
    <van-submit-bar button-text="提交报名" @submit="onSubmit" />

    <!-- 确认信息 -->
    <van-action-sheet v-model="show" title="确认报名信息">
      <div class="content">
        <van-divider
          :style="{
            color: '#1989fa',
            borderColor: '#1989fa',
            padding: '0 16px',
          }"
          >参加日期</van-divider
        >
        <van-cell :value="changeDate">
          <!-- 使用 title 插槽来自定义标题 -->
          <template #title>
            <span class="custom-title">您选择的日期:</span>
          </template>
        </van-cell>
        <van-divider
          :style="{
            color: '#1989fa',
            borderColor: '#1989fa',
            padding: '0 16px',
          }"
          >参与者</van-divider
        >
        <van-cell>
          <!-- 使用 title 插槽来自定义标题 -->
          <template #title>
            <span class="custom-title">您选择的参与者:</span>
          </template>
          <span v-for="(i, n) in changeParticipant" :key="n"
            >{{ i }}&nbsp;&nbsp;</span
          >
        </van-cell>
        <van-divider
          :style="{
            color: '#1989fa',
            borderColor: '#1989fa',
            padding: '0 16px',
          }"
          >教官</van-divider
        >
        <van-cell :value="changeStaff">
          <!-- 使用 title 插槽来自定义标题 -->
          <template #title>
            <span class="custom-title">您选择的教官:</span>
          </template>
        </van-cell>
        <!-- 提交按钮 -->
        <van-button
          round
          size="large"
          color="linear-gradient(to right, #ff6034, #ee0a24)"
          @click="submitForm()"
        >
          确认报名
        </van-button>
      </div>
    </van-action-sheet>
  </div>
</template>

<script>
import { Dialog } from "vant";
import { Notify } from "vant";
import storage from "@/utils/storage";
export default {
  name: "EnrollPage",

  data() {
    return {
      // 活动id
      activity_id: 0,
      // 活动名称
      activity_name: "",
      // 用户id
      user_id: 1,
      // 日期列表
      date_list: [],
      // 参与职工列表
      staff_list: [],
      // 参与者列表
      participant_list: [],
      // 确定面板
      show: false,
      // 协议
      checked: false,
      // 选择参与者
      changeParticipant: "参与者",
      // 选择日期
      changeDate: "2023-01-01",
      // 选择参与职工
      changeStaff: "白鲨",
      currentIndex: -1,
      // 提交表单
      form: {
        timeId: 0,
        participantId: [],
        staffId: 0,
        user_id: 0,
        // 合同id
        contractId: 0,
        // 用户备注
        userRemarks: "",
      },
      // 剩余总人数
      totalSum: 0,
      // 选择参与者最大人数限制
      max_participant: 1,
    };
  },
  mounted() {
    this.user_id = storage.get("user").userId;
    // 赋值活动id
    this.activity_id = this.$route.query.activity_id;
    // 赋值活动名称
    this.activity_name = this.$route.query.activity_name;
    // 获取活动内容、日期、参与者、参与职工
    this.getActivityAndDate();
  },
  methods: {
    // 选择参与职工
    changeStafff(i, index) {
      console.log(i, index);
      this.currentIndex = index;
      this.changeStaff = i.staffName;
      this.form.staffId = i.staffId;
    },
    // 选择参与者
    changeParticipantt(i) {},
    // 选择日期
    changeDatee(i) {
      console.log(i);
      this.changeDate = i.timeContent;
      this.max_participant = i.personSum - i.enrollSum;
      // 判断可选人数是否为0
      if (this.max_participant == 0) {
        this.participant_list = [];
      } else {
        this.getActivityAndDate();
      }
    },
    // 获取活动内容、日期、参与者、参与职工
    async getActivityAndDate() {
      await this.$http
        .post(
          `/activity/getactivityanddate?activity_id=${this.activity_id}&user_id=${this.user_id}`
        )
        .then((res) => {
          console.log(res);
          this.staff_list = res.data.data.staff;
          this.participant_list = res.data.data.participant;
          this.date_list = res.data.data.timeList;
          // 剩余总人数
          let personSum = 0;
          let enrollSum = 0;
          for (let i = 0; i < res.data.data.timeList.length; i++) {
            personSum += res.data.data.timeList[i].personSum;
            enrollSum += res.data.data.timeList[i].enrollSum;
          }
          this.totalSum = personSum - enrollSum;
        });
    },
    // 确认报名
    async submitForm() {
      console.log(this.form);
      const formData = new FormData();
      formData.append("userId", this.form.user_id);
      formData.append("timeId", this.form.timeId);
      formData.append("staffId", this.form.staffId);
      formData.append("activityId", this.activity_id);
      formData.append("activityName", this.activity_name);
      formData.append("contractId", this.form.contractId);
      formData.append("userRemarks", this.form.userRemarks);

      for (let i = 0; i < this.form.participantId.length; i++) {
        formData.append("participantId", this.form.participantId[i]);
      }
      await this.$http
        .post(`/activity/enroll`, formData, {
          Headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          console.log(res);
          Dialog.alert({
            title: "报名反馈",
            message: res.data.data,
          }).then(() => {
            // on close
            this.show = false;
            if (res.data.data !== "参与者已经包过名了哟") {
              this.$router.push({
                path: "/home",
              });
            }
          });
        });
    },
    // 提交报名按钮
    onSubmit() {
      // 判断是否都选择了
      if (
        this.form.timeId == 0 ||
        this.checked == false ||
        this.form.participantId.length == 0 ||
        this.form.staffId == 0
      ) {
        // 提示少选
        Notify("日期/参与者/教官/协议未勾选，请勾选！！！");
      } else {
        this.show = true;
      }

      this.form.user_id = this.user_id;
      // 遍历参与者
      this.changeParticipant = [];
      for (let i = 0; i < this.form.participantId.length; i++) {
        // console.log(this.form.participantId[i]);
        for (let j = 0; j < this.participant_list.length; j++) {
          if (
            this.participant_list[j].participantId == this.form.participantId[i]
          ) {
            this.changeParticipant.push(
              this.participant_list[j].participantName
            );
          }
        }
      }
    },
  },
};
</script>

<style scoped>
#enrollpage {
  padding: 10px;
}
.name {
  font-size: 16px;
  font-weight: 600;
  padding-bottom: 10px;
  border-bottom: 1px solid rgb(226, 226, 226);
}
.wz {
  padding: 5px 0 10px 0;
  font-size: 16px;
  font-weight: 600;
}
.wz span {
  font-size: 12px;
  color: rgb(255, 93, 93);
}
.date span {
  margin-left: 10px;
  font-size: 12px;
  color: rgb(255, 93, 93);
}
/* .date {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.date span {
  font-size: 14px;
  text-align: center;
  border-radius: 25px;
  width: 40%;
  display: block;
  margin: 10px;
  padding: 5px;
  background-color: rgb(232, 232, 232);
}
.date span:hover {
  background-color: rgb(255, 220, 220);
} */

/* 选择教官 */
.staff_change {
  margin-bottom: 50px;
  width: 100%;
  display: flex;
  flex-direction: row;
  /* overflow-x: hidden; */
  overflow-y: hidden;
}
.staff_change .active {
  background-color: rgb(201, 255, 242) !important;
  color: red;
}

.staff_change .staff {
  min-width: 110px;
  margin: 5px;
  height: 220px;
  background-color: rgb(244, 244, 244);
}
/* .staff_change .staff:hover {
  background-color: rgb(0, 255, 34);
} */
.staff_change .staff .img {
  padding-top: 5px;
  text-align: center;
}
.staff_change .staff .img img {
  width: 80px;
  height: 80px;
  border-radius: 50px;
}
.staff_change .staff .introduce {
  overflow: hidden;
  width: 110px;
  height: 95px;
  font-size: 12px;
  text-align: center;
  /* 省略号 */
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 6;
}
.staff_change .staff .more {
  margin-top: 5px;
  width: 100%;
  text-align: center;
  font-size: 14px;
  color: red;
}

/* 协议 */
.xy {
  font-size: 14px;
}
.xy span {
  color: blue;
}

/* 确定面板 */
.content {
  padding: 16px 16px 26px;
}
</style>
