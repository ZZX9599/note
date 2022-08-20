### 集成 openfeign 做远程调用和负载均衡

**启动多台 alibaba-nacos-provider**

**添加 openfeign 的依赖，注意还需要 cloud 的依赖管理，因为openfeign是cloud组件**

**记得版本对应关系，不然出现未知错误**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```



```java
package com.zzx.controller;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: ZZX
 * @Date: 2022/6/29 9:37
 */
@RestController
public class ProviderController {

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/provider")
    public String provider(){
        List<ServiceInstance> instances = discoveryClient.getInstances("zzx-service");

        ServiceInstance serviceInstance = instances.get(0);

        return "提供者"+serviceInstance.getPort();
    }
}

```

**启动项，改端口**



**消费者：**



**feign端：**

```java
package com.zzx.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: ZZX
 * @Date: 2022/6/29 9:43
 */
@FeignClient(value = "zzx-service")
public interface ProviderFeign {
    /**
     * feign远程调用zzx-service的provider的api
     * @return
     */
    @GetMapping("/provider")
    public String provider();
}
```



**启动类：**

```java
package com.zzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ZZX
 * 开启nacos
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosClient01Application {

    public static void main(String[] args) {
        SpringApplication.run(NacosClient01Application.class, args);
    }

}
```



**接口：**

```java
@GetMapping("/consumer")
public String consumer(){
    String provider = providerFeign.provider();
    return "远程调用信息:"+provider;
}
```