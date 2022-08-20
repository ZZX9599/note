### Thymeleaf属性

**属性是放在html元素中的，就是html元素的属性，加入了th前缀。属性的作用不变。加入上th，属性的值由模板引擎处理了。在属性可以使用变量表达式**

例如：

```xml
<form action="/loginServlet" method="post"></form>

<form th:action="/loginServlet" th:method="${methodAttr}"></form>
```



**反正意思就是在属性之前加上th:  属性的值就可以被模板处理，从模板里面拿到值**

```Java
//使用模板属性
@GetMapping("/property")
public String userProperty(Model model){
    model.addAttribute("method","post");
    model.addAttribute("id",1001);
    model.addAttribute("key","name");
    model.addAttribute("value","周志雄");
    model.addAttribute("color","color:blue");
    return "html-property";
}
```



```HTML
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>html-property</title>
    <script type="text/javascript" th:src="@{/js/jquery-1.11.1-min.js}"></script>
    <script type="text/javascript">
        $(function (){
            $("#btn").click(function (){
                alert("按钮点击");
            })
        })
    </script>
</head>
<body>
    <div style="margin-left: 400px">
        <h3 th:style="${color}">属性使用，在html属性上加th</h3>
        <form th:action="@{/loginServlet}" th:method="${method}">
            <input th:type="text" th:name="${name}" th:value="${value}">
            <input th:type="button" id="btn" th:value="点击">
        </form>
    </div>
</body>
</html>
```