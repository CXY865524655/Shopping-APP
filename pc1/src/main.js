import Vue from 'vue'
import App from './App.vue'
import router from '@/config/router.js'
import store from '@/store'
import '@/config/elementUI.js'
import 'element-ui/lib/theme-chalk/index.css'
import api from '@/config/API'
import axios from 'axios'
import 'reset-css'
import '@/config/rem.js'
import '@/config/vant.js'
import './icons'   // 引入svg-icon 标签  可以从 Iconfont（阿里巴巴） 中下载
import { setCookie, getCookie, removeCookie } from '@/assets/js/cookie'
Vue.config.productionTip = false
Vue.prototype.$api = api
Vue.prototype.$axios = axios
Vue.prototype.$cookieStore = {
  setCookie,
  getCookie,
  removeCookie
}

router.beforeEach((to, from , next) => {   
  if ( to.matched.some( record => record.meta.requiresAuth)) {   
      if(!localStorage.getItem('token') || localStorage.getItem('token').trim()==='') {
          next({
            path: '/Login',
            query: {
              returnURL: to.path
            }
          }) // 确保一定要调用 next()
        }
        next()
        return 
  }else {
    next()
  }
})
var vue = new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')

export default vue

