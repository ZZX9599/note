### SpringMVC的介绍

**SpringMVC相当于增强的Servlet**

**SpringMVC属于Spring框架的一个模块**

**SpringMVC主要是使用@Controller注解来扫描控制器对象【在配置文件中声明组件扫描器即可】**

**这个时候就能创建对应的控制器对象放在Spring容器中**

**容器的创建时间：在DispatcherServlet接收到请求的时候，这个时候需要分配请求**

**就需要扫描含有注解的位置，就要使用对应的配置文件来查找**



### **SpringMVC的处理流程**

**1：Tomcat启动**

**2：用户发起请求，创建DispatcherServlet对象【继承HttpServlet】**

**3：创建DispatcherServlet的时候，会执行init()方法，创建SpringMVC的容器**

**4：创建SpringMVC容器的时候，会默认读取配置文件，也可以自己指定**

**5：上面创建的是组件扫描器指定的含有@Controller的对象，放入SpringMVC容器**

**6：容器是Map  map.put("controller1",controller对象)**