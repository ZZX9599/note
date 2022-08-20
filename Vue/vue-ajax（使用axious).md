### vue-ajax（使用axious)

**注意：对于axious和vue-resources得到的Controller返回的json对象**

**对于vue-resources，使用的是对象.body.属性**

**对于axious，使用的是对象.data.属性**

```js
axios({
    method:"get",
    url:"my.do",

}).then(function (result){
    alert(result);//[object,object]
    alert(result.id);//[undefined]
    alert(result.data.id);
})
```



### 参数传递



### get传递参数

### 方法1：在url后面直接添加

```js
axios({
    method:"get",
    url:"my.do?name=zhangsan",

}).then(function (result){
    alert(result);//[object,object]
    alert(result.id);//[undefined]
    alert(result.data.id);
})
```

### 方法2：使用param参数

```js
axios({
    method:"get",
    url:"my.do",
    params:{
    	"name":"zhangsan","age":20
    }

}).then(function (result){
    alert(result);//[object,object]
    alert(result.id);//[undefined]
    alert(result.data.id);
})
```



### post传递参数

```js
axios({
    method:"post",
    url:"my.do",
    params:{
        "name":"zhangsan",
        "age":20
    }

}).then(function (result){
    alert(result);//[object,object]
    alert(result.id);//[undefined]
    alert(result.data.id);
})
```



**总结：axios 的形式是一个基于 Promise 的 HTTP 请求客户端用来发出请求。**

**该形式也是 vue2.0 官方推荐的形式，官方在推出了该形式后**

**同时就不再对之前的 vue-resource 的形式进行更新和维护了。**

**所以更推荐的是使用 axios 的形式来处理 ajax 请求。**