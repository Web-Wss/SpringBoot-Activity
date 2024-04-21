<template>
  <div id="headeradmin">
    <div class="left">
      <img src="../../assets/img/logo.png" alt="" />
    </div>
    <div class="center">天狼教育活动管理平台</div>
    <div class="right">
      <div class="img">
        <img :src="staff.staffAvatar" alt="" />
      </div>
      <!-- <div class="name">管理员</div> -->
      <el-dropdown trigger="click">
        <span class="el-dropdown-link" style="color: red">
          {{ staff.staffName
          }}<i class="el-icon-caret-bottom el-icon--right"></i>
        </span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item class="clearfix">
            <span @click="logout">退出登录</span>
            <el-badge class="mark" />
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
import storage from "../../utils/storage";
export default {
  name: "HeaderAdmin",
  data() {
    return {
      staff: {},
    };
  },
  mounted() {
    this.staff = storage.get("staff");
  },
  methods: {
    logout() {
      console.log("退出登录");
      storage.removeAll();
      this.$router.push({
        name: "login",
      });
    },
  },
};
</script>

<style scoped>
#headeradmin {
  width: 100%;
  height: 60px;
  display: flex;
}

.left {
  width: 30%;
  text-align: left;
  /* padding-left: 60px; */
}
.left img {
  width: 200px;
  height: 60px;
}
.center {
  font-size: 20px;
  color: rgb(0, 13, 255);
  width: 40%;
  text-align: center;
  font-weight: 600;
}
.right {
  width: 30%;
  display: flex;
  justify-content: flex-end;
}
.right img {
  padding: 10px;
  width: 40px;
  height: 40px;
  border-radius: 50px;
}
.right .name {
  font-size: 14px;
  color: red;
  line-height: 60px;
  text-align: right;
}

.item {
  margin-top: 10px;
  margin-right: 40px;
}
</style>
