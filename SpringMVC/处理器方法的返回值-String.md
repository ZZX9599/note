### **处理器方法的返回值-String**



**1：返回的是视图路径**

```Java
@Controller
@RequestMapping("/user")
public class MyAction {
    @RequestMapping("/first.do")
    public String doReturnString(String name,Integer age){
        return "first";
    }
}
```

```Java
<form action="user/first.do" method="post">
    姓名:<input type="text" name="name"/><br/>
    年龄:<input type="text" name="age"/><br/>
    <input type="submit" value="提交"/>
</form>
```

**这个时候是配置了视图解析器的，返回的String代表逻辑视图名称【不配置视图解析器也可以，写完整路径】**

**视图解析器执行的是字符串的拼接！如果配置了视图解析器还写完整路径，就会重复，会报错404**





**2：返回的String是文本数据**

**区分String是视图还是数据，就是看他有没有@ResponseBody注解**

```Java
@Controller
@RequestMapping("/user")
public class MyAction {
    @ResponseBody
    @RequestMapping("/first.do")
    public String doReturnString(String name,Integer age){
        return "first代表的是数据";
    }
}
```

**这个时候有@ResponseBody注解，使用AJAX接收的结果就是这个字符串**

**经过测试，返回的是first？？？？？**



**查看响应头：Content-Type: text/html;charset=ISO-8859-1**

**解决方案：使用@Request的属性produce**

```java
public class MyAction {
    @ResponseBody
    @RequestMapping(value = "/first.do",produces = "text/plain;charset=utf-8")
    public String doReturnString(String name, Integer age){
        return "first success";
    }
}
```