### 在Web项目使用Spring

**web项目中怎么使用容器对象**

**做javase项目，有main方法，执行代码是执行main方法的，在main里面创建的容器对象 ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");**

**web项目是在tomcat服务器上运行的。 tomcat一起动，项目一直运行的**

**需求：
web项目中spring容器对象只需要创建一次，把容器对象放入到全局作用域ServletContext中**

**当把获取容器的操作放在Servlet中，每次用户访问都会创建容器，很浪费资源，解决方法：只创建一次**



**方案一：将 Spring 容器的创建放在 Servlet 进行初始化时进行，即执行Servlet的init()方法时执行。并且Servlet 还是单例多线程的，即一个业务只有一个 Servlet 实例，所有执行该业务的用户执行的都是这一个 Servlet 实例。这样，Spring容器就具有了唯一性了。**

**但是，Servlet是一个业务一个 Servlet实例，即 LoginServlet 只有一个，但还会有 StudentServlet、TeacherServlet 等。每个业务都会有一个 Servlet，都会执行自己的 init()方法， 也就都会创建一个 Spring 容器了。这样一来，Spring 容器就又不唯一了**





**方案二：对于 Web 应用来说，ServletContext 对象是唯一的，一个 Web 应用，只有一个 ServletContext 对象，该对象是在 Web 应用装载时初始化的。若将 Spring 容器的创建时机， 放在 ServletContext 初始化时，就可以保证 Spring 容器的创建只会执行一次，也就保证了 Spring 容器在整个应用中的唯一性**

**当 Spring 容器创建好后，在整个应用的生命周期过程中，Spring 容器应该是随时可以被访问的。即，Spring 容器应具有全局性。而放入 ServletContext 对象的属性，就具有应用的 全局性。所以，将创建好的 Spring 容器，以属性的形式放入到 ServletContext 的空间中，就保证了 Spring 容器的全局性。**



**上述的这些工作，已经被封装在了如下的 Spring 的 Jar 包的相关 API 中： spring-web**



**怎么实现：
使用监听器 当全局作用域ServletContext的对象被创建时【应用启动的时候】创建容器存入ServletContext**



**监听器作用：
1）创建容器对象，执行 ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
2）把容器对象放入到ServletContext， ServletContext.setAttribute(key,ctx)**



```Java
<!--注册监听器,把spring的容器在web启动的时候就创建上-->
<!--让web容器创建的时候创建唯一的一次spring容器-->
<!--监听器创建的时候，会默认读取WEB/INF/applicationContext.xml,没有的话报错-->
<!--一般我们都是自定义位置，使用classpath:-->
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
</context-param>
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```