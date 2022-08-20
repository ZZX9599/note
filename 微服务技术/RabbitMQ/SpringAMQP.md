### SpringAMQP



**AMQP：Advanced Message Queuing Protocol**

**是用于在应用程序之间传递业务消息的开放标准。该协议与语言和平台无关，更符合微服务中独立性的要求。**



**SpringAMQP实际上就是实现这个规范的一种技术，定义了一堆API**

**简化消息的发送和接收，封装了原生的API操作，类似RedisTemplate**



**SpringAmqp的官方地址：https://spring.io/projects/spring-amqp**



#### 特征

- **用于异步处理入站消息的侦听器容器**
- **用于发送和接收消息的 RabbitTemplate**
- **RabbitAdmin 用于自动声明队列、交换和绑定**