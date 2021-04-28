import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'
import vuetify from '@/plugins/vuetify'
import router from './rotues/routes'
import  _ from 'lodash'

Vue.config.productionTip = false
Vue.use(VueRouter)

Vue.prototype._ = _

new Vue({
  render: h => h(App),
  router,
  vuetify,
}).$mount('#app')
