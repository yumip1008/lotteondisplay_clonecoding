import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import vuetify from '@/plugins/vuetify'
import router from './rotues/routes'
import store from '@/stores'
import VueLazyLoad from 'vue-lazyload'
import  _ from 'lodash'

Vue.config.productionTip = false
Vue.use(VueRouter)
Vue.use(VueLazyLoad, {
  preLoad : 2, // 로딩 줄 수
  attempt: 1, // 시도 횟수
  listenEvents: ['scroll']
})

Vue.prototype._ = _

new Vue({
  render: h => h(App),
  router,
  store,
  vuetify,
}).$mount('#app')
