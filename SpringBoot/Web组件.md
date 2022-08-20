### Web组件

#### 1： 拦截器

拦截器是SpringMVC中一种对象，能拦截器对Controller的请求。

拦截器框架中有系统的拦截器， 还可以自定义拦截器。  实现对请求预先处理。



**SpringMVC实现自定义拦截器：**

1. 创建类实现SpringMVC框架的HandlerInterceptor接口

   ```java
   public class LoginInterceptor implements HandlerInterceptor {
       @Override
       public boolean preHandle(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler) throws Exception {
           //handler:表示被拦截的控制器对象
           System.out.println("不放行");
           return false;
       }
   }
   
   ```



2.需在SpringMVC的配置文件中，声明拦截器

```xml
<mvc:interceptors>
	<mvc:interceptor>
    	<mvc:path="url" />
        <bean class="拦截器类全限定名称"/>
    </mvc:interceptor>
</mvc:interceptors>
```



**SpringBoot使用拦截器**

1. 创建类实现SpringMVC框架的HandlerInterceptor接口

1. 实现WebMvcConfigurer接口

   ```java
   public class LoginInterceptor implements HandlerInterceptor {
       @Override
       public boolean preHandle(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler) throws Exception {
           //handler:表示被拦截的控制器对象
           System.out.println("不放行");
           return false;
       }
   }
   
   ```

   

```java
@Configuration
public class MyAppConfig implements WebMvcConfigurer {

    /**
     * 添加拦截器对象  注入到容器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器对象
        HandlerInterceptor interceptor=new LoginInterceptor();
        //InterceptorRegistration--->拦截器注册中心
        InterceptorRegistration registration=registry.addInterceptor(interceptor);
        //添加拦截的请求uri地址
        String[] path=new String[]{"/user/**","/buy/**"};
        registration.addPathPatterns(path);
        //不拦截的地址
        String[] excludePath=new String[]{"/user/login/**"};
        registration.excludePathPatterns(excludePath);
    }
}

```



#### 2：Servlet

在SpringBoot框架中使用Servlet对象。

使用步骤：

1. 创建Servlet类。 创建类继承HttpServlet
2. 注册Servlet ，让框架能找到Servlet

```Java
public class MyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //使用HttpResponse输出数据的应答结果
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer=resp.getWriter();
        writer.println("执行了MyServlet");
        writer.flush();
        writer.close();
    }
}
```



```Java
@Configuration
public class WebConfig {
    /**
     * 注册Servlet对象
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        //public ServletRegistrationBean(T servlet, String... urlMappings)
        //分别是servlet对象，url地址
        ServletRegistrationBean bean=
                new ServletRegistrationBean(new MyServlet(),"/myServlet");
        return bean;
    }
}


//无参数形式
@Configuration
public class WebConfig {
    /**
     * 注册Servlet对象
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        //public ServletRegistrationBean(T servlet, String... urlMappings)
        //分别是servlet对象，url地址
        ServletRegistrationBean bean= new ServletRegistrationBean();
        bean.setServlet(new MyServlet());
        bean.addUrlMappings("/myServlet","/login");
        return bean;
    }
}
```



#### 3：过滤器Filter

Filter是Servlet规范中的过滤器，可以处理请求， 对请求的参数， 属性进行调整。 常常在过滤器中处理字符编码

在框架中使用过滤器：

1. 创建自定义过滤器类
2. 注册Filter过滤器对象



```Java
public class MyFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("执行了MyFilter");
        //放行
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
```



```Java
@Configuration
public class MyApplicationConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        //添加过滤器
        filterRegistrationBean.setFilter(new MyFilter());
        //添加地址
        filterRegistrationBean.addUrlPatterns("/user/*");
        return filterRegistrationBean;
    }
}
```
