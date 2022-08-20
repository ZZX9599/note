### nacos快速上手



**由于nacos还没有被加入到spring-cloud-dependencies里面**

**所以nacos的管理依赖需要自己添加到父工程**



**nacos的管理依赖，放进整个项目**

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-alibaba-dependencies</artifactId>
    <version>2.2.5.RELEASE</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```



**nacos的客户端依赖**

```xml
<!-- nacos客户端依赖包 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```



**nacos的默认端口为8848**



**在各个微服务里注册服务到nacos里面**

```ABAP
spring:
  application:
    name: service  #服务名称

  cloud:
    nacos:
      server-addr: localhost:8848  #nacos服务地址
```

**这样就能把微服务注册到nacos里，然后直接使用微服务的名称进行调用即可**

**注意：ribbon是通用的，一样可以在nacos里面使用**



**总结：**

**Nacos服务注册或发现**

**①引入nacos.discovery依赖**

**②配置nacos地址spring.cloud.nacos.server-addr**