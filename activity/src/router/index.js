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
import Login from "../pages/Login.vue";
import Register from "../pages/Register.vue";
import ActivityDetails from "../components/subpages/ActivityDetails.vue";
import Bind from "../components/subpages/Bind.vue";
import EnrollSearch from "../components/subpages/EnrollSearch.vue";
import Guide from "../components/subpages/Guide.vue";
import MyCollection from "../components/subpages/MyCollection.vue";
import EnrollPage from "../components/subpages/EnrollPage.vue";
import Option from "../components/subpages/Option.vue";
import Setting from "../components/subpages/Setting.vue";
import LostArticle from "../components/subpages/LostArticle.vue";
import Album from "../components/subpages/Album.vue";
import Picture from "../components/subpages/Picture.vue";
import Contract from "../components/subpages/Contract.vue";

// 管理端
import HomeAdmin from "../pages_admin/HomeAdmin.vue";
import Data from "../components/subpages_admin/Data.vue";
import Notice from "../components/subpages_admin/Notice.vue";
import OptionManage from "../components/subpages_admin/Option.vue";
import LostArticleManage from "../components/subpages_admin/LostArticleManage.vue";
import Classification from "../components/subpages_admin/Classification.vue";
import Date from "../components/subpages_admin/Date.vue";
import ActivityManage from "../components/subpages_admin/ActivityManage.vue";
import EnrollManage from "../components/subpages_admin/EnrollManage.vue";
import ContractManage from "../components/subpages_admin/ContractManage.vue";
import AgreementManage from "../components/subpages_admin/AgreementManage.vue";
import UserManage from "../components/subpages_admin/UserManage.vue";
import StaffManage from "../components/subpages_admin/StaffManage.vue";
import ParticipantManage from "../components/subpages_admin/ParticipantManage.vue";
import CollectionManage from "../components/subpages_admin/CollectionManage.vue";
import AlbumManage from "../components/subpages_admin/AlbumManage.vue";
import PictureManage from "../components/subpages_admin/PictureManage.vue";
import SignInManage from "../components/subpages_admin/SignInManage.vue";
import RoleManage from "../components/subpages_admin/RoleManage.vue";
import PowerManage from "../components/subpages_admin/PowerManage.vue";
import ModuleManage from "../components/subpages_admin/ModuleManage.vue";

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
        requireAuth: true,
      },
    },
    {
      name: "login",
      path: "/login",
      component: Login,
      meta: {
        title: "登录",
        requireAuth: false,
      },
    },
    {
      name: "register",
      path: "/register",
      component: Register,
      meta: {
        title: "注册",
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
        requireAuth: true,
      },
    },
    {
      name: "enrollsearch",
      path: "/enrollsearch",
      component: EnrollSearch,
      meta: {
        title: "报名查询",
        requireAuth: true,
      },
    },
    // {
    //   name: "guide",
    //   path: "/guide",
    //   component: Guide,
    //   meta: {
    //     title: "报名指南",
    //     requireAuth: false,
    //   },
    // },
    {
      name: "album",
      path: "/album",
      component: Album,
      meta: {
        title: "在线相册",
        requireAuth: true,
      },
    },
    {
      name: "picture",
      path: "/picture",
      component: Picture,
      meta: {
        title: "图片列表",
        requireAuth: true,
      },
    },
    {
      name: "mycollection",
      path: "/mycollection",
      component: MyCollection,
      meta: {
        title: "我的收藏",
        requireAuth: true,
      },
    },

    {
      name: "enrollpage",
      path: "/enrollpage",
      component: EnrollPage,
      meta: {
        title: "报名界面",
        requireAuth: true,
      },
    },
    {
      name: "contract",
      path: "/contract",
      component: Contract,
      meta: {
        title: "合同信息",
        requireAuth: true,
      },
    },
    {
      name: "option",
      path: "/option",
      component: Option,
      meta: {
        title: "意见反馈",
        requireAuth: true,
      },
    },
    {
      name: "lostarticle",
      path: "/lostarticle",
      component: LostArticle,
      meta: {
        title: "失物招领",
        requireAuth: true,
      },
    },
    {
      name: "setting",
      path: "/setting",
      component: Setting,
      meta: {
        title: "密码修改",
        requireAuth: true,
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
      children: [
        {
          name: "data",
          path: "/homeadmin/data",
          component: Data,
          meta: {
            title: "数据看板",
            requireAuth: false,
          },
        },
        {
          name: "notice",
          path: "/homeadmin/notice",
          component: Notice,
          meta: {
            title: "通知管理",
            requireAuth: false,
          },
        },
        {
          name: "optionManage",
          path: "/homeadmin/optionManage",
          component: OptionManage,
          meta: {
            title: "意见反馈",
            requireAuth: false,
          },
        },
        {
          name: "lostarticlemanage",
          path: "/homeadmin/lostarticlemanage",
          component: LostArticleManage,
          meta: {
            title: "失物招领",
            requireAuth: false,
          },
        },
        {
          name: "classification",
          path: "/homeadmin/classification",
          component: Classification,
          meta: {
            title: "活动分类",
            requireAuth: false,
          },
        },
        {
          name: "date",
          path: "/homeadmin/date",
          component: Date,
          meta: {
            title: "时间管理",
            requireAuth: false,
          },
        },
        {
          name: "activitymanage",
          path: "/homeadmin/activitymanage",
          component: ActivityManage,
          meta: {
            title: "活动管理",
            requireAuth: false,
          },
        },
        {
          name: "contractmanage",
          path: "/homeadmin/contractmanage",
          component: ContractManage,
          meta: {
            title: "合同列表",
            requireAuth: false,
          },
        },
        {
          name: "agreementmanage",
          path: "/homeadmin/agreementmanage",
          component: AgreementManage,
          meta: {
            title: "协议管理",
            requireAuth: false,
          },
        },

        {
          name: "enrollmanage",
          path: "/homeadmin/enrollmanage",
          component: EnrollManage,
          meta: {
            title: "报名管理",
            requireAuth: false,
          },
        },
        {
          name: "usermanage",
          path: "/homeadmin/usermanage",
          component: UserManage,
          meta: {
            title: "用户管理",
            requireAuth: false,
          },
        },
        {
          name: "staffmanage",
          path: "/homeadmin/staffmanage",
          component: StaffManage,
          meta: {
            title: "员工管理",
            requireAuth: false,
          },
        },
        {
          name: "participantmanage",
          path: "/homeadmin/participantmanage",
          component: ParticipantManage,
          meta: {
            title: "参与者管理",
            requireAuth: false,
          },
        },
        {
          name: "collectionmanage",
          path: "/homeadmin/collectionmanage",
          component: CollectionManage,
          meta: {
            title: "收藏管理",
            requireAuth: false,
          },
        },
        {
          name: "albumManage",
          path: "/homeadmin/albumManage",
          component: AlbumManage,
          meta: {
            title: "相册管理",
            requireAuth: false,
          },
        },
        {
          name: "pictureManage",
          path: "/homeadmin/pictureManage",
          component: PictureManage,
          meta: {
            title: "图片管理",
            requireAuth: false,
          },
        },
        {
          name: "signinmanage",
          path: "/homeadmin/signinmanage",
          component: SignInManage,
          meta: {
            title: "签到管理",
            requireAuth: false,
          },
        },
        {
          name: "rolemanage",
          path: "/homeadmin/rolemanage",
          component: RoleManage,
          meta: {
            title: "角色管理",
            requireAuth: false,
          },
        },
        {
          name: "powermanage",
          path: "/homeadmin/powermanage",
          component: PowerManage,
          meta: {
            title: "权限管理",
            requireAuth: false,
          },
        },
        {
          name: "modulemanage",
          path: "/homeadmin/modulemanage",
          component: ModuleManage,
          meta: {
            title: "模块管理",
            requireAuth: false,
          },
        },
      ],
    },
  ],
});

// 获取原型对象push函数
const originalPush = VueRouter.prototype.push;

// 获取原型对象replace函数
const originalReplace = VueRouter.prototype.replace;

// 修改原型对象中的push函数
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch((err) => err);
};

// 修改原型对象中的replace函数
VueRouter.prototype.replace = function replace(location) {
  return originalReplace.call(this, location).catch((err) => err);
};

router.beforeEach((to, from, next) => {
  // 设置路由守卫，在进页面之前，判断有token，才进入页面，否则返回登录页面
  if (storage.get("token")) {
    store.commit("setToken", storage.get("token"));
  }
  // 判断要去的路由有没有requireAuth
  if (to.matched.some((r) => r.meta.requireAuth)) {
    if (store.state.token) {
      next(); //有token，进行request请求，后台还会验证token
    } else {
      router.push({
        path: "/login",
        // 将刚刚要去的路由path作为参数，方便直接跳转
        query: { redirect: to.fullPath },
      });
    }
  } else {
    next(); //如果无需token，放行
  }
});

router.afterEach((to, from) => {
  document.title = to.meta.title;
});

export default router;
