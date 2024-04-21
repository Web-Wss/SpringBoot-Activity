import Vue from "vue";
import App from "./App.vue";

// 引入图标库
import "../src/assets/css/iconfont/iconfont.css";
import "../src/assets/css/iconfont/iconfont";

import "../src/assets/css/iconfont/iconfont";

// 引入vue-router
import VueRouter from "vue-router";
Vue.use(VueRouter);

import vueEsign from "vue-esign";
Vue.use(vueEsign);

// 引入vant
import Vant from "vant";
import "vant/lib/index.css";
Vue.use(Vant);

// 引入Elementui
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
Vue.use(ElementUI);

// 引入echarts
import * as echarts from "echarts";
Vue.prototype.$echarts = echarts;

import router from "./router";
import store from "./store/index";

// 引入http
import http from "./utils/request";
Vue.prototype.$http = http;

// 引入qs
import qs from "qs";
Vue.prototype.qs = qs;

Vue.config.productionTip = false;

new Vue({
  render: (h) => h(App),
  router,
  store,
}).$mount("#app");
