import Vue from 'vue'
import Router from 'vue-router'
import User from "../components/User";
import Dept from "../components/Dept";

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      redirect: '/user',
    },
    {
      path:'/user',
      name:'User',
      component: User
    },
    {
      path:'/dept',
      name:'Dept',
      component: Dept
    }
  ]
})
