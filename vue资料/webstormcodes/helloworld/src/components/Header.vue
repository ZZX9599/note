<template>
  <div>
    <div>
      <div class="container-fluid">
        <div class="row">
          <div class="col-sm-12">
            <h1 class="text-center text-info">欢迎进入员工管理系统首页 <span v-show="isLogin">欢迎: {{ admin.username }}</span></h1>
          </div>
          <div class="col-sm-12">
            <div style="float: right">
              <a href="javascript:;" @click="logout" v-show="isLogin">退出登录</a>
              <router-link v-show="!isLogin" :to="{name:'Login'}">立即登录</router-link>
            </div>
          </div>
        </div>
      </div>

      <div class="clearfix"></div>
      <div style="text-align: center">
        <a href="#/index/emps">员工管理</a>
        <a href="#/index/depts">部门管理</a>
        <a href="">用户管理</a>
        <a href="">订单管理</a>
      </div>

    </div>
  </div>
</template>

<script>
import instance from "../utils/request";

export default {
  name: "Header",
  data() {
    return {
      admin: {}
    }
  },
  methods: {
    logout() {
      let token = localStorage.getItem("token");
      //根据token删除redis中用户登录标记
      instance.delete("/token?token=" + token).then(res => {
        //this.$router.push({path:'/'});//切换到主页
        this.admin = {};
      });
    }
  },
  computed: {
    isLogin() {
      return this.admin.username;
    },
  },
  created() {
    //从localStorage获取token
    let token = localStorage.getItem("token");
    //发送axios 根据token获取用户信息
    instance.get("/token?token=" + token).then(res => {
      this.admin = res.data;
    });
  }
}
</script>

<style scoped>

</style>
