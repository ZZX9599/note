### GateWay简介



**你们项目里面 用的什么网关？ gateway zuul**

**它是 Spring Cloud 官方提供的用来取代 zuul（netflix）的新一代网关组件**

**zuul：1.0 , 2.0 ，zuul 的本质，一组过滤器，根据自定义的过滤器顺序来执行**

**本质就是 web 组件 **

**web 三大组件（监听器 过滤器 servlet） **

**拦截器：springmvc，拦截controller**

**Zuul1.0 使用的是 BIO（Blocking IO） tomcat7.0 以前都是 BIO 性能一般 **

**Zuul2.0 性能好 NIO **

**AIO 异步非阻塞 io a+nio = aio = async + no blocking io**

**它基于 spring5.x，springboot2.x 和 ProjectReactor 等技术。**

**它的目地是让路由更加简单，灵活，还提供了一些强大的过滤器功能，例如：熔断、限流、重 试,自义定过滤器等 **

**token 校验 ip 黑名单等**



**SpringCloud Gateway作为Spring Cloud生态的网关，目标是替代Zuul，在SpringCloud2.0 以上的版本中，没**

**有对新版本的 zuul2.0 以上的最新高性能版本进行集成，仍然还是使用的 zuul1.x[可以看项目依赖找到]非 **

**Reactor 模式的老版本。而为了提升网关的性能， SpringCloud Gateway 是基于 webFlux 框架实现的，而 **

**webFlux 框架底层则使用了高性能 的 Reactor 模式通信框架的 Netty NIO(非阻塞式 io) BIO **

**你只需要了解网关能做什么？ 网关里面写什么代码就可以了**



![gongneng](E:\笔记整理\动力节点SpringCloud\图解\gongneng.png)





![工作流程](E:\笔记整理\动力节点SpringCloud\图解\工作流程.png)



**网关的核心就是一组过滤器，按照先后执行顺序来执行顺序操作，标志order，越小越先执行**

**客户端向 springcloud Gateway 发出请求，然后在 Gateway Handler Mapping 中找到与 请求相匹配的路**

**由，将其发送到 Gateway Web Handler。Handler 再通过指定的过滤器来将请求发送到我们实际的服务的业**

**务逻辑，然后返回。 过滤器之间用虚线分开是因为过滤器可能会在发送爱丽请求之前【pre】或之后【post】**

**执行业务逻辑，对其进行加强或处理Filter **

**在 【pre】 类型的过滤器可以做参数校验、权限校验、流量监控、日志输出、协议转 换等 **

**在【post】 类型的过滤器中可以做响应内容、响应头的修改、日志的输出，流量监控等有着 非常重要的作用。 **



#### **总结：Gateway 的核心逻辑也就是 路由转发 + 执行过滤器链**



**filter1->filter2-interceptor->controller->interceptor->filter2->filter1**