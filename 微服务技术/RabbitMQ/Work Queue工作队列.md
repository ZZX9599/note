### Work Queue工作队列



**Work queue，工作队列，可以提高消息处理速度，避免队列消息堆积**

**实际上还是普通的队列，只是挂了很多的消费者**



**模拟WorkQueue，实现一个队列绑定多个消费者**



**基本思路如下：**

**1.在publisher服务中定义测试方法，每秒产生50条消息，发送到simple.queue**

**2.在consumer服务中定义两个消息监听者，都监听simple.queue队列**

**3.消费者1每秒处理50条消息，消费者2每秒处理10条消息**



```java
@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "my")
    public void listenerQueue01(String str) throws InterruptedException {
        System.out.println("消费者一接收到消息："+str);
        Thread.sleep(20);
    }

    @RabbitListener(queues = "my")
    public void listenerQueue02(String str) throws InterruptedException {
        System.err.println("消费者二接收到消息："+str);
        Thread.sleep(200);
    }
}
```



```java
    @Test
    public void testSend02() throws InterruptedException {
        String queueName="my";
        String message="消息";
        for(int i=1;i<=50;i++){
            rabbitTemplate.convertAndSend(queueName,message+i);
            Thread.sleep(20);
        }
    }
```



**经过测试：并不是消费者一消费得多，按理说应该是1S处理完成，实际上花费了六秒多，观察控制台我们发现，消费者一处理所有的奇数消息，消费者二处理所有的偶数消息，消费者一只暂停了20ms，很快就处理完成了，但是消费者二暂停了200ms，处理所有的偶数处理了很久**





**原因：消费预取限制**

**就是在RabbitMQ默认设置里，当消息到达队列，先将消息投递，先将消息全部分配，不管处理速度，就算其中的消费者很快处理完成了，也不会帮别的消费者处理，这就是消息预取**



**修改application.yml文件，设置preFetch这个值，可以控制预取消息的上限：**

**这个值默认是无效，就是在分配消息的时候，先把所有的分配完**

```yml
spring:
  rabbitmq:
    host: 192.168.220.146  #ip地址
    port: 5672  #端口
    username: JXL
    password: ZZXJXL79
    virtual-host: /jxl  #虚拟主机
    listener:
      simple:
        prefetch: 1  #取完一条消息之后，再去消息队列拿消息
```



**总结：**

**Work模型的使用：**

**•多个消费者绑定到一个队列，同一条消息只会被一个消费者处理**

**•通过设置prefetch来控制消费者预取的消息数量**