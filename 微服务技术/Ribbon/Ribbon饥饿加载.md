### Ribbon饥饿加载



**第一次加载比较慢，第一册访问了之后，一些服务列表会放进缓存队列**



![饥饿加载配置](E:\笔记整理\微服务技术\图解\饥饿加载配置.png)

```xml
ribbon:
  eager-load:
    enabled: true  #开启饥饿加载
    clients:
      - service  #指定饥饿加载的微服务名称
```

**默认不是饥饿加载**

**clients是一个集合**



```
ribbon:
  eager-load:
    enabled: true  #开启饥饿加载
    clients:
      - service  #指定饥饿加载的微服务名称
      - userservice  #多个就这样加载进去
```

