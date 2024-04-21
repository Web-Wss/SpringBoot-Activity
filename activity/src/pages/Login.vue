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
        v-model="username"
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
      <van-field name="radio">
        <template #input>
          <van-radio-group v-model="radio" direction="horizontal">
            <van-radio name="1">用户</van-radio>
            <van-radio name="2">职工</van-radio>
          </van-radio-group>
        </template>
      </van-field>

      <div style="margin: 36px">
        <van-button round block type="info" native-type="submit"
          >登录</van-button
        >
      </div>
    </van-form>

    <!-- 忘记密码，注册账号 -->
    <div class="register">
      <span @click="forget">忘记密码</span>
      <span @click="goRegister">还没有账号？</span>
    </div>
  </div>
</template>

<script>
import { Notify } from "vant";
import { Toast } from "vant";
import storage from "../utils/storage";
export default {
  name: "Login",
  data() {
    return {
      // radio
      radio: "1",
      username: "",
      password: "",
    };
  },
  methods: {
    // 忘记密码
    forget() {
      Toast("请联系系统管理员");
    },
    // 去注册
    goRegister() {
      this.$router.push({
        name: "register",
      });
    },
    async onSubmit() {
      if (this.radio == 1) {
        // 用户登录请求
        await this.$http
          .post(
            `/login/userlogin?phone=${this.username}&password=${this.password}`
          )
          .then((res) => {
            console.log(res);
            if (res.data.data != null) {
              storage.set("token", res.data.data.token);
              storage.set("user", res.data.data.users);
              storage.set("role", res.data.data.role);
              // 跳转到my
              Toast.loading({
                message: "登录成功...",
                forbidClick: true,
              });
              setTimeout(() => {
                // this.$router.push({
                //   path: "/my",
                // });
                this.$router.push(
                  this.$router.history.current.query.redirect || "/my"
                );
              }, 2000);
            } else {
              // 登录失败
              Notify("账号或密码错误");
            }
          });
      } else {
        // 职工登录请求
        await this.$http
          .post(
            `/login/stafflogin?phone=${this.username}&password=${this.password}`
          )
          .then((res) => {
            console.log(res);
            if (res.data.data != null) {
              storage.set("token", res.data.data.token);
              storage.set("staff", res.data.data.staff);
              storage.set("role", res.data.data.role);
              Toast.loading({
                message: "登录成功...",
                forbidClick: true,
              });
              setTimeout(() => {
                // this.$router.push({
                //   path: "/my",
                // });
                this.$router.push({
                  name: "data",
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

.register {
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  width: 100%;
  height: 20vh;
  /* background-color: aqua; */
}
.register span {
  cursor: pointer;
  font-size: 14px;
  color: rgb(89, 116, 247);
}
</style>
