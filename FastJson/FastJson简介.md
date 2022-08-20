### FastJson简介



#### 1. 什么是fastjson?

fastjson是阿里巴巴的开源JSON解析库，它可以解析JSON格式的字符串，支持将Java Bean序列化为JSON字符串，也可以从JSON字符串反序列化到JavaBean。

#### Fastjson使用场景

Fastjson已经被广泛使用在各种场景，包括cache存储、RPC通讯、MQ通讯、网络协议通讯、Android客户端、Ajax服务器处理程序等等。

#### 2.fastjson的优点

#### 2.1 速度快

fastjson相对其他JSON库的特点是快，从2011年fastjson发布1.1.x版本之后，其性能从未被其他Java实现的JSON库超越。

#### 2.2 使用广泛

fastjson在阿里巴巴大规模使用，在数万台服务器上部署，fastjson在业界被广泛接受。在2012年被开源中国评选为最受欢迎的国产开源软件之一。

#### 2.3 测试完备

fastjson有非常多的testcase，在1.2.11版本中，testcase超过3321个。每次发布都会进行回归测试，保证质量稳定。

#### 2.4 使用简单

fastjson的API十分简洁

```java
String text = JSON.toJSONString(obj);
VO vo = JSON.parseObject("json字符串", VO.class);
```

