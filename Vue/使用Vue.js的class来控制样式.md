### 使用Vue.js的class来控制样式



**案例 1：传递一个 class 样式的数组，通过 v-bind 做样式的绑定该形式与之前的形式没有太大的区别**

**语法：:class="[样式 1,样式 2]"**

```js
  <style>
    .style1{
      color: red;
    }
    .style2{
      color: aqua;
    }
    .style3{
      color: black;
    }
    .style4{
      font-style: italic;
    }
  </style>

<div id="app">
  <span :class="['style1','style4']">你好</span>
</div>

:class也就是v-bind来为class绑定数据
```





**案例 2：通过三目（元）运算符操作以上数组**

**语法：boolean?true 执行 :false 执行**

```js
<div id="app">
  <input v-model:value="flag"> 
  <span :class="['style1',flag ? 'style4' : '']">你好</span>
</div>

<script type="text/javascript">
  let vm=new Vue({
    el:"#app",
    data:{
      "flag":false
    }
  })
</script>
```

**默认打开为false，没有斜体，填写true之后，变成斜体**





**案例 3：使用对象（json） 来表达以上三目（元）运算符的操作**

**语法：{样式:flag}**

```js
<div id="app">
  <input v-model:value="flag">
  <span :class="['style1',{'style4':flag}]">你好</span>
</div>

<script type="text/javascript">
  let vm=new Vue({
    el:"#app",
    data:{
      "flag":false
    }
  })
</script>
```





**案例 4：以对象引用样式**

**语法：:class="{'样式一':boolean,'样式二':boolean}"**

```js
<div id="app">
  <input v-model:value="flag">
  <span :class="{'style1':flag,'style4':flag}">你好</span>
</div>

<script type="text/javascript">
  let vm=new Vue({
    el:"#app",
    data:{
      "flag":false
    }
  })
</script>
```





**案例 5：通过以直接模型 M 的形式做样式渲染【推荐使用】**

**注意：这样使用必须直接将具体的 boolean 值结果（true/false）赋值， 不能以 this.模型的形式来做引用**

```js
<div id="app">
  <span :class="mystyle">你好</span>
</div>

<script type="text/javascript">
  let vm=new Vue({
    el:"#app",
    data:{
      "mystyle":{style1:true,style4:true}
    }
  })
</script>
```



```js
<div id="app">
  <span :class="mystyle">你好</span>
</div>

<script type="text/javascript">
  let vm=new Vue({
    el:"#app",
    data:{
      "flag":true,
      "mystyle":{style1:this.flag,style4:this.flag}
    }
  })
</script>

注意：这样无效
```