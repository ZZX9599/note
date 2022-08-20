### **DirectExchange**

### ![Direct Exchange](E:\笔记整理\微服务技术\图解\Direct Exchange.png)

**相当于有暗号，队列一需要red and blue    队列二需要yellow and red**

**蓝色给上面，黄色给下面，红色都给**





#### 案例：利用SpringAMQP演示DirectExchange的使用

**实现思路如下：**

**1.利用@RabbitListener声明Exchange、Queue、RoutingKey【路由key】**

**2.在consumer服务中，编写两个消费者方法，分别监听direct.queue1和direct.queue2**

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
    
    @RabbitListener(bindings=@QueueBinding(
        	//队列
            value = @Queue("direct.queue1"),
        	//交换机
            exchange = @Exchange(name = "jxl",type = ExchangeTypes.DIRECT),
        	//指定队列的routingkey
            key = {"red","blue"}
    ))
    public void listenerDirectQueue1(String msg){
        System.out.println("消费者一拿到的direct.queue1的消息："+msg);
    }

    @RabbitListener(bindings=@QueueBinding(
            value = @Queue("direct.queue2"),
            exchange = @Exchange(name = "jxl",type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void listenerDirectQueue2(String msg){
        System.out.println("消费者二拿到的direct.queue1的消息："+msg);
    }
}
```





```java
@Test
public void testDirectSendExchange() throws InterruptedException {
    //交换机名称
    String exchangeName="jxl";
    //消息
    String message1="Hello,blue";
    String message2="Hello,yellow";
    String message3="Hello,red";
    rabbitTemplate.convertAndSend(exchangeName,"blue",message1);
    rabbitTemplate.convertAndSend(exchangeName,"yellow",message2);
    rabbitTemplate.convertAndSend(exchangeName,"red",message3);
}
```



**描述下Direct交换机与Fanout交换机的差异？**

**•Fanout交换机将消息路由给每一个与之绑定的队列**

**•Direct交换机根据RoutingKey判断路由给哪个队列**

**•如果多个队列具有相同的RoutingKey，则与Fanout功能类似**

**基于@RabbitListener注解声明队列和交换机有哪些常见注解？**

**•@Queue**

**•@Exchange**