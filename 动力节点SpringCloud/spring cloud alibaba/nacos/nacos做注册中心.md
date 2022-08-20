### nacos做注册中心



**添加依赖：**

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```



**配置文件：**

```yml
server:
  port: 8081

spring:
  application:
    name: nacos-client-01

  # nacos信息
  cloud:
    nacos:
      #注册到nacos 默认是public 不指定命名空间，就在public，不指定分组，默认在DEFAULT_GROUP
      server-addr: http://localhost:8848
      username: nacos
      password: nacos
      discovery:
        service: jxl-service #注册到nacos默认是微服务名称，可任意自己修改
        namespace: 78f9bff9-ab1f-44ff-b493-291381e83b20 #命名空间id
        group: A_GROUP #注册到哪个组

# 暴露服务端点，健康检查
management:
  endpoints:
    web:
      exposure:
        include: '*'
```



**主启动类：**

```java
package com.zzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ZZX
 * 开启nacos
 */
@SpringBootApplication
@EnableDiscoveryClient  //开启服务发现客户端 也就是 nacosServer 的客户端
public class NacosClient01Application {

    public static void main(String[] args) {
        SpringApplication.run(NacosClient01Application.class, args);
    }
}
```



**服务发现：**

```java
@GetMapping("/info")
public String info(String serviceName){
    //服务发现从nacos拿到服务集合
    List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
    String result="";
    if(instances.size()!=0){
        ServiceInstance serviceInstance = instances.get(0);
        String host = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        result=host+port;
    }
    return result;
}
```

**传入微服务名称即可拿到ip和端口信息**

