import Vue from 'vue'
import App from './App.vue'
import router from './router'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import Api from '@/api/api'

Vue.config.productionTip = false
Vue.use(BootstrapVue)

Vue.prototype.$api 

new Vue({
  router,
  Api,
  render: h => h(App)
}).$mount('#app')
