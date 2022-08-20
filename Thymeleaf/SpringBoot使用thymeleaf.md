### SpringBoot使用thymeleaf



**1：选择thymeleaf模板**

**2：配置application.properties**

```properties
#开发阶段用false,上线用true
spring.thymeleaf.cache=false
#模板
spring.thymeleaf.mode=HTML
#编码
spring.thymeleaf.encoding=UTF-8
#前缀
spring.thymeleaf.prefix=classpath:/templates/
#后缀
spring.mvc.view.suffix=.html
#服务端口
server.port=8888
#上下文路径
server.servlet.context-path=/thymeleaf
```

**实际上：一般只更改缓存那个设置就可以了，其他默认的设置就是我们想要的**



**3：准备视图：html**

**4：准备数据**

**5：使用表达式**



**可以获取简单类型数据，也可以是引用类型数据，调用getXXX 方法获取变量**

**也可以获取公共的属性**



**加上xmlns:th=http://www.thymeleaf.org就会出现各种提示信息**

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>var1测试</title>
</head>
<body>
    <p>获取简单数据</p>
    <p th:text="${name}"></p>
    <p th:text="${age}"></p>
    <br/>
    <p>获取对象数据</p>
    <p th:text="${student.id}"></p>
    <p th:text="${student.name}"></p>
    <p th:text="${student.email}"></p>
    <p th:text="${student.age}"></p>
    <p>获取公共属性</p>
    <p th:text="${student.sex}"></p>
    <p>获取对象中的对象的属性</p>
    <div>
        学校名称:<span th:text="${student.school.name}"></span>
        学校名称:<span th:text="${student.school.getName()}"></span>
        学校地址:<span th:text="${student.school.address}"></span>
    </div>
</body>
</html>
```

**实际上无论是直接输出属性，还是方法，都会调用getXXX方法**