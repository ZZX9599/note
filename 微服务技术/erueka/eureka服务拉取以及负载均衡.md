### eureka服务拉取以及负载均衡

**实际上就是把ip＋port改为微服务的名称**

**负载均衡：就是对于eureka的客户端中的微服务名称的列表，选中一个为其服务**



![服务拉取](E:\笔记整理\微服务技术\图解\服务拉取.png)



**userservice就是注册到eureka的微服务名称**





**为了实现负载均衡，可以加上@LoadBalanced**

```java
@Bean
@LoadBalanced
public RestTemplate restTemplate(){
    return new RestTemplate();
}
```

