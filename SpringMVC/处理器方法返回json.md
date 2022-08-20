### 处理器方法返回json

**在springMVC中返回json格式的数据**

**自己的方式返回json**

```java
@ResponseBody
@RequestMapping("/returnVoid-ajax.do")
public void doReturnVoid(String name, Integer age, HttpServletResponse response)
        throws IOException {
    Student student=new Student(name,age);
    String json="";
    //把结果转化成JSON
    if(student!=null){
        ObjectMapper om=new ObjectMapper();
        //返回的是json对象
        json=om.writeValueAsString(student);
    }
    //输出数据，响应ajax请求
    response.setContentType("application/json;charset=utf-8");
    PrintWriter writer=response.getWriter();
    writer.println(json);
    writer.flush();
    writer.close();
}
```



**在SpeingMVC对于json格式的输出，更加简单**

**1：加入Jackson依赖**

**2：加入注解驱动 <mvc:annotation-driven/>  会加入HttpMessageConverter【消息转换器】 内部实现java对象到json,xml,二进制等数据的转换，加入这个注解驱动之后，会加入七个HttpMessageConverter接口的实现类，有canWrite()和write()方法，把控制器类的方法输出给浏览器使用的**

**3：加入@Response注解**

**通过HttpResponse输出数据**

****

**没有加入注解驱动标签时的状态
org.springframework.http.converter.ByteArrayHttpMessageConverter 
org.springframework.http.converter.StringHttpMessageConverter
org.springframework.http.converter.xml.SourceHttpMessageConverter
org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter**



**加入注解驱动标签时的状态
org.springframework.http.converter.ByteArrayHttpMessageConverter
org.springframework.http.converter.StringHttpMessageConverter
org.springframework.http.converter.ResourceHttpMessageConverter
org.springframework.http.converter.ResourceRegionHttpMessageConverter
org.springframework.http.converter.xml.SourceHttpMessageConverter 
org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter 
org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter
org.springframework.http.converter.json.MappingJackson2HttpMessageConverter**



```Java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:mvc="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    
    <mvc:component-scan base-package="com.zzx.action"/>
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/view/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <mvc:annotation-driven/>
</beans>
```



```Java
@Controller
@RequestMapping("/user")
public class MyAction {
    @ResponseBody
    @RequestMapping("/returnObject-ajax.do")
    public Student doReturnVoid(String name, Integer age, HttpServletResponse response)
            throws IOException {
        Student student=new Student(name,age);
        return student;
    }
}
```



**返回List集合的话，打回的是json数组      [{"张三",20},{"李四",20}]**

**循环取到对象  **

**$.each(result,function(i,n){**

​		**//处理函数，n是每一个json对象**

**})**

