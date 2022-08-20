### SpringAMQP-消息转换器



**消费者：**

```java
//创建object.queue队列
@Bean
public Queue objectQueue(){
    return new Queue("object.queue");
}
```



**提供者：**

```java
@Test
public void testObjectSend() throws InterruptedException {
    Map<String,Object> map=new HashMap<>();
    map.put("name","周志雄");
    map.put("age",20);
    rabbitTemplate.convertAndSend("object.queue",map);
}
```



**浏览器查看：**

```sh
content_type:	application/x-java-serialized-object

内容：rO0ABXNyABFqYXZhLnV0aWwuSGFzaE1hcAUH2sHDFmDRAwACRgAKbG9hZEZhY3RvckkACXRocmVzaG9sZHhwP0AAAAAAAAx3CAAAABAAAAACdAAEbmFtZXQA
CeWRqOW/l+mbhHQAA2FnZXNyABFqYXZhLmxhbmcuSW50ZWdlchLioKT3gYc4AgABSQAFdmFsdWV4cgAQamF2YS5sYW5nLk51bWJlcoaslR0LlOCLAgAAeHAA
AAAUeA==
```

**SpringAMQP采用了JDK的序列化，缺点：太长，比较慢，有安全性，非常不推荐使用**



**rabbitmq只支持字节，但是springamqp可以让我们发object，说明springamqp会自动序列化**

**默认：application/x-java-serialized-object**



#### **消息转换器**

**Spring对消息对象的处理由org.springframework.amqp.support.converter.MessageConverter来处理**

**而默认实现是SimpleMessageConverter，基于JDK的ObjectOutputStream完成序列化。**

**如果要修改只需要定义一个MessageConverter 类型的Bean即可。推荐用JSON方式序列化，步骤如下：**



**在发送方添加：**

```xml
<dependency>
	<groupId>com.fasterxml.jackson.core</groupId>
	<artifactId>jackson-databind</artifactId>
</dependency>
```

**我们在publisher服务声明MessageConverter，覆盖默认的消息转换器**

```java
@Bean
public MessageConverter jsonMessageConverter(){
	return new Jackson2JsonMessageConverter();
}
```



**经过测试，RabbitMQ里的消息是json类型的了**





#### 怎么接收消息

**我们在consumer服务引入Jackson依赖：**

```xml
<dependency>
	<groupId>com.fasterxml.jackson.core</groupId>
	<artifactId>jackson-databind</artifactId>
</dependency>
```

**我们在consumer服务定义MessageConverter：**

```java
@Bean
public MessageConverter jsonMessageConverter(){
	return new Jackson2JsonMessageConverter();
}
```

**定义消费者：拿到消息**

```java
@RabbitListener(queues = "object.queue")
public void getObjectQueue(Map<String,Object> map){
     System.out.println("消息:"+map);
}
```

**测试，拿到了map类型的消息**





**SpringAMQP中消息的序列化和反序列化是怎么实现的？**

**•利用MessageConverter实现的，默认是JDK的序列化**

**•注意发送方与接收方必须使用相同的MessageConverter**