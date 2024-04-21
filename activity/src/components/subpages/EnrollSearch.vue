<template>
  <div id="enrollsearch">
    <!-- 报名列表 -->
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      报名列表
    </van-divider>
    <van-card
      v-for="i in enroll_list"
      :key="i.enrollId"
      :title="i.activity.activityName"
      :thumb="i.headerList[0].headerPath"
      @click="sign(i)"
    >
      <template #tags>
        <van-tag plain type="danger">{{ i.time.timeContent }}</van-tag
        ><br />
        <van-tag plain type="danger" style="margin-top: 5px"
          >姓名:{{ i.participant.participantName }}</van-tag
        ><br />
        <van-tag plain type="danger" style="margin-top: 5px"
          >参与者证件:{{ i.participant.participantCard }}</van-tag
        >
      </template></van-card
    >
    <van-divider
      :style="{ color: '#1989fa', borderColor: '#1989fa', padding: '0 16px' }"
    >
      签到
    </van-divider>
    <!-- 展示要签到的活动 -->
    <van-steps :active="active" v-if="sign_in_show">
      <van-step>用户报名</van-step>
      <van-step>现场签到</van-step>
    </van-steps>
    <van-card
      v-if="sign_in_show"
      :title="sign_in.activity.activityName"
      :thumb="sign_in.headerList[0].headerPath"
    >
      <template #tags>
        <van-tag plain type="danger">{{ sign_in.time.timeContent }}</van-tag
        ><br />
        <van-tag plain type="danger" style="margin-top: 5px"
          >姓名:{{ sign_in.participant.participantName }}</van-tag
        ><br />
        <van-tag plain type="danger" style="margin-top: 5px"
          >参与者证件:{{ sign_in.participant.participantCard }}</van-tag
        >
      </template></van-card
    >

    <div class="btn" v-if="active == 1" @click="signInClick">
      {{ is_sign_in_wz }}
    </div>
  </div>
</template>

<script>
import storage from "../../utils/storage";
export default {
  name: "EnrollSearch",
  data() {
    return {
      // 用户id
      user: [],
      // 报名列表
      enroll_list: [],
      // 签到列表
      sign_in: {
        activity: {
          activityName: "",
        },
        headerList: [
          {
            headerPath: "",
          },
        ],
        time: {
          timeContent: "0",
        },
        participant: {
          participantName: "0",
          participantCard: 0,
        },
      },
      sign_in_show: false,
      active: 0,
      // 根据报名id查询签到表信息
      sign_in_info: [],
      // 是否签到文字
      is_sign_in_wz: "",
    };
  },
  mounted() {
    this.user = storage.get("user");
    // 获取报名列表
    this.getEnrollList();
  },
  methods: {
    // 点击签到按钮事件
    async signInClick() {
      if (this.is_sign_in_wz == "签到") {
        // 发送签到请求
        await this.$http
          .post(
            `/activity/signinclick?sign_in_id=${this.sign_in_info.signInId}`
          )
          .then((res) => {
            console.log(res);
            if (res.data.success == true) {
              this.is_sign_in_wz = "已签到";
            }
          });
      }
    },
    // 点击某个活动获取签到信息
    async sign(i) {
      console.log(i);
      this.sign_in = i;
      this.sign_in_show = true;
      i.time.isSignIn == 0 ? (this.active = 0) : (this.active = 1);
      // 发送报名id查询用户是否已经签到
      if (this.active == 1) {
        await this.$http
          .post(`/activity/issignin?enroll_id=${i.enrollId}`)
          .then((res) => {
            console.log(res);
            this.sign_in_info = res.data.data;
            if (res.data.data.isSignIn == 0) {
              this.is_sign_in_wz = "签到";
            } else {
              this.is_sign_in_wz = "已签到";
            }
          });
      }
    },
    async getEnrollList() {
      await this.$http
        .post(`/my/enrollinfo?user_id=${this.user.userId}`)
        .then((res) => {
          console.log(res);
          this.enroll_list = res.data.data;
        });
    },
  },
};
</script>

<style scoped>
.btn {
  color: white;
  border-radius: 50px;
  text-align: center;
  line-height: 70px;
  margin: 20px auto;
  width: 70px;
  height: 70px;
  background-color: rgb(78, 183, 225);
}
</style>
