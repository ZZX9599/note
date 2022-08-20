### Redis的连接

**redis-cli是 Redis 自带的基于命令行的 Redis 客户端**



**默认情况下：redis-cli默认连接的是6379端口的redis服务**



**指定ip和端口号的连接**

**redis-cli -h 127.0.0.1 -p 6380**

**连接本机的6380端口的redis服务**



### Redis远程连接

**注意：redis远程连接是需要在配置文件解除安全模式的**

**Redis服务器有安全保护措施，默认只有本机（安装Redis的那台机器）能够访问。**

**配置信息存放在Redis安装目录下的 redis.conf文件，修改此文件的两个设置。 **

**远程连接redis需要修改redis主目录下的redis.conf配置文件：**

**1：bind ip 绑定ip此行注释 **

**2：protected-mode yes 保护模式改为 no**



**还需要关闭redis的防火墙  systemstl stop firewalld，使用阿里云服务器还需要开放端口**



