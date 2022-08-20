### vue动画

**需求：点击按钮，展现信息，再次点击按钮，隐藏信息**

```js
<body>
<div id="app">
    <input type="button" value="点击" @click="flag=!flag"/>
    <br/>
    <br/>
    <p v-if="flag">周志雄</p>
</div>

<script type="text/javascript">
    let vm=new Vue({
        el:"#app",
        data:{
            "flag":false
        }
    })
</script>
</body>
```



****

**需求：点击按钮，展现信息，再次点击按钮，隐藏信息【加入动画】**

**.v-enter 进入前**

**.v-enter-to 进入后**

**.v-leave 离开前**

**.v-leave-to 离开后**

**.v-enter-active：信息进入阶段**

**.v-leave-active：信息离开阶段**



