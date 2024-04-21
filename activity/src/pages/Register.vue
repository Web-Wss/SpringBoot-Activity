<template>
  <div id="login">
    <div class="logo">
      <van-image
        width="100"
        height="100"
        :src="require('../assets/img/sirius.png')"
      />
      <br />
      <span>天狼特训</span>
    </div>
    <!-- 输入康 -->
    <van-form @submit="onSubmit">
      <van-field
        v-model="phone"
        name="phone"
        label="手机号"
        placeholder="请输入手机号"
        :rules="[{ required: true, message: '请填写用户名' }]"
      />
      <van-field
        v-model="password"
        type="password"
        name="password"
        label="密码"
        placeholder="请输入密码"
        :rules="[{ required: true, message: '请填写密码' }]"
      />
      <van-field
        v-model="parentname"
        name="parentname"
        label="姓名"
        placeholder="请输入您姓名"
        :rules="[{ required: true, message: '请输入您的姓名' }]"
      />
      <van-field
        v-model="username"
        name="username"
        label="用户名"
        placeholder="请输入您的用户名"
        :rules="[{ required: true, message: '请输入您的用户名' }]"
      />
      <van-field name="radio">
        <template #input>
          <van-radio-group v-model="radio" direction="horizontal">
            <van-radio name="1">用户</van-radio>
          </van-radio-group>
        </template>
      </van-field>

      <div style="margin: 36px">
        <van-button round block type="info" native-type="submit"
          >注册</van-button
        >
      </div>
    </van-form>
  </div>
</template>

<script>
import { Toast } from "vant";
export default {
  name: "Register",
  data() {
    return {
      radio: "1",
      username: "",
      password: "",
      parentname: "",
      phone: "",
    };
  },
  methods: {
    // 注册按钮
    async onSubmit() {
      if (this.radio == 1) {
        await this.$http
          .post(
            `/login/userregister?username=${this.username}&password=${this.password}&parentName=${this.parentname}&phone=${this.phone}`
          )
          .then((res) => {
            console.log(res.data.data == 1);
            if (res.data.data == 1) {
              Toast.loading({
                message: "注册成功，正在跳转登录页...",
                forbidClick: true,
              });
              setTimeout(() => {
                this.$router.push({
                  name: "login",
                });
              }, 2000);
            }
          });
      }
    },
  },
};
</script>

<style scoped>
.logo {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-end;
  width: 100%;
  height: 30vh;
}
.logo span {
  font-size: 18px;
  margin-bottom: 10px;
  color: #112983;
}
</style>
