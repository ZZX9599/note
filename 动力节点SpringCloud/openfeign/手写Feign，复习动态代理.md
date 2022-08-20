### 手写Feign，复习动态代理



**JDk动态代理：只要执行代理对象的方法，必定走invoke**



```java
public interface InvocationHandler {
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable;
}
```



**参数一：代理对象**

**参数二：方法**

**参数三：参数**



****



**openfeign为什么写了接口，就能调用，实际上是创建了代理对象**



**我们现在手动给这个类创建代理对象**

```java
package com.zzx.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

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
}

```

```java
    public static void main(String[] args) {

        UserOrderFeign userOrderFeignProxyInstance = 		   (UserOrderFeign)Proxy.newProxyInstance(UserOrderFeign.class.getClassLoader(), new Class[]{UserOrderFeign.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("进入接口的动态代理");
                return "代理成功！";
            }
        });

        String s = userOrderFeignProxyInstance.orderService();

        System.out.println("======="+s+"=======");
    }
```

**以上是复习动态代理，不清楚记得回看gitee**



#### 手写OpenFeign核心

**核心：通过动态代理来拿到@FeignClient的接口，接口声明了远程调用的微服务名称，去eureka注册中心找**

**到，解析为ip+port，远程调用即可**