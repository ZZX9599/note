### Feign整合sentinel

**SpringCloud中，微服务调用都是通过Feign来实现的，因此做客户端保护必须整合Feign和Sentinel**



**1.修改OrderService的application.yml文件，开启Feign的Sentinel功能**

```yml
feign:
	sentinel:
  		enabled: true
```



**2.给FeignClient编写失败后的降级逻辑**

**①方式一：FallbackClass，无法对远程调用的异常做处理**

**②方式二：FallbackFactory，可以对远程调用的异常做处理，我们选择这种**



**我们一般都是使用FallbackFactory**



**创建熔断后的处理逻辑对象，实现FallbackFactory接口，添加泛型**

```java
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public User findById(Long id) {
                System.out.println("我是熔断备份处理逻辑");
                return null;
            }
        };
    }
}
```

**如果是使用了feign-api拆分出来的形式，就要在调用这里确保启动的时候有这个对象放进容器即可**

**添加到spring容器里面**

**声明客户端的时候指定使用这个类即可**

```java
@FeignClient(value = "userservice",fallbackFactory = UserClientFallbackFactory.class)
public interface UserClient {

    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long id);
}
```





**Sentinel支持的雪崩解决方案：**

**•线程隔离（仓壁模式）**

**•降级熔断**

**Feign整合Sentinel的步骤：**

**•在application.yml中配置：feign.sentienl.enable=true**

**•给FeignClient编写FallbackFactory并注册为Bean**

**•将FallbackFactory配置到FeignClient**