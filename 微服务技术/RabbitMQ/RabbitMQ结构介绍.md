### RabbitMQ结构介绍

**RabbitMQ结构和概念**

![RabbitMQ结构和概念](E:\笔记整理\微服务技术\图解\RabbitMQ结构和概念.png)



**Publisher：发送消息的**

**exchange：交换机**

**queue：消息队列**

**consumer：消费者**

**每个用户的虚拟主机，相互隔离，不可见，避免干扰**



**RabbitMQ中的几个概念：**

**•channel：操作MQ的工具**

**•exchange：路由消息到队列中**

**•queue：缓存消息**

**•virtual host：虚拟主机，是对queue、exchange等资源的逻辑分组**