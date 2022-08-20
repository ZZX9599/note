### AOF

**AOF全称为Append Only File（追加文件）**

**Redis处理的每一个写命令都会记录在AOF文件，可以看做是命令日志文件。**



![AOF](E:\笔记整理\微服务技术\redis\图片\AOF.png)



**可以看作命令的日志文件**



![AOF配置](E:\笔记整理\微服务技术\redis\图片\AOF配置.png)



**注意：这个AOF的写操作是由redis的主进程来完成的，先执行redis的语句，再写入AOF文件**



**set name zhangsan  set name lisi  set name zzx    最后的数据是zzx，所以没有必要记录前面的两次数据**



![解决多次写的问题](E:\笔记整理\微服务技术\redis\图片\解决多次写的问题.png)



**关闭RDB：设置 save ""**



### 对比

![对比](E:\笔记整理\微服务技术\redis\图片\对比.png)



**同时启用了RDB和AOF的时候，起作用的是AOF**

