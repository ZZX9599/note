### 实践-AT模式



![At](E:\笔记整理\微服务技术\seata\图片\At.png)

**AT和XA的最大区别就是，AT直接执行sql并且提交数据，只不过RM会记录一次快照，如果后面回滚了**

**就恢复到快照之前的数据**



![AT-juli](E:\笔记整理\微服务技术\seata\图片\AT-juli.png)



**简述AT模式与XA模式最大的区别是什么？**

**•XA模式一阶段不提交事务，锁定资源；AT模式一阶段直接提交，不锁定资源。**

**•XA模式依赖数据库机制实现回滚；AT模式利用数据快照实现数据回滚。**

**•XA模式强一致；AT模式最终一致**

**XA模式中途会出现短暂的软状态**





#### 问题1：脏写问题

![zangxie](E:\笔记整理\微服务技术\seata\图片\zangxie.png)

**前提：事务一二都是被seata管理的事务，属于同一个TM管理的事务**

**因为阶段一执行完就释放锁了，可能数据被另外的事务在事务一的二阶段执行前改了**

**后面回滚的话，事务二的更新就无效了，因为记录的快照还是100，最后数据就是100，而不是90了**



#### 解决：使用全局锁

![全局锁解决](E:\笔记整理\微服务技术\seata\图片\全局锁解决.png)

**添加一张新的表，记录数据和第一次执行的事务id，在事务没有结束事务二之前，就不准其他的seata事务执行**

**必须等待全局锁释放【也就是事务一的阶段二执行】**



#### 问题：如果事务二不是seata的事务【不属于同一个全局事务TM】

![非](E:\笔记整理\微服务技术\seata\图片\非.png)



**记录两次快照，对比第二次的快照和即将回滚的时候的数据，来判断期间是否有事务修改了数据**



**AT模式的优点：**

**•一阶段完成直接提交事务，释放数据库资源，性能比较好**

**•利用全局锁实现读写隔离**

**•没有代码侵入，框架自动完成回滚和提交**

**AT模式的缺点：**

**•两阶段之间属于软状态，属于最终一致**

**•框架的快照功能会影响性能，但比XA模式要好很多**



### 实现AT模式

**AT模式中的快照生成、回滚等动作都是由框架自动完成，没有任何代码侵入，因此实现非常简单**

**快照生成是由RM完成的**

**全局锁的设置是由TC完成的**



**快照信息表：加入到微服务的数据库**

```sql
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `branch_id` bigint(20) NOT NULL COMMENT 'branch transaction id',
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'global transaction id',
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'undo_log context,such as serialization',
  `rollback_info` longblob NOT NULL COMMENT 'rollback info',
  `log_status` int(11) NOT NULL COMMENT '0:normal status,1:defense status',
  `log_created` datetime(6) NOT NULL COMMENT 'create datetime',
  `log_modified` datetime(6) NOT NULL COMMENT 'modify datetime',
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'AT transaction mode undo table' ROW_FORMAT = Compact;
```



**全局锁信息：加入到seata的数据库，因为是属于TC管理，我们搭建的seata一般都是TC**

```sql
DROP TABLE IF EXISTS `lock_table`;
CREATE TABLE `lock_table`  (
  `row_key` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `xid` varchar(96) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `transaction_id` bigint(20) NULL DEFAULT NULL,
  `branch_id` bigint(20) NOT NULL,
  `resource_id` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `table_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pk` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gmt_create` datetime NULL DEFAULT NULL,
  `gmt_modified` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`row_key`) USING BTREE,
  INDEX `idx_branch_id`(`branch_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;
```



**2：修改application.yml文件，将事务模式修改为AT模式即可：**

```yml
seata:
	data-source-proxy-mode: AT
```

