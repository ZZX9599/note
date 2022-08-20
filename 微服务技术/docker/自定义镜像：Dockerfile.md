### 自定义镜像：Dockerfile



![dockerfile](E:\笔记整理\微服务技术\图解\dockerfile.png)



**注意：expose暴露的是容器的端口，也就是-p后面的参数**



#### 案例：基于Ubuntu镜像构建一个新镜像，运行一个java项目

```yaml
步骤1：新建一个空文件夹docker-demo
步骤2：拷贝课前资料中的docker-demo.jar文件到docker-demo这个目录
步骤3：拷贝课前资料中的jdk8.tar.gz文件到docker-demo这个目录
步骤4：拷贝课前资料提供的Dockerfile到docker-demo这个目录
步骤5：进入docker-demo
步骤6：运行命令：
```



```sh
# 指定基础镜像
FROM ubuntu:16.04

# 配置环境变量，JDK的安装目录
ENV JAVA_DIR=/usr/local

# 拷贝jdk和java项目的包
COPY ./jdk8.tar.gz $JAVA_DIR/
COPY ./docker-demo.jar /tmp/app.jar

# 安装JDK
RUN cd $JAVA_DIR \
 && tar -xf ./jdk8.tar.gz \
 && mv ./jdk1.8.0_144 ./java8

# 配置环境变量
ENV JAVA_HOME=$JAVA_DIR/java8
ENV PATH=$PATH:$JAVA_HOME/bin

# 暴露端口
EXPOSE 8090

# 入口，java项目的启动命令
ENTRYPOINT java -jar /tmp/app.jar
```



**构建新镜像：docker build -t javaweb:1.0 .**

**注意：-t实际上是tag  后面指定镜像名称和版本  **

**.代表的是dockerfile的位置，我这里放在了一个目录下，所以用.**





**查看镜像  docker images**



**创建容器并且运行  docker run --name myweb -p 8099:8090 -d javaweb:1.0**



**浏览器打开：http://192.168.220.138:8099/hello/count**



**测试成功！**





#### 不足：

```sh
# 指定基础镜像
FROM ubuntu:16.04

# 配置环境变量，JDK的安装目录
ENV JAVA_DIR=/usr/local

# 拷贝jdk包
COPY ./jdk8.tar.gz $JAVA_DIR/

# 安装JDK
RUN cd $JAVA_DIR \
 && tar -xf ./jdk8.tar.gz \
 && mv ./jdk1.8.0_144 ./java8

# 配置环境变量
ENV JAVA_HOME=$JAVA_DIR/java8
ENV PATH=$PATH:$JAVA_HOME/bin
```

**实际上上面的都是构建java的环境，如果有很多的微服务，每个都要在dockerfile里写这些**

**我们可以把构建java环境的镜像全部做完，以后构建的时候，直接引用这个基础镜像即可**



**有人已经把java8的镜像做好了**

**java:8-alpine**

```sh
# 指定基础镜像
FROM java:8-alpine

#指定java项目
COPY ./docker-demo.jar /tmp/app.jar

# 暴露端口
EXPOSE 8090

# 入口，java项目的启动命令
ENTRYPOINT java -jar /tmp/app.jar
```



**1.Dockerfile的本质是一个文件，通过指令描述镜像的构建过程**

**2.Dockerfile的第一行必须是FROM，从一个基础镜像来构建**

**3.基础镜像可以是基本操作系统，如Ubuntu。也可以是其他人制作好的镜像，例如：java:8-alpine**