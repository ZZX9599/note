### nacos做配置中心



**加入依赖：**

```xml
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```



**bootstrap.yml 比 application 有更高的优先级**



```yml
server:
  port: 8080

spring:
  application:
    name: nacos-config-01

  #项目启动的时候去哪里找配置文件
  cloud:
    nacos:
      server-addr: http://localhost:8848
      username: nacos
      password: nacos

      #微服务注册信息
      discovery:
        service: nacos-config-01
        namespace: 78f9bff9-ab1f-44ff-b493-291381e83b20
        group: A-GROUP

      config:
        namespace: 78f9bff9-ab1f-44ff-b493-291381e83b20
        group: A-GROUP
        prefix: nacos-config-01  #配置的Date-Id
        file-extension: yml #配置文件后缀
```