<template>
  <div>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-12">
          <button @click="emp={}" class="btn btn-success btn-sm">添加员工信息</button>
        </div>
        <div class="col-sm-12">
          <table class="table table-bordered table-striped table-hover">
            <thead>
              <tr class="aa">
                <th>编号</th>
                <th>姓名</th>
                <th>年龄</th>
                <th>工资</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(emp,index) in emps" :key="emp.id">
                <td>{{emp.id}}</td>
                <td>{{emp.name}}</td>
                <td>{{emp.age}}</td>
                <td>{{emp.salary}}</td>
                <td>
                  <a href="" class="btn btn-danger btn-sm">删除</a>
                  <a href="" class="btn btn-info btn-sm" @click.prevent="detailEmp(emp.id)">修改</a>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
        <div class="col-sm-12">
          <form action="">
            <div v-show="emp.id" class="form-group">
               <label >编号:</label>
               <input class="form-control"  readonly type="text" v-model="emp.id"> <br>
            </div>
            <div class="form-group">
              <label >姓名:</label>
              <input class="form-control" type="text" v-model="emp.name">
            </div>
            <div class="form-group">
              <label >年龄:</label>
              <input class="form-control" type="text" v-model="emp.age">
            </div>
            <div class="form-group">
              <label >工资:</label>
              <input class="form-control" type="text" v-model="emp.salary">
            </div>
            <input type="button" class="btn btn-danger btn-sm btn-block" value="保存|修改" @click="saveOrUpdate">
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import instance from "../../utils/request";

export default {
  name: "Index",
  data(){
    return {
      emps:[],
      emp:{},//定义接收员工信息的对象
    }
  },
  methods:{
    saveOrUpdate(){
      //1.获取用户输入员工信息
      instance.post("/emp",this.emp).then(res=>{
        this.emp = {};//清空添加数据
        this.findAll();
      })
    },
    findAll(){
      instance.get("/emps").then(res=>{
        this.emps = res.data;
      });
    },
    detailEmp(id){
      instance.get("/emp?id="+id).then(res=>this.emp = res.data);
    }
  },
  created() {
    //发送axios请求
    this.findAll();//员工列表
  }
}
</script>

<style scoped>
  /*.aa{*/
  /*  background: chartreuse;*/
  /*}*/
</style>
