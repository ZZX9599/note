### Redis部分配置说明

**编辑 Master 的配置文件 redis6380.conf : **

**在空文件加入如下内容 **

**include /usr/local/redis-4.0.13/redis.conf **

**daemonize yes port 6380 **

**pidfile /var/run/redis_6380**

**pid logfile 6380.log **

**dbfilename dump6380.rdb**

**slaveof 127.0.0.1 6380**

**配置项说明： **

**include ：包含原来的配置文件内容。/usr/local/redis-4.0.13/redis.conf 按照自己的目录设置。 **

**daemonize：yes 后台启动应用，相当于 ./redis-server & 的作用。 **

**port : 自定义的端口号 **

**pidfile : 自定义的文件，表示当前程序的 **

**pid ,进程 id。 **

**logfile：日志文件名 **

**dbfilename：持久化的 rdb 文件名**

**slaveof：配置该服务器从属于哪个服务器**

