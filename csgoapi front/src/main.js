import Vue from 'vue'
import VueRouter from 'vue-router';
import VueTheMask from 'vue-the-mask';
import App from './App.vue'
import Home from './components/Home.vue';
import Grenade from './components/Grenade.vue';
import Heavy from './components/Heavy.vue';
import Pistol from './components/Pistol.vue';
import Rifle from './components/Rifle.vue';
import SubmachineGun from './components/SubmachineGun.vue';

Vue.use(VueTheMask);
Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: '/home',
      componet: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/grenades',
      component: Grenade
    },
    {
      path: '/heavys',
      component: Heavy
    },
    {
      path: '/pistols',
      component: Pistol
    },
    {
      path: '/rifles',
      component: Rifle
    },
    {
      path: '/smgs',
      component: SubmachineGun
    }
  ]
})

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
