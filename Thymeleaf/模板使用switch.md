### 模板使用switch

语法：th:switch

语法：类似 java 中的 switch，case



```html
语法：
<div th:switch="要比对的值">
    <p th:case="值1">
        结果1
    </p>
    <p th:case="值2">
        结果2
    </p>
    <p th:case="*">
        默认结果
    </p>
    以上的case只有一个语句执行，最多只能匹配一个结果，匹配不到执行case *
</div>
```



```java
@GetMapping("/switchCase")
public String switchCase(Model model){
    model.addAttribute("age",20);
    return "switchCase";
}
```



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
        <h3>switch的使用</h3>
        <div th:switch="${age}">
            <p th:case="10">10岁</p>
            <p th:case="15">15岁</p>
            <p th:case="20">20岁</p>
            <p th:case="25">25岁</p>
            <p th:case="*">年龄未知</p>
        </div>
    </div>
</body>
</html>
```