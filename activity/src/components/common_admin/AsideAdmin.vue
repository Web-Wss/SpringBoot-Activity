<template>
  <div id="asideadmin">
    <el-row class="tac">
      <!-- <el-col>
        <el-menu
          default-active="1-1"
          class="el-menu-vertical-demo"
          collapse-transition
        >
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>数据看板</span>
            </template>
            <el-menu-item index="1-1">
              <router-link to="/homeadmin/data">数据看板</router-link>
            </el-menu-item>
            <el-menu-item index="1-2">
              <router-link to="/homeadmin/notice">通知管理</router-link>
            </el-menu-item>
            <el-menu-item index="1-3">
              <router-link to="/homeadmin/optionManage">意见反馈</router-link>
            </el-menu-item>
            <el-menu-item index="1-4">
              <router-link to="/homeadmin/lostarticlemanage"
                >失物招领</router-link
              >
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>活动策划</span>
            </template>
            <el-menu-item index="2-1">
              <router-link to="/homeadmin/classification">活动分类</router-link>
            </el-menu-item>
            <el-menu-item index="2-2">
              <router-link to="/homeadmin/date">时间管理</router-link>
            </el-menu-item>
            <el-menu-item index="2-3">
              <router-link to="/homeadmin/activitymanage">活动管理</router-link>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>合同管理</span>
            </template>
            <el-menu-item index="3-1">
              <router-link to="/homeadmin/contractmanage">合同列表</router-link>
            </el-menu-item>
            <el-menu-item index="3-2">
              <router-link to="/homeadmin/agreementmanage"
                >协议管理</router-link
              >
            </el-menu-item>
          </el-submenu>

          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>报名管理</span>
            </template>
            <el-menu-item index="4-1">
              <router-link to="/homeadmin/enrollmanage">报名管理</router-link>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="5">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>用户中心</span>
            </template>
            <el-menu-item index="5-1">
              <router-link to="/homeadmin/usermanage">用户管理</router-link>
            </el-menu-item>
            <el-menu-item index="5-2">
              <router-link to="/homeadmin/staffmanage">员工管理</router-link>
            </el-menu-item>
            <el-menu-item index="5-3">
              <router-link to="/homeadmin/participantmanage"
                >参与者管理</router-link
              >
            </el-menu-item>
            <el-menu-item index="5-4">
              <router-link to="/homeadmin/collectionmanage"
                >收藏管理</router-link
              >
            </el-menu-item>
          </el-submenu>

          <el-submenu index="6">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>云上相册</span>
            </template>
            <el-menu-item index="6-1">
              <router-link to="/homeadmin/albummanage">相册管理</router-link>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="7">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>签到管理</span>
            </template>
            <el-menu-item index="7-1">
              <router-link to="/homeadmin/signinmanage">签到管理</router-link>
            </el-menu-item>
          </el-submenu>

          <el-submenu index="8">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>权限管理</span>
            </template>
            <el-menu-item index="8-1">
              <router-link to="/homeadmin/rolemanage">角色管理</router-link>
            </el-menu-item>
            <el-menu-item index="8-2">
              <router-link to="/homeadmin/powermanage">权限管理</router-link>
            </el-menu-item>
            <el-menu-item index="8-2">
              <router-link to="/homeadmin/modulemanage">模块管理</router-link>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col> -->

      <!-- 遍历 -->
      <el-col>
        <el-menu
          default-active="1-1"
          class="el-menu-vertical-demo"
          collapse-transition
        >
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>数据看板</span>
            </template>
            <el-menu-item
              :index="i.modulePath"
              v-for="i in module1"
              :key="i.moduleId"
            >
              <router-link :to="i.modulePath">{{ i.moduleName }}</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>活动策划</span>
            </template>
            <el-menu-item
              :index="i.modulePath"
              v-for="i in module2"
              :key="i.moduleId"
            >
              <router-link :to="i.modulePath">{{ i.moduleName }}</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>合同管理</span>
            </template>
            <el-menu-item
              :index="i.modulePath"
              v-for="i in module3"
              :key="i.moduleId"
            >
              <router-link :to="i.modulePath">{{ i.moduleName }}</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>报名管理</span>
            </template>
            <el-menu-item
              :index="i.modulePath"
              v-for="i in module4"
              :key="i.moduleId"
            >
              <router-link :to="i.modulePath">{{ i.moduleName }}</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="5">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>用户中心</span>
            </template>
            <el-menu-item
              :index="i.modulePath"
              v-for="i in module5"
              :key="i.moduleId"
            >
              <router-link :to="i.modulePath">{{ i.moduleName }}</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="6">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>云上相册</span>
            </template>
            <el-menu-item
              :index="i.modulePath"
              v-for="i in module6"
              :key="i.moduleId"
            >
              <router-link :to="i.modulePath">{{ i.moduleName }}</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="7">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>签到管理</span>
            </template>
            <el-menu-item
              :index="i.modulePath"
              v-for="i in module7"
              :key="i.moduleId"
            >
              <router-link :to="i.modulePath">{{ i.moduleName }}</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="8">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>权限管理</span>
            </template>
            <el-menu-item
              :index="i.modulePath"
              v-for="i in module8"
              :key="i.moduleId"
            >
              <router-link :to="i.modulePath">{{ i.moduleName }}</router-link>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-col>

      
    </el-row>
  </div>
