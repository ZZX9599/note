### Dubbo常用标签

**Dubbo 中常用标签。分为三个类别：公用标签，服务提供者标签，服务消费者标签**



#### **公用标签**

**<dubbo:application/> 和 <dubbo:registry/>**

**A、配置应用信息**

```xml
<dubbo:application name=”服务的名称”/>
```

**B、配置注册中心**

```xml
<dubbo:registry address=”ip:port” protocol=”协议”/>
```



#### 服务提供者标签

**配置暴露的服务**

```xml
<dubbo:service interface=”服务接口名” ref=”服务实现对象 bean”>
```



#### 服务消费者标签

**配置服务消费者引用远程服务**

```xml
<dubbo:reference id=”服务引用 bean 的 id” interface=”服务接口名”/>
```

