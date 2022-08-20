### SpringMVC过滤器解决乱码

**在SpringMVC中，框架写好了一个过滤器，专门解决乱码问题**

**get请求不会乱码，post乱码**

**需要使用request.setCharacterEncoding("UTF-8")来解决乱码**

**比较麻烦，所以一般使用框架写好的过滤器**



**过滤器类：CharacterEncodingFilter**



```Java
<filter>
    <!--声明过滤器-->
    <filter-name>characterEncodingFilter</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <!--设置项目的字符编码-->
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <!--设置HttpServletRequest使用encoding编码-->
    <init-param>
        <param-name>forceRequestEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
    <!--设置HttpServletResponse使用encoding编码-->
    <init-param>
        <param-name>forceResponseEncoding</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>

<filter-mapping>
    <filter-name>characterEncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```