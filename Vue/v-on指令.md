### v-on指令

**v-on:click="fun1"的形式来绑定事件相当于原生 js 中的 onclick**

```js
<div id="app">
  <input type="button" v-on:click="fun1()"/>
  <input type="button" onclick="fun1()"/>
</div>

<script type="text/javascript">
function fun1(){
    alert("你也好");
  }

let vm=new Vue({
    el:"#app",
    data:{
      "str1":"你好",
      "str2":"Hello World"
    },
    methods:{
      fun1(){
        alert("你好");
      }
    }
  })
</script>
```



#### v-on的简化形式：直接使用@也可以

```
<input type="button" @click="fun1()"/>

<input type="button" v-on:click="fun1()"/>
```