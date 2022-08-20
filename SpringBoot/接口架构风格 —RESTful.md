### 接口架构风格 —RESTful

**接口： API（Application Programming Interface，应用程序接口）是一些预先定义的接口（如函数、HTTP接口），或指[软件系统](https://baike.baidu.com/item/软件系统/224122)不同组成部分衔接的约定。 用来提供[应用程序](https://baike.baidu.com/item/应用程序)与开发人员基于某[软件](https://baike.baidu.com/item/软件)或硬件得以访问的一组[例程](https://baike.baidu.com/item/例程/2390628)，而又无需访问源码，或理解内部[工作机制](https://baike.baidu.com/item/工作机制/9905789)的细节**



**接口（API）： 可以指访问servlet，controller的url，调用其他程序的函数**



**架构风格： api组织方式（样子）**

**就是一个传统的：http://localhost:9002/mytrans/addStudent?name=lisi&age=26** 

**在地址上提供了访问的资源名称addStudent, 在其后使用了get方式传递参数。**



### URL定义

**资源：互联网所有的事物都可以被抽象为资源** 
**资源操作：使用POST、DELETE、PUT、GET，使用不同方法对资源进行操作。** 
**分别对应 添加、 删除、修改、查询。** 



### REST

**RESTful架构风格**

**1)REST :  （英文： Representational State Transfer , 中文： 表现层状态转移)。**

**REST：是一种接口的架构风格和设计的理念，不是标准。**

**优点： 更简洁，更有层次**

**比如我们要访问一个 http 接口：http://localhost:8080/boot/order?id=1021&status=1 **

**采用 RESTFul 风格则 http 地址为：http://localhost:8080/boot/order/1021/1**



**表现层状态转移:** 

**表现层就是视图层， 显示资源的， 通过视图页面，jsp等等显示操作资源的结果。**

**状态： 资源变化**

**转移： 资源可以变化的。 资源能创建，new状态，  资源创建后可以查询资源， 能看到资源的内容，**

**这个资源内容 ，可以被修改， 修改后资源和之前的不一样。**  



**REST中的要素：**

**用REST表示资源和对资源的操作。  在互联网中，表示一个资源或者一个操作。** 

**资源使用url表示的， 在互联网， 使用的图片，视频， 文本，网页等等都是资源，资源是用名词表示。**



**对资源：** 

**查询资源： 看，通过url找到资源。** 

**创建资源： 添加资源**

**更新资源：更新资源 ，编辑**

**删除资源： 去除**



**资源使用url表示，通过名词表示资源。**

**在url中，使用名词表示资源， 以及访问资源的信息,  在url中，使用“ / " 分隔对资源的信息**

**http://localhost:8080/myboot/student/1001**

**使用http中的动作（请求方式）， 表示对资源的操作（CURD）**



### **使用http中的动作（请求方式）， 表示对资源的操作（CURD）**

**第一种   GET:  查询资源  --  sql select**

**1：处理单个资源： 用他的单数方式**

**查询1001学生的信息**

**http://localhost:8080/myboot/student/1001**

**查询1001学生并且正常上课的学生**

**http://localhost:8080/myboot/student/1001/1**

**2：处理多个资源：使用复数形式**

**http://localhost:8080/myboot/students/1001/1002**

​           

**第二种   POST: 创建资源  -- sql insert**

**http://localhost:8080/myboot/student，这里的student是学生参数**

**在post请求中传递数据**

```html
<form action="http://localhost:8080/myboot/student" method="post">
	姓名：<input type="text" name="name" />
    年龄：<input type="text" name="age" />
</form>
```



**第三种   PUT： 更新资源  --  sql  update**

   ```xml
<form action="http://localhost:8080/myboot/student/1" method="post">
	姓名：<input type="text" name="name" />
    年龄：<input type="text" name="age" />
         <input type="hidden" name="_method" value="PUT" />
</form>
   ```



**第四种   DELETE: 删除资源  -- sql delete**

<a href="http://localhost:8080/myboot/student/1001">删除1001学生的数据</a>



### **注意：浏览器默认只支持get和post**



**需要的分页，  排序等参数，依然放在  url的后面， 例如** 

**http://localhost:8080/myboot/students?page=1&pageSize=20**

  

**一句话说明REST：** 

**使用url表示资源 ，使用http动作操作资源。**



### 对比

**传统方式操作资源** 
**http://127.0.0.1/item/queryUser.action?id=1   查询,GET** 
**http://127.0.0.1/item/saveUser.action             新增,POST** 
**http://127.0.0.1/item/updateUser.action          更新,POST** 
**http://127.0.0.1/item/deleteUser.action?id=1  删除,GET或POST**

**使用RESTful操作资源** 
**【GET】 /users # 查询用户信息列表**

**【GET】 /users/1001 # 查看某个用户信息**

**【POST】 /users # 新建用户信息**

**【PUT】 /users/1001 # 更新用户信息(全部字段)**

**【PATCH】 /users/1001 # 更新用户信息(部分字段)**

**【DELETE】 /users/1001 # 删除用户信息**



**之前的操作是没有问题的,大神认为是有问题的,有什么问题呢?你每次请求的接口或者地址,都在做描述,例如查询的时候用了queryUser,新增的时候用了saveUser ，修改的时候用了updateUser,其实完全没有这个必要,我使用了get请求,就是查询.使用post请求,就是新增的请求,PUT就是修改，delete就是删除，我的意图很明显,完全没有必要做描述,这就是为什么有了restful**

### API设计风格基本规则

**1.使用名词而不是动词**
**不要使用：**

**/getAllUsers**
**/createNewUser**
**/deleteAllUser**



**2.Get方法和查询参数不应该涉及状态改变**
**使用PUT, POST 和DELETE 方法 而不是 GET 方法来改变状态，不要使用GET 进行状态改变:**



**3.使用复数名词**
**不要混淆名词单数和复数，为了保持简单，只对所有资源使用复数。**

**/cars 而不是 /car**
**/users 而不是 /user**
**/products 而不是 /product**
**/settings 而不是 /setting**



**4.使用子资源表达关系**

**如果一个资源与另外一个资源有关系，使用子资源：**

**GET /cars/711/drivers/ 返回 car 711的所有司机**
**GET /cars/711/drivers/4 返回 car 711的4号司机**

..............................................