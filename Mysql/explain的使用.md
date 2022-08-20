### explain的使用



**EXPLAIN 或者 DESC命令获取 MySQL 如何执行 SELECT 语句的信息**

**包括在 SELECT 语句执行 过程中表如何连接和连接的顺序。**

```sql
explain select * from emp
```



**Explain 执行计划中各个字段的含义**



![QQ截图20220507201658](E:\笔记整理\Mysql\图片\QQ截图20220507201658.png)



![QQ截图20220507201448](E:\笔记整理\Mysql\图片\QQ截图20220507201448.png)



#### 验证索引的快速性

**假设有一千万条数据**



**没有索引的情况下【全表扫描】**

```sql
select * from user where name="张三";
耗时20S
```

**这个时候我们对name增加一个索引**

```sql
create index idx_user_name on user(name);
这个时候，数据库创建这个索引的数据结构B+树，因为数量太大，很耗时间
```

**创建完成之后，执行select * from user where name="张三"**

**耗时0.001S**