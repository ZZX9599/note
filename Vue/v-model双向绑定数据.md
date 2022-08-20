### v-model双向绑定数据

**v-model 的基本使用： 使用 v-model 可以对数据进行双向的绑定操作。 **

**注意：由于实际开发中记载页面元素值的需求都是发生在表单元素中，所以 v-model 只能运用在表单元素中。**

**并不是所有的都能替代v-bind   因为只有输入类，表单类才能使用v-model**



**form**

**系列 type:text,hidden,checkbox,radio....**

**select**

**textarea**



```js
<div id="app">
    {{str1}}
    <input type="text" v-model:value="str1" @click="fun1()">
</div>
```



**当我们在输入框输入的时候，这个数据会传到data里面去，通过插值表达式就能获取str1的值，也会跟着变化**

**v-model默认收集的就是value，所以简写的时候可以不写value**