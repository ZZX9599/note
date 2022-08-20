### 事件修饰符capture

**使用.capture 实现捕获触发事件的机制**

**使用的是外层 div 套用内存 div 的例子（其中内层 div 没有使用.stop来阻止内层的冒泡），在此基础之上，点击**

**内层的 div，先触发内层 div 的事件， 再触发外层 div 的事件。**

**加入了.capture 修饰符之后，先触发的是外层的 div 事件，后触发 的是内层 div 事件。**

**被.capture 修改之后，事件优先触发。**

### 总结就是：被.capture修饰的，优先触发事件



```js
<div id="app">
  <div style="width: 200px;height: 200px;background-color: aqua" @click.capture="fun1()">
    <div style="width: 100px;height: 100px;background-color: red" @click="fun2()">

    </div>
  </div>
</div>
```

### 结果：先外层，后内层



```js
<div id="app">
  <div style="width: 200px;height: 200px;background-color: aqua" @click="fun1()">
    <div style="width: 100px;height: 100px;background-color: red" 		                           @click.capture="fun2()">

    </div>
  </div>
</div>
```

### 结果：先内层，后外层