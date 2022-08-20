

### Thymeleaf 模板引擎

Thymeleaf： 是使用java开发的模板技术，在服务器端运行。 把处理后的数据发送给浏览器。

模板是作视图层工作的。  显示数据的。  Thymeleaf是基于Html语言。 Thymleaf语法是应用在

html标签中 。 SpringBoot框架集成Thymealeaf,  使用Thymeleaf代替jsp



Thymeleaf 是一个流行的模板引擎，该模板引擎采用 Java 语言开发，模板引擎是一个技术名词，是跨领域跨平台的概念，在 Java 语言体系下有模板引擎，在 C#、PHP 语言体系下也有模板引擎，甚至在 JavaScript 中也会用到模板引擎技术，Java 生态下的模板引擎有 Thymeleaf 、Freemaker、Velocity、Beetl（国产） 等。 Thymeleaf 对网络环境不存在严格的要求，既能用于 Web 环境下，也能用于非 Web 环境 下。在非 Web 环境下，他能直接显示模板上的静态数据；在 Web 环境下，它能像 Jsp 一样从 后台接收数据并替换掉模板上的静态数据。它是基于 HTML 的，以 HTML 标签为载体， Thymeleaf 要寄托在HTML 标签下实现。 Spring Boot 集成了 Thymeleaf 模板技术，并且 Spring Boot 官方也推荐使用 Thymeleaf 来 替代 JSP 技术，Thymeleaf 是另外的一种模板技术，它本身并不属于 Spring Boot，Spring Boot 只是很好地集成这种模板技术，作为前端页面的数据展示，在过去的 Java Web 开发中，我们 往往会选择使用 Jsp 去完成页面的动态渲染，但是 jsp 需要翻译编译运行，效率低



例如：jsp中的  ${data}就是模板技术，模板技术实际上就是字符串的替换



Thymeleaf 的官方网站：http://www.thymeleaf.org
Thymeleaf 官方手册：https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html



### 表达式是在页面获取数据的一种 thymeleaf 语法。类似 ${key}



### **1：标准变量表达式** 

**说明：标准变量表达式用于访问容器（tomcat）上下文环境中的变量，功能和 EL 中的 ${} 相 同。Thymeleaf 中的变量表达式使用 ${变量名} 的方式获取 Controller 中 model 其中的数据。 也就是 request 作用域中的数据。**

**注意：th:text="  " 是 Thymeleaf 的一个属性，用于文本的显示**

**语法：  ${key}** 

**作用： 获取key对于的文本数据，  key是request作用域中的key ， 使用request.setAttribute(), model.addAttribute()**

**在页面中的 html标签中， 使用 th:text="${key}"** 

**如果th:text爆红，引入库文件**

```HTML
<html lang="en" xmlns:th="http://www.thymeleaf.org">    
```

```html
<h3>标准变量表达式</h3>
<div>
    <p th:text="${myData01}">未成功！</p>

    <!--获取学生信息-->
    <p th:text="${data01.getId()}">id</p>
    <p th:text="${data01.getName()}">name</p>
    <p th:text="${data01.getSex()}">sex</p>
    <p th:text="${data01.getAge()}">age</p>
</div>
```

**注意：<p th:text="${data}">你好</p>    如果data不存在，会使用空串覆盖你好**



### 2：选择变量表达式（ 星号变量表达式）

**语法：*{key}**

**作用： 获取这个key对应的数据，*{key}需要和th:object 这个属性一起使用。**

**目的是简单获取对象的属性值**

```html
<h3>选择变量表达式</h3>
<div>
    <p th:text="${myData02}">未成功！</p>

    <!--获取学生信息-->
    <!--使用*{key}来获取属性-->
    <div th:object="${data02}">
        <p th:text="*{id}">id</p>
        <p th:text="*{name}">name</p>
        <p th:text="*{sex}">sex</p>
        <p th:text="*{age}">age</p>
    </div>
    <!--说白了，就是使用*{key}来获取对象的属性
	<!--还可以当作${key}-->
    <p th:text="*{myData02.age}"></p>
</div>
```



### 3：链接表达式

**语法： @{url} **

**作用： 表示链接， 可以在下面使用**

```html
 <script src="....">
 <link href="....."> 
 <a href="........">
 <form action="...">
 <img src=".......">
```



```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>链接表达式</title>
</head>
<body>
    <h3>链接表达式</h3>
    <div>
        <h3>链接绝对地址</h3>
        <a th:href="@{http://www.baidu.com}">链接到百度</a>
        <h3>链接相对地址，无参数</h3>
        <a th:href="@{/temp/query/account01}">没有参数</a>
        <h3>链接相对地址，带参数</h3>
        <a th:href="@{'/temp/query/account01?id='+${userId}}">传递单个参数</a>
<a th:href="@{'/temp/query/account02?id='+${userId}+'&name='+${userName}}">传递多个参数</a>
        <h3>推荐使用的传递参数方式</h3>
        <!--传递单个参数-->
        <a th:href="@{/temp/query/account01(id=${userId})}">传递单个参数</a>
        <!--传递多个参数-->
<a th:href="@{/temp/query/account02(name=${userName},id=${userId})}">传递多个参数</a>
    </div>
</body>
</html>
```

### 在模板引擎拼接的时候，使用  '  '     但是推荐使用  ()  来传递参数





### Thymeleaf的属性

**大部分属性和 html的一样，只不过前面加了一个 th 前缀。 加了 th 前缀的属性，是经过模版引擎处理的。**

**属性是放在html元素中的，就是html元素的属性，加入了th前缀。  属性的作用不变。 加上了th， 属性的值由模板引擎处理了。  在属性可以使用变量表达式**

**也就是说，加入了Thymeleaf依赖之后，在页面加入th之后，就拥有了模板引擎，变成了动态的！**

**例如：**

```xml
<form action="/loginServlet" method="post"></form>

<form th:action="/loginServlet" th:method="${methodAttr}"></form>
```



**1:th:text  **

**用于文本的显示，该属性显示的文本在标签体中，如果是文本框，数据会在文本框外显示， 要想显示在文本框内，使用 th:value**



**2:th:action**

**定义后台控制器的路径，类似标签的 action 属性，主要结合 URL 表达式,获取动态变量**

**<form th:action="@{/login}"**



**3:th:method**

**设置请求方法**

**<form th:action="@{/login}"  th:method="@{get}"**



**4:th:href**

**5:th:src**

**4，5同上**

```java
model.addAttribute("key","name");
model.addAttribute("value","周志雄");

model.addAttribute("skey","submit");
model.addAttribute("svalue","提交");
```

```html
<div style="margin-left: 500px">
    <h3>属性的使用，在html的元素属性上加入th</h3>
    <form th:action="@{/loginServlet}" th:method="${method}">
        <input type="text" th:name="${key}" th:value="${value}"/>
        <input th:type="${skey}" th:value="${svalue}" onclick="btnClick()"/>
    </form>
</div>
```

###### **6:th:style**

**7:th:each**