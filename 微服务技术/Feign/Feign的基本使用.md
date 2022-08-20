### Feign的基本使用

**RestTemplate方式调用存在的问题**

**先来看我们以前利用RestTemplate发起远程调用的代码：**

```java
String url="http://userservice/user/"+order.getUserId();
User user=restTemplate.getForObject(url,User.class);
```

**存在下面的问题：**

**•代码可读性差，编程体验不统一**

**•参数如果比较多的话，非常复杂，URL难以维护**



**Feign是一个声明式的http客户端，官方地址：https://github.com/OpenFeign/feign**

**其作用就是帮助我们优雅的实现http请求的发送，解决上面提到的问题。**





#### **定义和使用Feign客户端**

使用Feign的步骤如下：

1.引入依赖：

```xml
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

2.在order-service的启动类添加注解开启Feign的功能

```java
@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
@EnableFeignClients
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
```

3.编写feign的客户端接口

```java
@FeignClient("userservice")
public interface UserClient {
    @GetMapping("/user/{id}")
    /**
     * 根据user的id查询用户
     */
    User findById(@PathVariable("id") Long id);
}
```

4.orderservice的微服务直接使用即可

```java
@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;
   
   @Resource
   private UserClient userClient;
    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        Order order=orderService.queryOrderById(orderId);

        User user = userClient.findById(order.getUserId());

        order.setUser(user);

        return order;
    }
}
```

**总结**

**Feign的使用步骤**

**①引入依赖**

**②添加@EnableFeignClients注解**

**③编写FeignClient接口**

**④使用FeignClient中定义的方法代替RestTemplate**