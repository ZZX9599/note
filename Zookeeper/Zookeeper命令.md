### Zookeeper命令



##### 1：服务端常用命令

```xml
启动：zkServer.sh start
查看状态：zkServer.sh status
停止：zkServer.sh stop
重启：zkServer.sh restart
```



##### 2：客户端常用命令

```xml
连接Zookeeper客户端：zkCli.sh -server ip : port
连接本机：直接zkCli.sh
连接后退出：quit
查看目录结构：ls /目录/目录/目录/...   ls /
创建节点：create /目录/目录           ls /app1
创建节点和数据：create /目录 数据      create /app2 你好
设置数据：set /目录 数据              set /app1 你也好
获取数据：get /目录                  get /app1
删除数据：delete /目录               delete /app1
目录已经存在的话，再创建目录会提示存在
删除的话，目录下面还有文件，不能删除
使用deleteAll /目录 来删除
帮助命令：help
```



##### 创建节点，附带参数，创建不同类型的节点【默认是持久策略】

```xml
创建临时节点：create -e /app      【客户端退出后进来就没有了】
创建顺序节点：create -s /app      【实际上Created /app40000000005】
再创一次节点：create -s /app      【实际上Created /app40000000006】
创建临时顺序：create -es /app
```



##### 实际上，dubbo节点里面的provider和consumer里面存放的是ip地址

