### SpringBoot配置文件

配置文件名称： application【注意，不能改名字】

扩展名有： properties( k=v)       yml ( k: v)

使用application.properties        application.yml

现在更加推荐使用yml格式



例1：application.properties设置端口和上下文

```java
#设置端口号
server.port=8081
#设置访问应用上下文路径， contextpath
server.servlet.context-path=/myboot
```



例2： application.yml

```java
server:
  port: 8082
  servlet:
    context-path: /myboot2
```



**上下文路径：server.servlet.context-path**

**端口：server.port**



**当拥有两个配置文件 application.properties和application.yml  默认使用properties**



**获取配置文件的数据  ${key}**