### RabbitMQ安装

**RabbitMQ是基于Erlang语言开发的开源消息通信中间件，官网地址：https://www.rabbitmq.com/**



#### 安装RabbitMQ

**使用镜像：**

**方式一：镜像拉取  docker pull rabbitmq:3-management**

**方式二：本地导入，然后docker load -i RabbitMQ.tar加载为镜像**



**安装RabbitMQ**

执行下面的命令来运行MQ容器：

```sh
docker run \
 -e RABBITMQ_DEFAULT_USER=ZZX \
 -e RABBITMQ_DEFAULT_PASS=JXLZZX79 \
 --name mq \
 --hostname mq1 \
 -p 15672:15672 \
 -p 5672:5672 \
 -d \
 rabbitmq:3-management
```



**15672是RabbitMQ的UI管理平台**

**5672是消息通信的端口**



**浏览器http://192.168.220.138:15672即可进入RabbitMQ的管理平台**





**每个用户应该有自己的虚拟主机**