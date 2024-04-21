<template>
  <div id="contract">
    <!--  -->
    <van-popup v-model="showPopup" style="width: 100%">
      <vue-esign
        ref="esign"
        :width="800"
        :height="300"
        :isCrop="isCrop"
        :lineWidth="lineWidth"
        :lineColor="lineColor"
        :bgColor.sync="bgColor"
      />
      <div class="btn">
        <van-button type="default" @click="handleReset" size="small"
          >重新签名</van-button
        >
        <van-button type="info" @click="handleGenerate" size="small"
          >确认签名</van-button
        >
      </div>
    </van-popup>

    <!-- <img :src="resultImg" alt="" /> -->

    <!--  -->
    <div class="list" v-for="i in enrollList" :key="i.enrollId">
      <div class="titme">{{ i.activity.activityName }}</div>
      <div class="date">活动日期:{{ i.time.timeContent }}</div>
      <div class="name">参与者姓名:{{ i.participant.participantName }}</div>
      <br />
      <van-button type="primary" @click="showContent(i)"
        >查看协议内容</van-button
      >&nbsp;
      <van-button type="info" @click="showPopups(i)" v-if="i.contractId == null"
        >点击进行电子签名</van-button
      >
      <van-button type="warning" @click="showresult(i)" v-else
        >点击查看电子签名</van-button
      >
    </div>

    <!-- 展示合同信息 -->
    <van-action-sheet v-model="show">
      <div class="content" v-html="agreementContent"></div>
    </van-action-sheet>

    <!-- 查看电子签名 -->
    <van-overlay :show="show1" @click="show1 = false">
      <div class="wrapper">
        <div class="block">
          <img :src="resultImg" alt="" />
        </div>
      </div>
    </van-overlay>
  </div>
</template>

<script>
import storage from "../../utils/storage";
export default {
  name: "Contract",
  data() {
    return {
      show: false,
      show1: false,
      showPopup: false,
      // 签名
      lineWidth: 6,
      lineColor: "#000000",
      bgColor: "",
      resultImg: "",
      isCrop: false,

      // user
      user: "",
      // 报名列表
      enrollList: [],
      // 协议内容
      agreementContent: "",
      // 报名id
      enrollId: 0,
      agreementId: 0,
    };
  },
  mounted() {
    this.user = storage.get("user");
    // 获取用户报名列表
    this.getUserEnrollList();
  },
  methods: {
    //获取用户报名列表
    async getUserEnrollList() {
      await this.$http
        .post(`/my/enrollinfo?user_id=${this.user.userId}`)
        .then((res) => {
          console.log(res);
          // for (let i = 0; i < res.data.data.length; i++) {
          //   // 去查询当前报名有没有签名
          //   console.log(res.data.data[i].enrollId);
          //   let enrollId = res.data.data[i].enrollId;
          //   this.$http
          //     .post(`/my/searchcontractis?enrollId=${enrollId}`)
          //     .then((res1) => {
          //       console.log(res1);
          //     });
          // }
          this.enrollList = res.data.data;
        });
    },

    // 签名
    handleReset() {
      this.$refs.esign.reset();
    },
    // 查看签名
    async showresult(i) {
      console.log(i);
      await this.$http
        .post(`/my/searchcontractinfoimg?contract_id=${i.contractId}`)
        .then((res) => {
          console.log(res);
          this.resultImg = res.data.data.autographImg;
          this.show1 = true;
        });
    },
    // 提交签名
    async handleGenerate() {
      this.$refs.esign
        .generate()
        .then((res) => {
          this.resultImg = res;
          // 提交签名
          let params = new FormData();
          params.append("enroll_id", this.enrollId);
          params.append("result_img", this.resultImg);
          params.append("agreement_id", this.agreementId);
          let config = {
            headers: {
              //添加请求头
              "Content-Type": "multipart/form-data",
            },
          };
          this.$http.post(`/my/uploadresultimg`, params, config).then((res) => {
            console.log(res);
            this.getUserEnrollList();
            this.showPopup = false;
          });
        })
        .catch((err) => {
          alert(err); // 画布没有签字时会执行这里 'Not Signned'
        });
    },

    //
    async showContent(i) {
      console.log(i);
      // 根据协议id查询协议内容并展示
      await this.$http
        .post(
          `/my/getagreementcontentbyid?agreement_id=${i.activity.agreementId}`
        )
        .then((res) => {
          console.log(res);
          this.agreementContent = res.data.data.agreementContent;
        });
      this.show = true;
    },
    showPopups(i) {
      console.log(i);
      this.enrollId = i.enrollId;
      this.agreementId = i.activity.agreementId;
      this.showPopup = true;
    },
  },
};
</script>

<style scoped>
.list {
  margin: 10px auto;
  padding: 10px;
  width: 90%;
  box-shadow: 0 0 10px rgb(215, 215, 215);
}

/* 合同信息 */
.content {
  line-height: 40px;
  padding: 16px 16px 20px;
}

.title {
  text-align: center;
  font-weight: 600;
}
.jia,
.yi,
.participant,
.time,
.info {
  font-weight: 600;
}

/* 签名 */
img {
  width: 100%;
}

.btn {
  width: 100%;
  background-color: #e5e5e5;
  display: flex;
  justify-content: space-evenly;
}

/* 查看签名 */
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.block {
  width: 50%;
  height: 120px;
  background-color: #fff;
}
.block img {
  width: 100%;
}
</style>
