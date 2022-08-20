### Feign传参数



#### 提供者的接口：

```java
package com.zzx.controller;

import com.zzx.domain.Order;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: ZZX
 * @Date: 2022/6/25 9:06
 * 1.通过 URL 传参数，GET 请求，参数列表使用 @PathVariable（""）
 * 2.如果是 GET 请求，每个基本参数必须加 @RequestParam（""）
 * 3.如果是 POST 请求，而且是对象集合等参数，必须加@Requestbody
 *
 * 测试url传递参数
 * get传递一个参数
 * get传递多个参数
 * post传递一个对象
 * post传递一个对象+一个基本参数
 */

@RestController
public class ParamController {

    /**
     * 测试URL传递参数,res风格
     * @param name
     * @param age
     * @return
     */
    @GetMapping("/testUrl/{name}/and/{age}")
    public String testUrl(@PathVariable("name") String name,@PathVariable Integer age){
        return "name:"+name+"age:"+age;
    }


    /**
     * get传递一个参数
     * @param name
     * @return
     */
    @GetMapping("/testGetOneParam")
    public String testGetOneParam(@RequestParam String name){
        return "name:"+name;
    }


    /**
     * get传递两个参数
     * @param name
     * @return
     */
    @GetMapping("/testGetTwoParam")
    public String testGetTwoParam(@RequestParam String name,@RequestParam Integer age){
        return "name:"+name+"age:"+age;

    }


    /**
     * post传递一个对象
     * @param order
     * @return
     */
    @PostMapping("/testOneObj")
    public String testOneObj(@RequestBody Order order){
        return order.toString();
    }


    /**
     * post传递一个对象+一个参数
     * @param order
     * @return
     */
    @PostMapping("/testObjAndParam")
    public String testObjAndParam(@RequestBody Order order,@RequestParam String name){
        return order.toString()+name;
    }

    /**
     * 测试时间
     * 不建议单独发送时间
     * 1：可以放在对象里面或者字符串里面
     * 2：可以使用LocalDate.now方法  年月日【没有差错】
     * 3：可以使用LocalDate.time方法  时间 【缺少秒】
     * @param date
     * @return
     */
    @GetMapping("/testTime")
    public String testTime(@RequestParam Date date){
        return date.toString();
    }
}

```





#### 消费者的feign接口

```java
package com.zzx.feign;

import com.zzx.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author: ZZX
 * @Date: 2022/6/24 9:42
 * FeignClient开启注解  value就是远程调用的微服务名称
 */

@FeignClient(value = "order-service")

public interface UserOrderFeign {

    /**
     * 远程调用orderService
     * @return
     */
    @GetMapping("/orderService")
    public String orderService();

    /**
     * 测试URL传递参数,res风格
     * @param name
     * @param age
     * @return
     */
    @GetMapping("/testUrl/{name}/and/{age}")
    public String testUrl(@PathVariable("name") String name, @PathVariable Integer age);

    /**
     * get传递一个参数
     * @param name
     * @return
     */
    @GetMapping("/testGetOneParam")
    public String testGetOneParam(@RequestParam String name);

    /**
     * get传递两个参数
     * @param name
     * @return
     */
    @GetMapping("/testGetTwoParam")
    public String testGetTwoParam(@RequestParam String name,@RequestParam Integer age);

    /**
     * post传递一个对象
     * @param order
     * @return
     */
    @PostMapping("/testOneObj")
    public String testOneObj(@RequestBody Order order);

    /**
     * post传递一个对象+一个参数
     * @param order
     * @return
     */
    @PostMapping("/testObjAndParam")
    public String testObjAndParam(@RequestBody Order order,@RequestParam String name);

    /**
     * 测试时间
     * 不建议单独发送时间
     * 1：可以放在对象里面或者字符串里面
     * 2：可以使用LocalDate.now方法  年月日【没有差错】
     * 3：可以使用LocalDate.time方法  时间 【缺少秒】
     * @param date
     * @return
     */
    @GetMapping("/testTime")
    public String testTime(@RequestParam Date date);
}

```





#### 消费者远程调用：

```java
package com.zzx.controller;

import com.zzx.domain.Order;
import com.zzx.feign.UserOrderFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Date;

/**
 * @Author: ZZX
 * @Date: 2022/6/24 9:37
 */

@Slf4j
@RestController
public class UserController {

    @Resource
    private UserOrderFeign userOrderFeign;

    @GetMapping("/userService")
    public String userController(){
        log.info("进入到用户模块");

        log.info("即将发起远程请求");

        String result = userOrderFeign.orderService();

        return result;
    }
    

    @GetMapping("/testParam")
    public String testParam() {
        String resultUrl = userOrderFeign.testUrl("zzx", 20);
        System.out.println(resultUrl);

        String resultOneParam = userOrderFeign.testGetOneParam("zzx");
        System.out.println(resultOneParam);

        String resultTwoParam = userOrderFeign.testGetTwoParam("zzx", 20);
        System.out.println(resultTwoParam);

        Order order = Order.builder().id("1001").name("牛排").price(102D).time(new Date()).build();
        String resultOneObj = userOrderFeign.testOneObj(order);
        System.out.println(resultOneObj);

        String resultObjAndParam = userOrderFeign.testObjAndParam(order, "张三");
        System.out.println(resultObjAndParam);

        Date date=new Date();
        System.out.println("发送时间:"+date);
        String testTime = userOrderFeign.testTime(date);
        System.out.println("收到的时间:"+testTime);
        return "ok";
    }
}

```





#### 日志：

```yml
logging:
  level:
    com.zzx.feign.UserOrderFeign: debug
```



```java
    /**
     * feign的日志
     * @return
     */
    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;
    }
```

