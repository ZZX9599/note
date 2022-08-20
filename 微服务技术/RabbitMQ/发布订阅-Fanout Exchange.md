### 发布订阅-Fanout Exchange

**Fanout Exchange 会将接收到的消息广播到每一个跟其绑定的queue**

**也就是说，只要绑定了这个交换机的队列，都会收到对应的消息**



![Fanout Exchange](E:\笔记整理\微服务技术\图解\Fanout Exchange.png)



![交换机继承图](E:\笔记整理\微服务技术\图解\交换机继承图.png)



#### **案例：利用SpringAMQP演示FanoutExchange的使用**



**实现思路如下：**

**1.在consumer服务中，利用代码声明队列、交换机，并将两者绑定**

**2.在consumer服务中，编写两个消费者方法，分别监听fanout.queue1和fanout.queue2**

**3.在publisher中编写测试方法，向交换机发送消息**



**消费者：**

```java
package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZZX
 * @date 2022/5/29 10:31
 */
@Configuration
public class FanoutConfig {

    //交换机：zzx
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("zzx");
    }
    //队列一：queue1
    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanout.queue1");
    }
    //队列二：queue2
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.queue2");
    }
    
    
    //绑定交换机01和队列
    @Bean
    public Binding bindingFanoutQueue1(Queue fanoutQueue1,FanoutExchange fanoutExchange){
        //把队列绑定到交换机
        return BindingBuilder
                .bind(fanoutQueue1)
                .to(fanoutExchange);
    }
    //绑定交换机02和队列
    @Bean
    public Binding bindingFanoutQueue2(Queue fanoutQueue2,FanoutExchange fanoutExchange){
        //把队列绑定到交换机
        return BindingBuilder
                .bind(fanoutQueue2)
                .to(fanoutExchange);
    }
}
```



**消息发送：**

```java
@Test
public void testFanoutSendExchange() throws InterruptedException {
    //交换机名称
    String exchangeName="zzx";
    //消息
    String message="Hello,everyone";
    rabbitTemplate.convertAndSend(exchangeName,"",message);
}
```



**交换机的作用是什么？**

**•接收publisher发送的消息**

**•将消息按照规则路由到与之绑定的队列**

**•不能缓存消息，路由失败，消息丢失**

**•FanoutExchange的会将消息路由到每个绑定的队列**

**声明队列、交换机、绑定关系的Bean是什么？**

**•Queue**

**•FanoutExchange**

**•Binding**