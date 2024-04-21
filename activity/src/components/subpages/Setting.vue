<template>
  <div id="setting">
    <van-cell-group>
      <van-field
        v-model="old_password"
        type="password"
        placeholder="请输入原密码"
        label="原密码"
      />
      <van-field
        v-model="new_password"
        type="password"
        placeholder="请输入新密码"
        label="新密码"
      />
      <van-field
        v-model="new_password1"
        type="password"
        placeholder="请再次输入新密码"
        label="确认密码"
      />
    </van-cell-group>
    <!-- 提交按钮 -->
    <div class="btn">
      <van-button plain hairline type="primary" @click="btn"
        >修改密码</van-button
      >
    </div>
  </div>
</template>

<script>
import storage from "../../utils/storage";
export default {
  name: "Setting",
  data() {
    return {
      user: [],
      old_password: "",
      new_password: "",
      new_password1: "",
    };
  },
  mounted() {
    // 赋值user
    this.user = storage.get("user");
  },
  methods: {
    async btn() {
      await this.$http
        .post(
          `/my/editpassword?phone=${this.user.phone}&old_password=${this.old_password}&new_password=${this.new_password}&new_password1=${this.new_password1}`
        )
        .then((res) => {
          console.log(res);
          if (res.data.msg == "密码修改成功") {
            this.old_password = "";
            this.new_password = "";
            this.new_password1 = "";
            storage.removeAll();
            this.$router.push({
              path: "/home",
            });
          }
        });
    },
  },
};
</script>

<style scoped>
.btn {
  position: fixed;
  bottom: 50px;
  width: 100%;
  text-align: center;
}
</style>
