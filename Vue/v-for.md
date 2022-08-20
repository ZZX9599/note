### v-for

**1：普通方式：可以通过下标**

```js
<div id="app">
  <p>{{cityList[0]}}</p>
  <p>{{cityList[1]}}</p>
  <p>{{cityList[2]}}</p>
  <p>{{cityList[3]}}</p>
  <p>{{cityList[4]}}</p>
</div>

<script type="text/javascript">
  let vm=new Vue({
    el:"#app",
    data:{
      "cityList":["北京","上海","广州","深圳","成都"]
    }
  })
```





**2：使用v-for指令遍历字符串数组      语法：变量   in   数组**

```js
<div id="app">
  <p v-for="city in cityList">
    {{city}}
  </p>
</div>

<script type="text/javascript">
  let vm=new Vue({
    el:"#app",
    data:{
      "cityList":["北京","上海","广州","深圳","成都"]
    }
  })
</script>
```

**注意：如果在遍历的时候要使用下标**

```js
<p v-for="(city,i) in cityList">
```





**3：遍历对象数组**

```js
<div id="app">
  <p v-for="(user,i) in userList">
    用户下标:{{i}}
    <br/>
    用户id:{{user.id}}
    <br/>
    用户name:{{user.name}}
  </p>
</div>

<script type="text/javascript">
  let vm=new Vue({
    el:"#app",
    data:{
      "userList":[
        {"id":"A001","name":"张三"},
        {"id":"A002","name":"李四"},
        {"id":"A003","name":"王五"},
        {"id":"A004","name":"赵六"},
        {"id":"A005","name":"猪头"},
      ]
    }
  })
</script>
```





**4：遍历对象的属性和属性值**

**注意：遍历对象的属性或者属性值，必须取出键值对的形式来遍历  **

**语法：(value[值],key[键])  in  对象**

**注意：value在前面**

```js
<div id="app">
  <p v-for="(value,key,i) in user">
    下标:{{i}}------key:{{key}}------value:{{value}}
  </p>
</div>

<script type="text/javascript">
  let vm=new Vue({
    el:"#app",
    data:{
      "user":{"id":"A001","name":"张三"}
    }
  })
</script>
```





**5：遍历整型数字**

```js
<div id="app">
  <p v-for="i in 100">
    {{i}}
  </p>
</div>
```