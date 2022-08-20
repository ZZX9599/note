### nacos多环境配置共享



![多环境配置共享](E:\笔记整理\微服务技术\图解\多环境配置共享.png)



**创建userservice.yaml配置文件**

```yaml
pattern:
  envSharedValue: 环境共享属性
```



**这样的话，启动带有dev环境的userservice服务**

**就会同时读取userservice-dev.yaml和userservice.yaml配置文件**



**如果把环境改为test**

**就会同时读取userservice-test.yaml和userservice.yaml配置文件**



**本地application.yml和nacos远程环境userservice.yaml发送冲突的时候，使用的是nacos的配置文件**

**userservice.yaml和userservice-dev.yaml发送冲突的时候，使用的是userservice-dev配置文件**



**远端带环境的>远端>本地**



**总结：**

**微服务会从nacos读取的配置文件：**

**①[服务名]-[spring.profile.active].yaml，环境配置**

**②[服务名].yaml，默认配置，多环境共享**

**优先级：**

**①[服务名]-[环境].yaml >[服务名].yaml > 本地配置**