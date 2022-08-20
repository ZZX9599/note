<template>
  <div>
    <h1>{{ msg }}--{{ this.$store.state.counter }}--{{ $store.state.counter }}--{{ this.$store.getters.countSqrt }}</h1>

    <button @click="incr">修改共享数据counter+10</button>
    <button @click="decr">修改共享数据counter--</button>
  </div>
</template>

<script>
export default {
  name: "Dept",
  data() {
    return {
      msg: "部门管理"
    }
  },
  methods: {
    incr() {
      this.$store.commit('incrCounter',{count:10,name:'小陈'}); //调用vuex mutations中方法
    },
    decr() {
      this.$store.commit('decrCounter'); //调用vuex mutations中方法
    }
  }
}
</script>

<style scoped>

</style>
