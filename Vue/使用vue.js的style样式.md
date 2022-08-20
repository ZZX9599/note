### 使用vue.js的style样式

**在实际项目开发中，对于 style 样式的使用，没有 class 使用的广泛，也就是说原生的style没有:class使用得多**

**通常 style 属性仅仅只是对个别指定元素的样式进行的进一步补充使用**



**案例 1：引用样式对象 **

**语法：style="引用样式对象"**

**注意：在写 color 这样的样式属性的时候，由于仅仅只是一个单词， 所以不需要加引号，开发中的大多数的样式都是包含横杠（-）的**

**例如：font-style，background-color 等等**

**在使用这样带有-的演示属 性的时候，必须套用在引号中。 'font-style' 'background-color' color**

```js
<div id="app">
  <span :style="mystyle">你好</span>
</div>

<script type="text/javascript">
  let vm=new Vue({
    el:"#app",
    data:{
      "mystyle":{color:'red','font-size':'100px'}
    }
  })
</script>
```





**案例 2：引用样式对象数组 **

**语法：style="[样式对象引用 1,样式对象引用 2]"**

```js
<div id="app">
  <span :style="[mystyle1,mystyle2]">你好</span>
</div>

<script type="text/javascript">
  let vm=new Vue({
    el:"#app",
    data:{
      "mystyle1":{color:'red','font-size':'100px'},
      "mystyle2":{'font-style':'italic'}
    }
  })
</script>
```