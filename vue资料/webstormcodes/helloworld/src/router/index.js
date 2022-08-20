import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

const originalPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
};


export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // },
    {
      path:'/login',
      name:'Login',
      //component: Login
      component: ()=>import('../views/Login')  //按需加载
    },{
      path:'/',
      redirect:'/index',
    },{
      path:'/index',
      name:'Index',
      component: ()=>import('../views/Index'),
      children:[
        {
          path: 'emps',
          name: 'Emps',
          component: () => import('../views/emp/Index')
        },
        {
          path: 'depts',
          name: 'Depts',
          component: () => import('../views/dept/Index')
        }
      ]
    }, {
      path:'*',//匹配所有路由
      component: ()=>import('../views/404')
    }
  ]
})
