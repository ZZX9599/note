### 模板使用if和unless

语法：th:if=”boolean 条件” 

条件为 true 显示体内容 

th:unless 是 th:if 的一个相反操作

**注意：没有else语句**

```xml
语法：
<div th:if=" 10 > 0 "> 显示文本内容 </div>
```

**还有一个 th:unless  和 th:if相反的行为**

```xml
语法：
<div th:unless=" 10 < 0 "> 当条件为false显示标签体内容 </div>
```



th:if=" "  如果是空字符串，模板处理为真

th:if=null   如果是null，模板处理为假



### If的使用

```Java
@GetMapping("/ifAndUnless")
public String ifAndUnless(Model model){
    model.addAttribute("sex","男");
    model.addAttribute("isLogin",true);
    model.addAttribute("age",20);
    model.addAttribute("name","");
    model.addAttribute("isOld",null);
    return "ifAndUnless";
}
```



```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>eachList</title>
</head>
<body>
<!--student:每个成员   studentState:成员状态变量,是一个对象，有很多属性  ${studentList}集合-->
    <div>
        <h3>ifAndUnless</h3>
        <p th:if="${sex=='男'}">显示性别</p>
        <p th:if="${isLogin}">成功登录</p>
        <p th:if="${age>18}">年龄合法</p>
        <p th:if="${name}">名称为空</p>
        <p th:if="${isOld}">为null不显示</p>
    </div>
</body>
</html>
```





### unless的使用

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>eachList</title>
</head>
<body>
    <!--student:每个成员   studentState:成员状态变量,是一个对象，有很多属性  ${studentList}集合-->
    <div>
        <h3>if</h3>
        <p th:if="${sex=='男'}">显示性别</p>
        <p th:if="${isLogin}">成功登录</p>
        <p th:if="${age>18}">年龄合法</p>
        <p th:if="${name}">名称为空</p>
        <p th:if="${isOld}">为null不显示</p>
    </div>
    <hr/>
    <div>
        <h3>unless</h3>
        <p th:unless="${sex=='男'}">显示性别</p>
        <p th:unless="${isLogin}">成功登录</p>
        <p th:unless="${age>18}">年龄合法</p>
        <p th:unless="${name}">名称为空</p>
        <p th:unless="${isOld}">isOld为null</p>
    </div>
</body>
</html>
```

**实际上：跟if的使用一样，只是思维相反**