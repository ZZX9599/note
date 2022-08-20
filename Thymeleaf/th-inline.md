### th-inline

**1:内联text：在html标签外面获取表达式的值**

语法： [[表达式]]



```Java
@GetMapping("/inLine")
public String inLine(Model model){
    model.addAttribute("name","周志雄");
    model.addAttribute("age",20);
    model.addAttribute("student",new Student(1001,"蒋雪丽",22));
    return "inLine";
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
    <div th:inline="text">
        <h3>内联表达式显示变量的值</h3>
        <p>我是[[${name}]],年龄是[[${age}]]</p>
    </div>

    <!--也可以不写inline="text"-->
    <div>
        <h3>内联表达式显示变量的值</h3>
        <p>学生对象[[${student}]]</p>
    </div>
</body>
</html>
```





**2:内联javascript：在html标签外面获取表达式的值**

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>eachList</title>
    <script type="text/javascript" th:inline="javascript">
        //th:inline="javascript" 这样就能在脚本里面获取模型的数据
        let name=[[${name}]];
        let age=[[${age}]];
        function btnClick(){
            alert("姓名-->"+name+"===年龄-->"+age);
        }
    </script>
</head>
<body>
    <input type="button" id="btn" value="点击显示数据" onclick="btnClick()">
</body>
</html>
```