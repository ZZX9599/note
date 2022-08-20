### **TopicExchange**



![TopicExchange](E:\笔记整理\微服务技术\图解\TopicExchange.png)



**话题交换机，实际上跟Direct-Exchange没有大的变化，主要就是使用通配符来简化一类消息的获取**



#### 案例：利用SpringAMQP演示TopicExchange的使用

**实现思路如下：**

**1.并利用@RabbitListener声明Exchange、Queue、RoutingKey**

**2.在consumer服务中，编写两个消费者方法，分别监听topic.queue1和topic.queue2**

**3.在publisher中编写测试方法，向交换机发送消息**



```java
package cn.itcast.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @author ZZX
 * @date 2022/5/28 16:43
 */

@Component
public class SpringRabbitListener {
    
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topic.queue1"),
            exchange = @Exchange(value = "zzx.topic",type = ExchangeTypes.TOPIC),
            key = {"beijing.#","chain.#"}
    ))
    public void listenerTopicQueue1(String msg){
        System.out.println("消费者一拿到的topic.queue1的消息："+msg);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("topic.queue2"),
            exchange = @Exchange(value = "zzx.topic",type = ExchangeTypes.TOPIC),
            key = {"sichuan.#","chain.#"}
    ))
    public void listenerTopicQueue2(String msg){
        System.out.println("消费者二拿到的topic.queue2的消息："+msg);
    }
}
```



```java
@Test
public void testTopicSendExchange() throws InterruptedException {
    //交换机名称
    String exchangeName="zzx.topic";
    //消息
    String message1="北京新闻----NB";
    String message2="四川新闻----NB";
    String message3="中国新闻----NB";
    rabbitTemplate.convertAndSend(exchangeName,"beijing",message1);
    rabbitTemplate.convertAndSend(exchangeName,"sichuan",message2);
    rabbitTemplate.convertAndSend(exchangeName,"chain",message3);
}
```



**描述下Direct交换机与Topic交换机的差异？**

**Topic交换机接收的消息RoutingKey必须是多个单词，以 . 分割**

**Topic交换机与队列绑定时的bindingKey可以指定通配符**

**#：代表0个或多个词**

***：代表1个词**

**注意：发送消息的时候多个单词，中间靠.分割**