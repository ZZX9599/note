### 实践-XA模式



#### 数据库的XA模式

**成功：**



![XA](E:\笔记整理\微服务技术\seata\图片\XA.png)



**失败：**

![XA失败](E:\笔记整理\微服务技术\seata\图片\XA失败.png)



**以上的基于数据库【只要数据库支持】都能做成，就是根据数据库的事务，等待其他事务的通知，具有强一致性**



#### **seata的XA模式和这个基本类似，就是增加了一个全局事务管理TM**



![seata-xa](E:\笔记整理\微服务技术\seata\图片\seata-xa.png)

**XA模式的优点是什么？**

**•事务的强一致性，满足ACID原则。**

**•常用数据库都支持，实现简单，并且没有代码侵入**

**XA模式的缺点是什么？**

**•因为一阶段需要锁定数据库资源，等待二阶段结束才释放，性能较差**

**•依赖关系型数据库实现事务**



#### 实现XA模式

**Seata的starter已经完成了XA模式的自动装配，实现非常简单，步骤如下：**

**1：修改配置，开启XA模式**

```yml
seata:
  data-source-proxy-mode: XA
```



**2：给发起全局事务的入口方法添加@GlobalTransactional注解**

**create方法调用了两个远程服务，这就是入口，TM就会从这里开始**

```java
@Override
@GlobalTransactional
public Long create(Order order) {
    // 创建订单
    orderMapper.insert(order);
    try {
        // 远程服务，扣用户余额
        accountClient.deduct(order.getUserId(), order.getMoney());
        // 远程服务，扣库存
        storageClient.deduct(order.getCommodityCode(), order.getCount());

    } catch (FeignException e) {
        log.error("下单失败，原因:{}", e.contentUTF8(), e);
        throw new RuntimeException(e.contentUTF8(), e);
    }
    return order.getId();
}
```