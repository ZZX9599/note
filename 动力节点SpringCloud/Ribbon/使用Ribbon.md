### 使用Ribbon



#### **准备两个提供者**



**提供者A**

```java
@RestController
public class ProviderController01 {

    @GetMapping("/hello")
    public String hello(){
        return "我是provider-a";
    }
}
```



```yml
server:
  port: 8081

spring:
  application:
    name: provider

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka

  instance:
    hostname: localhost
    instance-id: ${eureka.instance.hostname}:${spring.application.name}:${server.port}
    prefer-ip-address: true
```





**提供者B**

```java
@RestController
public class ProviderController02 {

    @GetMapping("/hello")
    public String hello(){
        return "我是provider-b";
    }
}
```



```yml
server:
  port: 8082

spring:
  application:
    name: provider

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka

  instance:
    hostname: localhost
    instance-id: ${eureka.instance.hostname}:${spring.application.name}:${server.port}
    prefer-ip-address: true
```





**消费者**

```java
@SpringBootApplication
@EnableEurekaClient
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    /**
     * 加上了这个注解，则restTemplate被ribbon接管
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```



```java
@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;


    @GetMapping("/testRibbon")
    public String testRibbon(String applicationName){

        //正常来说，url需要用ip和端口，这里我们把提供者注册到了注册中心，直接写微服务名称即可

        //ribbon会把注册中心里面的微服务解析为ip+port

        //ribbon首先会拦截，从eureka里面拿到服务集合，再通过负载均衡算法来拿到一个服务的ip+port
        
        //注意：这个时候不能再直接发送ip+port，因为这个restTemplate不是之前的了，已经被ribbon接管
        
        //如果需要使用ip+port  需要自己再new一个

        String url="http://"+applicationName+"/hello";

        String result = restTemplate.getForObject(url, String.class);

        return result;
    }
}
```



```yml
server:
  port: 8083

spring:
  application:
    name: consumer

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka

  instance:
    hostname: localhost
    instance-id: ${eureka.instance.hostname}:${spring.application.name}:${server.port}
    prefer-ip-address: true
```



**访问：http://localhost:8083/testRibbon?applicationName=provider**

**默认是轮询算法**

**先启动provider，在启动consumer**



```apl
Ribbon 要做什么事情？
先通过 "http://" + serviceId + "/info" 我们思考 ribbon 在真正调用之前需要做什么？
restTemplate.getForObject(“http://provider/info”, String.class);
想要把上面这个请求执行成功，我们需要以下几步
1. 拦截该请求；
2. 获取该请求的 URL 地址:http://provider/info
3. 截取 URL 地址中的 provider
4. 从服务列表中找到 key 为 provider 的服务实例的集合(服务发现)
5. 根据负载均衡算法选出一个符合的实例
6. 拿到该实例的 host 和 port，重构原来 URL 中的 provider
7. 真正的发送 restTemplate.getForObject(“http://ip:port/info”，String.class)
```