</template>

<script>
import storage from "../../utils/storage";
export default {
  name: "AsideAdmin",
  data() {
    return {
      staff: {},
      module: [],
      // 数据看板
      module1: [],
      // 活动策划
      module2: [],
      // 合同管理
      module3: [],
      // 报名管理
      module4: [],
      // 用户中心
      module5: [],
      // 云上相册
      module6: [],
      // 签到管理
      module7: [],
      // 权限管理
      module8: [],
    };
  },
  mounted() {
    this.staff = storage.get("staff");
    // 获取当前员工的模块列表
    this.getModuleList();
  },
  methods: {
    async getModuleList() {
      let res1 = [];
      await this.$http
        .post(`/roleadmin/getmodulelistbyroleid?role_id=${this.staff.roleId}`)
        .then((res) => {
          console.log(res);
          res1 = res.data.data;
        });
      console.log("获取模块列表", res1);
      for (let i = 0; i < res1.length; i++) {
        if (
          res1[i].moduleName == "数据看板" ||
          res1[i].moduleName == "通知管理" ||
          res1[i].moduleName == "意见反馈" ||
          res1[i].moduleName == "失物招领"
        ) {
          this.module1.push(res1[i]);
        } else if (
          res1[i].moduleName == "活动分类" ||
          res1[i].moduleName == "时间管理" ||
          res1[i].moduleName == "活动管理"
        ) {
          this.module2.push(res1[i]);
        } else if (
          res1[i].moduleName == "合同列表" ||
          res1[i].moduleName == "协议管理"
        ) {
          this.module3.push(res1[i]);
        } else if (res1[i].moduleName == "报名管理") {
          this.module4.push(res1[i]);
        } else if (
          res1[i].moduleName == "用户管理" ||
          res1[i].moduleName == "员工管理" ||
          res1[i].moduleName == "参与者管理" ||
          res1[i].moduleName == "收藏管理"
        ) {
          this.module5.push(res1[i]);
        } else if (res1[i].moduleName == "相册管理") {
          this.module6.push(res1[i]);
        } else if (res1[i].moduleName == "签到管理") {
          this.module7.push(res1[i]);
        } else if (
          res1[i].moduleName == "角色管理" ||
          res1[i].moduleName == "权限管理" ||
          res1[i].moduleName == "模块管理"
        ) {
          this.module8.push(res1[i]);
        }
      }
    },
  },
};
</script>

<style scoped>
.tac {
  width: 100%;
}
.el-col-20 {
  width: 100%;
}
</style>
