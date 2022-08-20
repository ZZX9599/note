### Sleuth 快速入门



**直接使用之前的微服务工程化项目**

**在order-center和user-center加入依赖**

```xml
<!--zipkin依赖-->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-zipkin</artifactId>
    <version>2.2.8.RELEASE</version>
</dependency>
```

**因为这两个都依赖api-project，直接加入api-project即可**



```yml
spring:
  zipkin:
    base-url: http://localhost:9411
  sleuth:
    sampler:
      rate: 10 
      #为了使用速率限制采样器,选择每秒间隔接受的trace量，最小数字为0，最大值为2147483647[int最大值]默	   认为 10
      probability: 1 #配置采样率 默认的采样比例为:0.1，即10%，所设置的值介于0到1之间，1表示全部采集
```

**两个服务都加上配置即可**



**启动访问，远程调用一下**

**查看 zipkin**

**点一下依赖**