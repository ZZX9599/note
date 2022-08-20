### Gateway没有结合eureka存在的问题

**没有结合eureka，无法做负载均衡**

**假设有五个登陆的微服务，端口不同，我们没有把gateway注册到eureka，则每个服务自己写路径，也不能负载**

**可以通过ribbon来实现负载均衡**



```yml
spring:
  application:
    name: gateway-service
  cloud:
    gateway:
      enabled: true  #开启gateway
      routes:
        - id: login-service-route
          uri: lb://login-service
          predicates: #断言匹配
            - Path=/doLogin #和服务中的路径匹配,是正则匹配的模式
```

**lb://login-service使用这个可以通过ribbon去拉取服务列表，但是gateway自己是不知道微服务列表的**



**我们还可以把gateway加入到eureka，让gateway自己定期拉取服务列表，做负载均衡做动态路由**