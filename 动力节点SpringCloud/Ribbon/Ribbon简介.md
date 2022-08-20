### Ribbon简介



**Spring Cloud Ribbon 是一个基于 HTTP 和 TCP 的客户端负载均衡工具，它基于 Netflix Ribbon 实现。**

**通过 Spring Cloud 的封装，可以让我们轻松地将面向服务的 REST 模版请求 自动转换成客户端负载均衡**

**的服务调用。 轮询 hash 权重 ... 简单的说 Ribbon 就是 netfix 公司的一个开源项目**

**主要功能是提供客户端负载均衡算法和服务调用。Ribbon 客户端组件提供了一套完善的配置项**

**比如连接超时，重试等。 在 Spring Cloud 构建的微服务系统中， Ribbon 作为服务消费者的负载均衡器**

**有两种使用方式，一种是和 RestTemplate 相结合，另一种是和 OpenFeign 相结合**

**OpenFeign 已经 默认集成了 Ribbon,关于 OpenFeign 的内容将会在下一章进行详细讲解**

**Ribbon 有很多子 模块，但很多模块没有用于生产环境! **

**文档：https://docs.spring.io/spring-framework/docs/current/reference/html/integration.html#spring-integration**



**Ribbon的使用架构：完成 远程调用+负载均衡**