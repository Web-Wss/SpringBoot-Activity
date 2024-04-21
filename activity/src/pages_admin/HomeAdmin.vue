<template>
  <div id="home_admin">
    <el-container>
      <el-header><HeaderAdmin /></el-header>
      <el-container>
        <el-aside width="220px"><AsideAdmin /></el-aside>
        <el-main>
          <el-card class="box-card">
            <router-view />
          </el-card>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import HeaderAdmin from "../components/common_admin/HeaderAdmin.vue";
import AsideAdmin from "../components/common_admin/AsideAdmin.vue";
export default {
  name: "HomeAdmin",
  components: { HeaderAdmin, AsideAdmin },
  data() {
    return {};
  },
};
</script>

<style scoped>
.el-header,
.el-footer {
  background-color: #ffffff;
  color: rgb(51, 51, 51);
  line-height: 60px;
}

.el-aside {
  background-color: #ffffff;
  color: #333;
  text-align: center;
  height: calc(100vh - 60px);
}

.el-main {
  background-color: #f2f2f2;
  color: #333;
  /* text-align: center; */
}

/* 卡片 */
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}

.box-card {
  overflow-y: scroll;
  width: 99%;
  height: calc(100vh - 120px);
}
</style>
