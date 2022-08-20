### 使用SpringAMQP实现HelloWorld中的基础消息队列功能



#### 发送消息：

**流程如下：**

**1.在父工程中引入spring-amqp的依赖**

**2.在publisher服务中利用RabbitTemplate发送消息到simple.queue这个队列**

**3.在consumer服务中编写消费逻辑，绑定并监听simple.queue这个队列**



```xml
<!--AMQP依赖，包含RabbitMQ-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```



**配置文件：**

```yml
spring:
  rabbitmq:
    host: 192.168.220.145  #ip地址
    port: 5672  #端口
    username: ZZX
    password: JXLZZX79
    virtual-host: /  #虚拟主机
```



**代码：**

```java
@SpringBootTest
public class TestSpringAMQP {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSend(){
        String queueName="simple.queue";
        String message="hello spring-AMQP";
        rabbitTemplate.convertAndSend(queueName,message);
    }
}
```



**什么是AMQP？**

**•应用间消息通信的一种协议，与语言和平台无关。**

**SpringAMQP如何发送消息？**

**•引入amqp的starter依赖**

**•配置RabbitMQ地址**

**•利用RabbitTemplate的convertAndSend方法**





#### 接收消息：

**流程如下：**

**1.在父工程中引入spring-amqp的依赖**

**2.在consumer服务中编写application.yml，添加mq连接信息：**

**3.在consumer服务中新建一个类，编写消费逻辑：**





```xml
<!--AMQP依赖，包含RabbitMQ-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```



```yml
spring:
  rabbitmq:
    host: 192.168.220.145  #ip地址
    port: 5672  #端口
    username: JXL
    password: ZZXJXL79
    virtual-host: /jxl  #虚拟主机
```



```java
@Component
public class SpringRabbitListener {
    
    @RabbitListener(queues = "simple.queue")
    public void listenerQueue(String str){
        System.out.println("接收到消息："+str);
    }
}
```



**通过设置prefetch来控制消费者预取的消息数量**
