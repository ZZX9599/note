### v-if和v-show的区别



**案例 1：v-if 的使用**

**v-if只有两个值,true或者false**

```js
<div id="app">
  <p v-if="false">
    你好
  </p>
</div>
```



**案例 2：v-show 的使用**

```js
<div id="app">
  <p v-show="false">
    你好
  </p>
</div>
```

**简单的比较 v-if 指令和 v-show 指令，效果是一模一样的**

**点击浏览器（F12）中的查看器观察显示页面元素信息 **

**如果 flag 为 true，观察到的结果是一致的**



**如果 flag 为 false，观察到的结果是不同的**



**<p style="display:none">v-show</p>**



**其中 v-if 中的元素是本身就没有的，v-show 中的元素是存在的**
**只是 通过 false 属性值，系统为该元素新增了 display:none，表示不展现该 元素**







**总结：v-if 为 true：就创建条件元素，如果为false：就去除该元素**

**v-show 为 true：就创建条件元素，如果为false，就隐藏该元素**



**在实际项目开发中，一般的需求情况下，我们使用 v-if 就可以了。 **

**但是如果对于元素的展现与否需要频繁的切换，我们需要使用 v-show 的形式展现或者隐藏元素**

**因为 v-if 在每次切换为 true 的时候都需要 新的创建元素，降低页面展现元素的效率。**