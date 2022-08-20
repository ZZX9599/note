### Mybatis事务

**事务
多个操作同时完成,或同时失败称为事务处理.
事务有四个特性:一致性,持久性,原子性,隔离性.**



**下订单的业务:
1)订单表中完成增加一条记录的操作
2)订单明细表中完成N条记录的增加
3)商品数据更新(减少)
4)购物车中已支付商品删除
5)用户积分更新(增加)**



**在MyBatis框架中设置事务**

**Mybatis 框架是对 JDBC 的封装，所以 Mybatis 框架的事务控制方式有两种，一种是容器进行事务管理的，一种是程序员手工决定事务的提交与回滚**

<transactionManager type="JDBC"></transactionManager>  **=>程序员自己控制处理的提交和回滚**



**可设置为自动提交
sqlSession = factory.openSession();  ===>默认是手工提交事务,设置为false也是手工提交事务,如果设置为true,则为自动提交.
sqlSession = factory.openSession(true);  ===>设置为自动提交,在增删改后不需要commit();**