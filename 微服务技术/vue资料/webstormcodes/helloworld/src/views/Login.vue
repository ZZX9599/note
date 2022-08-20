<template>
  <div>
    <div class="container-fluid">

      <div class="row" style="margin-top: 70px;">
        <div class="col-sm-6 col-sm-offset-3">
          <form action="" class="form-horizontal">
            <div class="form-group">
              <label class="col-sm-2 control-label">用户名:</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" v-model="admin.username">
              </div>
            </div>
            <div class="form-group">
              <label class="col-sm-2 control-label">密码:</label>
              <div class="col-sm-10">
                <input type="text" class="form-control" v-model="admin.password">
              </div>
            </div>
            <input type="button" value="登录" class="btn btn-primary btn-block" @click="login">
          </form>
        </div>
      </div>

    </div>
  </div>
</template>

<script>
import instance from "../utils/request";

export default {
  name: "Login",
  data(){
    return {
      admin:{},
    }
  },
  methods:{
    login(){
      //console.log(this.admin);
      //发送axios请求,登录成功
      //this.$router.push({name:'Index'});
      instance.post("/login",this.admin).then(res=>{
          //console.log(res.data);
          const result = res.data;
          if(result.success){
            //前端存储token信息
            localStorage.setItem("token",result.token);
            //切换路由到主页
            this.$router.push({name:'Index'});
          }else{
            alert(result.msg);
          }
      });
    }
  }
}
</script>

<style scoped>

</style>
