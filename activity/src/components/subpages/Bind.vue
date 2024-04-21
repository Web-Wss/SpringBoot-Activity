<template>
  <div id="bind">
    <!-- 参与者列表 -->
    <van-divider>参与者列表</van-divider>
    <van-swipe-cell
      v-for="i in participant_list"
      :key="i.participantId"
      v-if="participant_list.length"
    >
      <van-cell
        :border="false"
        :title="'姓名:' + i.participantName"
        :value="'证件:' + i.participantCard"
      />
      <template #right>
        <van-button square type="danger" text="删除" @click="deletee(i)" />
      </template>
    </van-swipe-cell>
    <!-- 添加参与者按钮 -->
    <div class="btn" v-show="btn">
      <van-button @click="bind()" round type="danger" class="bottom-button"
        >添加参与者信息</van-button
      >
    </div>

    <!-- 绑定参与者 -->
    <van-empty v-show="!btn" description="当前您还没有添加任何参与者哦">
      <van-button @click="bind()" round type="danger" class="bottom-button"
        >添加参与者信息</van-button
      >
    </van-empty>

    <!-- 遮罩层 -->
    <van-overlay :show="show">
      <div class="wrapper" @click.stop>
        <div class="block">
          <div class="logo">
            <img
              src="https://img1.baidu.com/it/u=1637591443,2879807930&fm=253&fmt=auto&app=138&f=JPEG?w=462&h=462"
              alt=""
            />
          </div>
          <div class="content">
            <div class="title">为了更好的活动服务<br />请添加参与者</div>
            <div class="form">
              <div class="name">参与者姓名</div>
              <input type="text" v-model="form.name" />
              <div class="card">参与者证件</div>
              <input type="text" v-model="form.card" />
              <div class="card">身高</div>
              <input type="text" v-model="form.height" />
              <div class="card">体重</div>
              <input type="text" v-model="form.weight" />
              <div class="card">性别</div>
              <input type="text" v-model="form.sex" />
              <!-- 绑定按钮 -->
              <div class="bind_btn">
                <van-button type="primary" @click="addParticipant"
                  >绑定</van-button
                >
              </div>
            </div>
            <div class="close" @click="zz()">❌</div>
          </div>
        </div>
      </div>
    </van-overlay>

    <!-- 底部 -->
    <TabBar />
  </div>
</template>

<script>
import TabBar from "../common/TabBar.vue";
import storage from "../../utils/storage";
export default {
  name: "Bind",
  components: { TabBar },
  data() {
    return {
      // 用户user
      user: [],
      // 参与者列表
      participant_list: [],
      participant_id: [],
      // 是否展示按钮
      btn: true,
      // 遮罩层
      show: false,
      // 参与者信息
      form: {
        name: "",
        height: "",
        weight: "",
        card: "",
        sex: "",
      },
    };
  },
  mounted() {
    this.user = storage.get("user");
    // 根据用户id获取参与者
    this.getParticipantList();
  },
  methods: {
    // 根据用户id获取参与者列表
    async getParticipantList() {
      await this.$http
        .post(`/my/participantlist?user_id=${this.user.userId}`)
        .then((res) => {
          console.log(res);
          this.participant_list = res.data.data;
          for (let i = 0; i < res.data.data.length; i++) {
            this.participant_id.push(res.data.data[i].participantId);
          }
        });
    },
    // 删除参与者
    async deletee(i) {
      console.log("要删除的参与者id", i.participantId);
      console.log("当前用已经绑定的参与者id", this.participant_id);
      const filteredArr = this.participant_id.filter(
        (item) => item !== i.participantId
      );
      console.log("处理后的数组", filteredArr);
      const str = JSON.stringify(filteredArr);
      const result = "[" + str.slice(1, -1) + "]";
      const participant_id1 = encodeURIComponent(result);
      console.log("处理后的数组", participant_id1);
      await this.$http
        .post(
          `/my/deleteparticipant?user_id=${this.user.userId}&participant_id=${participant_id1}&participantId=${i.participantId}`
        )
        .then((res) => {
          console.log(res);
          if (res.data.success == true) {
            console.log(1);
            this.participant_id = [];
            console.log(this.participant_id);
            this.getParticipantList();
          }
        });
    },
    // 绑定参与者
    async addParticipant() {
      const formData = new FormData();
      formData.append("name", this.form.name);
      formData.append("card", this.form.card);
      formData.append("height", this.form.height);
      formData.append("weight", this.form.weight);
      formData.append("sex", this.form.sex);
      formData.append("user_id", this.user.userId);
      console.log(1);
      await this.$http
        .post(`/my/bind`, formData, {
          Headers: { "Content-Type": "multipart/form-data" },
        })
        .then((res) => {
          console.log(res);
          this.form.name = "";
          this.form.card = "";
          this.form.height = "";
          this.form.weight = "";
          this.form.sex = "";
          this.show = false;
          // 获取参与者
          this.participant_id = [];
          this.getParticipantList();
        });
    },
    zz() {
      this.show = false;
    },
    // 点击按钮
    bind() {
      this.show = true;
    },
  },
};
</script>

<style scoped>
.bottom-button {
  width: 160px;
  height: 40px;
}

.btn {
  text-align: center;
  width: 50%;
  margin: 60px auto;
}

/* 遮罩层 */
.wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.block {
  position: relative;
  width: 220px;
  height: 550px;
  background-color: #4c4c4c;
}
.block .logo {
  position: relative;
  z-index: 1;
  width: 60px;
  height: 60px;
  margin: 0 auto;
  border-radius: 10px;
  background-color: aqua;
}
.block .logo img {
  width: 100%;
  border-radius: 10px;
}
.block .content {
  margin-top: -25px;
  width: 100%;
  height: 470px;
  border-radius: 10px;
  border: 1px solid #00a2e6;
  background-color: #fff;
}
.block .content .title {
  margin: 40px auto;
  text-align: center;
  font-size: 14px;
}
.block .content .form .name,
.card {
  font-size: 12px;
  text-align: center;
  color: #9d9d9d;
}
.block .content .form input {
  text-align: center;
  display: block;
  margin: 10px auto;
  width: 80%;
  border: 0;
  border-bottom: 1px solid red;
  /* margin-bottom: 20px; */
}
.form .bind_btn {
  width: 60px;
  margin: 0 auto;
}
.close {
  position: absolute;
  bottom: -20px;
  left: 90px;
  text-align: center;
  line-height: 40px;
  font-size: 25px;
  width: 40px;
  height: 40px;
  border-radius: 50px;
  background-color: rgb(255, 255, 255);
}
</style>
