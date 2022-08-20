### Redis高可用 Sentinel 哨兵

**哨兵模式其实就是从机上位的自动版**

**Redis提供了哨兵的命令，哨兵命令是一个独立的进程，哨兵通过发送命令，来监控主从服务器的运行状态，如果检测到master发生故障，会根据投票数自动将某一个slave转换master，然后通过消息订阅模式通知其它slave，让它们切换主机。然而，一个哨兵进程对Redis服务器进行监控，可能会出现问题，为此，我们可以使用多哨兵进行监控**



**Sentinel 系统有三个主要任务： **

**⚫ 监控：Sentinel 不断的检查主服务和从服务器是否按照预期正常工作。 **

**⚫ 提醒：被监控的 Redis 出现问题时，Sentinel 会通知管理员或其他应用程序。 **

**⚫ 自动故障转移：监控的主 Redis 不能正常工作，Sentinel 会开始进行故障迁移操作。将 一个从服务器升级新的主服务器。让其他从服务器挂到新的主服务器。同时向客户端提 供新的主服务器地址。**



**主要的配置文件：sentinel.conf**



**编辑里边的内容：sentinel monitor name 127.0.0.1 6379 1**



**表示：指定监控主机的ip地址，port端口，得到哨兵的投票数(当哨兵投票数大于或者等于此数时切换主从关系)。**

**sentinel monitor name 127.0.0.1 6379 1解析**

**sentinel monitor ：固定**

**name：可以修改**

**127.0.0.1：master的ip地址**

**6379：master的端口**

**1：投票数**



**Sentinel 默认端口号为26379**

**启动 Sentinel redis安装时make编译后就产生了redis-sentinel程序文件，可以在一个redis中运行多个 sentinel进程。**





**开启sentinel        **

**redis-sentinel     sentinel26380.conf**



**主机宕机之后，根据投票选出新的master，这个时候原来其他从机自动从属于新的主机**

**主机恢复的话，自动加入新的主从关系成为从机**



**命令集合：**

**1 查看主从复制关系命令：info replication
 2 设置主从关系命令：slaveof 主机ip 主机port
 3 开启哨兵模式命令：./redis-sentinel sentinel.conf
 4 主从复制原则：开始是全量复制，之后是增量复制
 5 哨兵模式三大任务：监控，提醒，自动故障迁移**

