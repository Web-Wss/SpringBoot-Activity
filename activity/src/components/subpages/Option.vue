<template>
  <div id="option">
    <van-field
      v-model="message"
      rows="10"
      autosize
      type="textarea"
      maxlength="128"
      placeholder="将您的宝贵意见留下来"
      show-word-limit
    />

    <!-- 提交按钮 -->
    <div class="btn">
      <van-button plain hairline type="primary" @click="btn"
        >意见提交</van-button
      >
    </div>
  </div>
</template>

<script>
import storage from "../../utils/storage.js";
import { Dialog } from "vant";
export default {
  name: "Option",
  data() {
    return {
      user: [],
      message: "",
    };
  },
  mounted() {
    this.user = storage.get("user");
  },
  methods: {
    async btn() {
      console.log(this.message);
      await this.$http
        .post(`/my/option?user_id=${this.user.userId}&message=${this.message}`)
        .then((res) => {
          console.log(res);
          if (res.data.success == true) {
            // 提示成功
            Dialog.alert({
              title: "提交反馈",
              message: "反馈提交成功",
            }).then(() => {
              // on close
              this.message = "";

              this.$router.push({
                path: "/my",
              });
            });
          }
        });
    },
  },
};
</script>

<style scoped>
#option {
  width: 100%;
  height: 100vh;
  background-color: rgb(241, 241, 241);
}

.btn {
  position: fixed;
  bottom: 50px;
  width: 100%;
  text-align: center;
}
</style>
