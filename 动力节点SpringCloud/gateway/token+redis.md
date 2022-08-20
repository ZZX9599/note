### token+redis





![token+redis](E:\笔记整理\动力节点SpringCloud\图解\token+redis.jpg)



**为什么要使用token,因为HTTP是无状态协议**

**使用token的意义就是让这次发送变成有状态的**



**在网关加入一个全局过滤器，判断路径，是登陆的路径就放行，否则验证token**

**验证token成功，放行，否则拦截**

**登陆成功之后，生成一个token，存入redis的key里面，value存放用户登陆的对象**

**在整个项目中，其他的微服务不需要做其他的，只需要加入eureka即可，网关注册到eureka，会自动的拉取其**

**他微服务，当你通过微服务名称访问其他的微服务的时候，就会走网关的全局过滤器来验证token**



### **演示：**



#### 网关的过滤器：

```java
package com.zzx.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: ZZX
 * @Date: 2022/6/28 10:07
 * 请求一般放在请求头里面
 * 一般key叫做 Authorization
 * 一般value是 bearer token
 * 这是全局过滤器，不能直接先拿token，先拿到路径，如果是登录，放行，不是就验证token
 */

@Component
public class CheckTokenFilter implements GlobalFilter, Ordered {

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 放行的路径
     */

    private static final List<String> ALLOW_LIST = Arrays.asList("/login-service/doLogin");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //拿到url
        String path = exchange.getRequest().getURI().getPath();
        System.out.println(path);

        //判断
        if (ALLOW_LIST.contains(path)) {
            return chain.filter(exchange);
        }

        //检查token
        List<String> authorization = exchange.getRequest().getHeaders().get("Authorization");

        if(authorization!=null&&authorization.size()!=0){
            //判断token是否有效
            String token = authorization.get(0);

            //一般value是 bearer token
            //所以我们还要去除bearer

            String[] s = token.split(" ");
            token= s[1];
            
            //这下来判断token是否和redis的key一致
            if (token.length() != 0 && stringRedisTemplate.hasKey(token)) {
                return chain.filter(exchange);
            }
        }

        //拦截
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().set("content-type","application/json;charset=utf-8");

        HashMap<String, Object> stringObjectHashMap = new HashMap<>(4);

        stringObjectHashMap.put("code", HttpStatus.UNAUTHORIZED.value());
        stringObjectHashMap.put("msg","未授权");

        ObjectMapper objectMapper = new ObjectMapper();
        byte[] bytes=null;
        try {
             bytes= objectMapper.writeValueAsBytes(stringObjectHashMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(wrap));

    }

    @Override
    public int getOrder() {
        return 1;
    }
}

```



#### 网关配置文件：

```yml
server:
  port: 80

spring:
  application:
    name: gateway-service
    
  cloud:
    gateway:
      enabled: true  #开启gateway
      
      routes:
        - id: login-service-route
          uri: http://localhost:8081
          predicates: #断言匹配，断言是给路由使用的
            - Path=/doLogin #和服务中的路径匹配,是正则匹配的模式
            - After=2022-06-27T16:48:29.089+08:00[Asia/Shanghai]  #在时间之后才能访问
            - Method=GET,POST  #指定请求方式
            - Query=name  #必须传入参数name
            
      discovery:
        locator:
          enabled: true   #开启动态路由
          lower-case-service-id: true  #开启小写字母，eureka默认是大写微服务名称
          
  redis:
    host: 192.168.220.148

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
    registry-fetch-interval-seconds: 3  #拉取服务列表的时间，对于gateway来说短一点好点，默认30S

  instance:
    hostname: localhost
    instance-id: ${eureka.instance.hostname}:${spring.application.name}:${server.port}
```



#### 登录：

```java
package com.zzx.controller;

import com.zzx.domain.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.UUID;

/**
 * @Author: ZZX
 * @Date: 2022/6/27 15:39
 */

@RestController
public class LoginController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/doLogin")
    public String doLogin(){

        //假设已经查询了数据库
        User user=new User("1001","zzx","123",20);

        //拿到token
        String token = UUID.randomUUID().toString();

        //存入redis【为什么要存，因为HTTP是无状态协议】
        stringRedisTemplate.opsForValue().set(token,user.toString(), Duration.ofSeconds(7200));

        //返回token给前端
        return token;
    }
}
```



```yml
server:
  port: 8081

spring:
  application:
    name: login-service

  redis:
    host: 192.168.220.148

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka

  instance:
    hostname: localhost
    instance-id: ${eureka.instance.hostname}:${spring.application.name}:${server.port}
```





#### 其他服务

```yml
server:
  port: 8083

spring:
  application:
    name: other-service

eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka
```



```java
package com.zzx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZZX
 * @Date: 2022/6/28 10:40
 */

@RestController
public class OtherController {

    @GetMapping("/other")
    public String other(){
        return "成功！";
    }
}
```



**这个时候访问http://localhost/other-service/other就必须携带token，不然就失败！登录之后的redis里面的key复制下来，粘贴即可！**

