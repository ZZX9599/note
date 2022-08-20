### eureka配置文件



![eureka常见配置](E:\笔记整理\微服务技术\图解\eureka常见配置.png)



#### eureka的配置分为三大类  server  client  instance实例



#### 服务端一般的配置

```yml
server:
  port: 8761  #端口

spring:
  application:
    name: eureka-server  #微服务名称


eureka:

  #server
  server:
    eviction-interval-timer-in-ms: 10000  #服务端间隔多少秒没有续约就执行剔除操作【删除死掉的服务】
    renewal-percent-threshold: 0.85       #超过85%的没有建立续约，则不删除任何服务，体现了AP原则

  #client一般不在server配置

  #instance
  instance:
    hostname: localhost  #主机
    instance-id: ${eureka.instance.hostname}:${spring.application.name}:${server.port}
    prefer-ip-address: true   #以ip显示服务信息
    lease-renewal-interval-in-seconds: 30  #服务续约的时间
```





#### 客户端配置

```yml
server:
  port: 1111

spring:
  application:
    name: eureka-clinet-a

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
    register-with-eureka: true  #是否注册在eureka
    fetch-registry: true  #拉取服务列表进缓存，增加速度
    registry-fetch-interval-seconds: 10  #为了缓解服务列表更新不及时问题，可以将时间设置的很短

  instance:
    hostname: localhost  #主机
    instance-id: ${eureka.instance.hostname}:${spring.application.name}:${server.port}
    prefer-ip-address: true  #是否显示ip
    lease-renewal-interval-in-seconds: 10 #十秒续约一次
```

