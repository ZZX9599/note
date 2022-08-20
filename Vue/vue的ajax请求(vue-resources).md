### vue的ajax请求（使用vue-resources)



**vuejs 本身不支持发送 ajax 请求的 **

**需要使用插件来实现**

**1：vue-resource 插件 **

**2：axios 插件**



**做查询，一般是get，get的效率更高，涉及到安全问题，采用post**

**其余的请求，基本上都是post**



### 不带参数：

```js
this.$http.get("my.do").then(function (result){
    //前台打回的学生的json对象
    /*
     * 注意：result.属性拿不到值，是undefined
     * 这里拿值的时候，需要使用result.body.属性
     */
   alert(result.body.id)
})
```



### 带参数

### 带参数，get和post的语法不同



### get必须是在路径后面的url后面加上?key1=value1&key2=value2

```js
this.$http.get("my.do?name=zhangsan&age=lisi").then(function (result){
    //前台打回的学生的json对象
    /*
     * 注意：result.属性拿不到值，是undefined
     * 这里拿值的时候，需要使用result.body.属性
     */
   alert(result.body.id)
})
```



### post是以请求体的方式来传递参数的，所以不能挂在url后面用?的方式

```js
this.$http.post("my.do",{"name":"zhangsan","age":20}).then(function (result){
    //前台打回的学生的json对象
    /*
     * 注意：result.属性拿不到值，是undefined
     * 这里拿值的时候，需要使用result.body.属性
     */
   alert(result.body.id)
})
```



**注意：手动发起的post请求，默认没有表单格式，有的服务器处理不了，我们需要使用post的第三个参数**

**{emulateJSON:true}，这样就把提交的内容设置为了表单，才能获取到参数！**

```js
this.$http.get("my.do",{"name":"zhangsan","age":20},{emulateJSON:true}).then(function (result){
    //前台打回的学生的json对象
    /*
     * 注意：result.属性拿不到值，是undefined
     * 这里拿值的时候，需要使用result.body.属性
     */
   alert(result.body.id)
})
```