### nacos环境隔离

**环境隔离：namespace**

**Nacos中服务存储和数据存储的最外层都是一个名为namespace的东西，用来做最外层隔离**

![nacos结构](E:\笔记整理\微服务技术\图解\nacos结构.png)



**namespace下有group，group下面有service或者date，service下面有集群，集群下面有实例**

**所以 namespace可以对服务实例和数据进行隔离**

**不同namespace的东西是不能直接访问的**



**group默认就是DEFAULT_GROUP**

**namespace默认是public**



**在nacos控制台创建namespace**

**然后在配置文件里面声明服务属于哪个namespace即可，注意并不是在nacos控制台修改**



```xml
  cloud:
    nacos:
      server-addr: localhost:8848  #nacos服务地址
      discovery:
        cluster-name: CD  #成都集群
        namespace: c8f8c6a6-11bc-453e-89e2-e03494753add  #写namespace的id
```



**把所有的service移动到另一个namespace，这个时候再访问orderservice，报错，找不到对应的service**





**总结**

**Nacos环境隔离**

**①每个namespace都有唯一id**

**②服务设置namespace时要写id而不是名称**

**③不同namespace下的服务互相不可见**