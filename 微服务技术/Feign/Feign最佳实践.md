### Feign最佳实践



**方式一（继承）：给消费者的FeignClient和提供者的controller定义统一的父接口作为标准。**

**服务紧耦合**

**父接口参数列表中的映射不会被继承**



**orederservice之所以能够调用到userservice，正是因为HttpClient设置了微服务名称，访问的方式，路径等，加上他们都注册到了nacos，所以能够根据HttpClient来找到对应的微服务的方法，执行远程调用**



![Feign最佳实践方式一](E:\笔记整理\微服务技术\图解\Feign最佳实践方式一.png)



**但是不推荐，当声明变了，HttpClient和Controller那边也需要更改**

**另外，并不支持SpringMVC，方法的实现还是需要自己写**



![Feign最佳实践方式一](E:\笔记整理\微服务技术\图解\Feign最佳实践方式二.png)



**因为如果不把feifn的clients抽取为独立的模块，每一个微服务想要调用Userservice，对应的微服务都要写HttpClient，就会造成重复开发，缺点：如果封装了某个微服务的10个方法，但是另一个微服务只需要1个，另外的九个也会引入，显得多余**



#### 方式二的实现：

**类似Dubbo最佳服务实践**

**实现最佳实践方式二的步骤如下：**

**1.首先创建一个module，命名为feign-api，然后引入feign的starter依赖**

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

**2.将order-service中编写的UserClient、User、DefaultFeignConfiguration都复制到feign-api项目中**

**3.在order-service中引入feign-api的依赖**

**4.修改order-service中的所有与上述三个组件有关的import部分，改成导入feign-api中的包**

**5.重启测试**



#### **注意：**

**当定义的FeignClient不在SpringBootApplication的扫描包范围时，这些FeignClient无法使用。**

**有两种方式解决：**

**方式一：指定FeignClient所在包**

```java
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguation.class,basePackages = "com.zzx.httpclients")
```

**方式二：指定FeignClient字节码**

```java
@EnableFeignClients(defaultConfiguration = DefaultFeignConfiguation.class,clients = UserClient.class)
```

