import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);//配置内置vue注册vuex状态管理


const stroe = new Vuex.Store({
    state: { //用来定义共享数据    //组件中使用state定义共享数据      组件中使用共享数据:     this.$stroe.state.counter
      counter: 12,
    },
    mutations: { //用来定义对共享数据修改方法  在这里面定义方法 都有一个默认参数 默认参数会将状态对象传递给自定义方法   //组件中调用这个方法 this.$store.commit('方法名');
      incrCounter(state, data) {
        console.log("vuex:", data.name);
        state.counter += data.count;
      },
      decrCounter(state) {
        state.counter--;
      }
    },
    getters:{ //用来定义对共享数据一系列计算方法  好处只计算一次  缓存结果   在这里面定义方法 都有一个默认参数 默认参数会将状态对象传递给自定义方法
              // 组件中使用getters中定义计算属性 this.$store.getters.countSqrt
        countSqrt(state){
          console.log("------getters-----");
          return state.counter*state.counter;
        }
    }
  }
);
export default stroe;


