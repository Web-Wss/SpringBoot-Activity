// 引入vue-router
import VueRouter from "vue-router";
// 引入store
import store from "../store";
// 引入工具:storage.js
import storage from "../utils/storage";

// 引入页面组件
// 用户端
import Home from "../pages/Home.vue";
import Activity from "../pages/Activity.vue";
import My from "../pages/My.vue";
import ActivityDetails from "../components/subpages/ActivityDetails.vue";
import Bind from "../components/subpages/Bind.vue";
import EnrollSearch from "../components/subpages/EnrollSearch.vue";
import Guide from "../components/subpages/Guide.vue";
import MyCollection from "../components/subpages/MyCollection.vue";
import MyEnroll from "../components/subpages/MyEnroll.vue";
import EnrollPage from "../components/subpages/EnrollPage.vue";

// 管理端
import HomeAdmin from "../pages_admin/HomeAdmin.vue";

const router = new VueRouter({
  routes: [
    {
      path: "/",
      redirect: "home",
    },
    {
      name: "home",
      path: "/home",
      component: Home,
      meta: {
        title: "首页",
        requireAuth: false,
      },
    },
    {
      name: "activity",
      path: "/activity",
      component: Activity,
      meta: {
        title: "活动",
        requireAuth: false,
      },
    },
    {
      name: "my",
      path: "/my",
      component: My,
      meta: {
        title: "我的",
        requireAuth: false,
      },
    },
    {
      name: "activitydetails",
      path: "/activitydetails",
      component: ActivityDetails,
      meta: {
        title: "活动详情",
        requireAuth: false,
      },
    },
    {
      name: "bind",
      path: "/bind",
      component: Bind,
      meta: {
        title: "成员绑定",
        requireAuth: false,
      },
    },
    {
      name: "enrollsearch",
      path: "/enrollsearch",
      component: EnrollSearch,
      meta: {
        title: "报名查询",
        requireAuth: false,
      },
    },
    {
      name: "guide",
      path: "/guide",
      component: Guide,
      meta: {
        title: "报名指南",
        requireAuth: false,
      },
    },
    {
      name: "mycollection",
      path: "/mycollection",
      component: MyCollection,
      meta: {
        title: "我的收藏",
        requireAuth: false,
      },
    },
    {
      name: "myenroll",
      path: "/myenroll",
      component: MyEnroll,
      meta: {
        title: "我的报名",
        requireAuth: false,
      },
    },
    {
      name: "enrollpage",
      path: "/enrollpage",
      component: EnrollPage,
      meta: {
        title: "报名界面",
        requireAuth: false,
      },
    },

    // 后端
    {
      name: "homeadmin",
      path: "/homeadmin",
      component: HomeAdmin,
      meta: {
        title: "主页",
        requireAuth: false,
      },
    },
  ],
});

router.afterEach((to, from) => {
  document.title = to.meta.title;
});

export default router;
