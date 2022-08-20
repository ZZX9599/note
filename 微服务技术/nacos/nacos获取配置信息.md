### nacos获取配置信息

![读取nacos配置文件](E:\笔记整理\微服务技术\图解\读取nacos配置文件.png)



**在读取配置文件 application.yml之前，应该先拿到nacos的地址信息，才能读取到nacos的配置文件**

**bootstrap.yml的优先级更高**

**bootstrap 优于 application ,`*.properties`优于`*.yml`**



**步骤01：**

```xml
<!--引入nacos配置管理依赖-->
<dependency>   
	<groupId>com.alibaba.cloud</groupId>    
	<artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
```

**步骤02：**

**在userservice中的resource目录添加一个bootstrap.yml文件，这个文件是引导文件**

**优先级高于application.yml**



**步骤03：**

**我们在nacos里面配置的配置信息为   userservice-dev.yaml**

```xml
spring:
  application:
    name: userservice # 服务名称
  profiles:    
    active: dev #开发环境，这里是dev   
  cloud:    
    nacos:      
      server-addr: localhost:8848 # Nacos地址
      config:
        file-extension: yaml # 文件后缀名
```



**测试：读取配置文件**

```xml
pattern:
  dateformat: yyyy-MM-dd HH:mm:ss
```

**配置文件如上**

**测试使用@Value可以拿到配置文件的信息**





**总结：**

**将配置交给Nacos管理的步骤**

**①在Nacos中添加配置文件**

**②在微服务中引入nacos的config依赖**

**③在微服务中添加bootstrap.yml，配置nacos地址、当前环境、服务名称、文件后缀名。这些决定了程序启动时去nacos读取哪个文件**