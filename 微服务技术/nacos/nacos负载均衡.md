### nacos负载均衡

**之前已经配置了userservice两个集群  CD和SH**





**我们现在把orderservice添加到CD集群**

![服务消费分别集群](E:\笔记整理\微服务技术\图解\服务消费分别集群.png)

**经过测试：这个时候，依然是轮询策略**

**我们需要进行修改IRule**



**直接在orderservice配置文件修改更加方便**

**指定对userservice的负载均衡方式**

```xml
userservice:
  ribbon:
    NFLoadBalancerRuleClassName: com.alibaba.cloud.nacos.ribbon.NacosRule # 负载均衡规则 
```

**这个方式，只会优先选择当前集群，除非当前集群的userservice全部宕机，才会访问其他集群**

**对于当前集群的访问，采用的是随机方式**



**测试：把CD集群的userservice全部关闭，这个时候我们发现访问了SH集群**



**会出现警告**

![跨集群警告](E:\笔记整理\微服务技术\图解\跨集群警告.png)





**总结：**

**1.NacosRule负载均衡策略**

**①优先选择同集群服务实例列表**

**②本地集群找不到提供者，才去其它集群寻找，并且会报警告**

**③确定了可用实例列表后，再采用随机负载均衡挑选实例**