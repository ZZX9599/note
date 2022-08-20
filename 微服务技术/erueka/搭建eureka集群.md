### 搭建eureka集群



#### eureka-server-01

```yml
server:
  port: 8761

spring:
  application:
    name: eureka-server

eureka:
  client:
    service-url:
      defaultZone:
        - http://localhost:8762/eureka,
        - http://localhost:8763/eureka

  instance:
    instance-id: ${eureka.instance.hostname}:${spring.application.name}:${server.port}  
    #服务id
    hostname: localhost  #主机
    prefer-ip-address: true  #显示ip信息
    lease-renewal-interval-in-seconds: 5  #多少时间进行续约
```



#### eureka-server-02

```yml
server:
  port: 8762

spring:
  application:
    name: eureka-server

eureka:
  client:
    service-url:
      defaultZone:
        - http://localhost:8761/eureka,
        - http://localhost:8763/eureka

  instance:
    instance-id: ${eureka.instance.hostname}:${spring.application.name}:${server.port}  
    #服务id
    hostname: localhost  #主机
    prefer-ip-address: true  #显示ip信息
    lease-renewal-interval-in-seconds: 5  #多少时间进行续约
```



#### eureka-server-03

```yml
server:
  port: 8763

spring:
  application:
    name: eureka-server

eureka:
  client:
    service-url:
      defaultZone:
        - http://localhost:8761/eureka,
        - http://localhost:8762/eureka

  instance:
    instance-id: ${eureka.instance.hostname}:${spring.application.name}:${server.port}  
    #服务id
    hostname: localhost  #主机
    prefer-ip-address: true  #显示ip信息
    lease-renewal-interval-in-seconds: 5  #多少时间进行续约
```



#### 测试，浏览器查看副本

![eureka无集群](E:\笔记整理\微服务技术\图解\eureka无集群.png)



**表示没有集群**





```json
127.0.0.1  peer1

127.0.0.1  peer2

127.0.0.1  peer3
```

**修改系统的配置，假设三个域名**



```yml
server:
  port: 8761

spring:
  application:
    name: eureka-server

eureka:
  client:
    service-url:
      defaultZone:
        - http://peer2:8762/eureka,
        - http://peer3:8763/eureka

  instance:
    instance-id: ${eureka.instance.hostname}:${spring.application.name}:${server.port}  
    #服务id
    hostname: peer1  #主机
    prefer-ip-address: true  #显示ip信息
    lease-renewal-interval-in-seconds: 5  #多少时间进行续约
```



**像这样配置，就可以了**

**eureka集群配置的思想，就是自己注册自己，再把自己注册给别人，机器之间互相注册**

