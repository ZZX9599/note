### 项目引入和配置sentinel



![项目结构](E:\笔记整理\微服务技术\sentinel\图解\项目结构.png)



**微服务整合Sentinel**

**我们在order-service中整合Sentinel，并且连接Sentinel的控制台，步骤如下：**



**1：引入依赖**

```xml
<!--sentinel-->
<dependency>
	<groupId>com.alibaba.cloud</groupId>
	<artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>
```



**2：写配置**

```yml
spring:    
  cloud:  
    sentinel:
      transport:
        dashboard: localhost:80
```



**3：访问任意端点即可触发监控**

**访问任意一个Controller即可，任意的Controller都是一个端点**