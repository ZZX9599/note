### SpringMVC的重定向和转发

**原生 Servlet**



**forward:表示转发，实现 request.getRequestDispatcher("xx.jsp").forward() **

**redirect:表示重定向，实现 response.sendRedirect("xxx.jsp")**



**转发：不会改变response，请求次数：1**

**重定向：改变请求，相当于重新写了一次地址栏，请求次数：2及以上**



**都不与视图解析器一起工作，配置了视图解析器一样无效！**



### **转发代码：forward:视图页面的完整路径**

```Java
@Controller
@RequestMapping("/user")
public class MyController {
    @RequestMapping("/some.do")
    public ModelAndView doSome(HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        mv.addObject("name","张三");
        mv.addObject("fun","doSome");
        //语法：setViewName("forward:视图页面的完整路径")
        mv.setViewName("forward:/WEB-INF/01/show.jsp");
        return mv;
    }
}
```



**注意：forward:视图页面的完整路径【叫做显示转发，forward表明了是转发操作】**

**配置了视图解析器，但是需要转发的页面不在视图解析器目录下面，我们就使用转发操作！**





### **重定向代码：redirect:视图页面的完整路径**

**注意：重定向无法访问WEB-INF下的数据，报错404！**

```java
mv.setViewName("redirect:/WEB-INF/01/show.jsp");
```



**注意：在SpringMVC中，重定向发起请求时，会将简单类型的作为参数放进下一次请求，作为参数**

```java
@Controller
@RequestMapping("/user")
public class MyController {
    @RequestMapping("/some.do")
    public ModelAndView doSome(String name,Integer age,HttpServletRequest request){
        ModelAndView mv=new ModelAndView();
        mv.addObject("myName",name);
        mv.addObject("myAge",age);
        System.out.println(name+age);
        //语法：setViewName("forward:视图页面的完整路径")
        mv.setViewName("redirect:/show.jsp");
        return mv;
    }
}
```

**show.jsp**

```html
<body>
    姓名:${name}<br/>
    函数:${age}
</body>
```



**这个时候，是读取不到数据的，因为是重定向，是两个request作用域**



**另外：${param.name}   相当于  request.getParameter("name")**