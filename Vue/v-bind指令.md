### V-bind指令

**v-vind:是 Vuejs 中，提供用于绑定属性的指令 **

**对于 v-bind 在开发中一共有如下几种使用方式 **

**a.直接使用指令属性 v-bind 来为元素中的属性进行绑定操作 **

**b.使用简化后的方式，将 v-bind 去除，直接使用 : 来对元素中的属性进行绑定。**

**因为在实际项目开发中，对于前端元素的绑定是我们的常规操作，**

**v-bind 的使用复用率非常高，所以每一次都直接写 v-bind 会很麻烦**

**所以 vuejs 为 v-bind 指令属性提供了简写的方式，直接使用:即可  v-bind:title --> :title**



### V-bind加载需要绑定的数据之前

```js
<div id="app">
  <!--全写的写法-->
  <input type="text" v-bind:value="str1"/>
      
  <!--简单的写法-->
  <input type="text" :value="str1"/>
</div>

data:{
      "str1":"你好",
      "str2":"Hello World"
    }
```

