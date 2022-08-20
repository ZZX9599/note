### ES集群新增文档分析



**当新增文档时，应该保存到不同分片，保证数据均衡**

**那么coordinating node如何确定数据该存储到哪个分片呢？**

**elasticsearch会通过hash算法来计算文档应该存储到哪个分片：**

**shard = hash(_routing) % number_of_shards**





**说明：**

**•_routing默认是文档的id**

**•算法与分片数量有关，因此索引库一旦创建，分片数量不能修改！**



![新增流程](E:\笔记整理\微服务技术\图解\DSL对应关系\新增流程.png)





![两个阶段](E:\笔记整理\微服务技术\图解\DSL对应关系\两个阶段.png)



**分布式新增如何确定分片？**

**•coordinating node根据id做hash运算，得到结果对shard数量取余，余数就是对应的分片**



**分布式查询的两个阶段**

**•分散阶段： coordinating node将查询请求分发给不同分片**

**•收集阶段：将查询结果汇总到coordinating node ，整理并返回给用户**