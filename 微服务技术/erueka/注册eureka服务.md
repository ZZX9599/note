### 注册eureka服务

![注册uereka](E:\笔记整理\微服务技术\图解\注册uereka.png)



**eureka客户端依赖**

```xml
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
        </dependency>
```



**配置文件**

**配置文件只需要指定微服务名称和eureka地址**



**service就是@EnableEurekaServer**

**client技术@EnableEurekaClient**



**注意：spring-boot-starter-client没有web依赖**

****

**spring-boot-starter-server有web依赖**