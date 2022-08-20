### Redis的基本知识

**测试redis性能：redis-benchmark**

**查看redis服务是否正常运行：ping   --->  返回PONG代表redis服务正常运行**

**查看redis服务器的统计信息：info [参数]**

**查看服务信息  info server**

**查看全部信息  info**

**redis默认使用16个库  默认使用的是0号库，下标是0-15   选择库的语法  select  下标** 

**数据库个数的修改，在redis.conf文件中databases 16，理论上可以配置无限多个**

**Redis的库和关系型数据库中的数据库实例类似，但又有一些不同，比如redis中各个库不能自定义命名，只能用序号表示**

**redis中各个库不是完全独立的，使用时最好一个应用使用一个redis实例，不建议一个redis实例中保存多个应用的数据。**

**Redis实例本身所占存储空间其实是非常小的，因此不会造成存储空间的浪费。**



### 常用的命令

**1） 查看当前数据库中key的数目：dbsize**

**2） 查看当前数据库中有哪些key：keys ***

**3） 清空当前库：flushdb**

**4） 清空所有数据库：flushall**

**5） redis 自带的客户端退出当前 redis 连接: exit 或 quit**