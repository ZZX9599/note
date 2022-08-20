### Docker镜像命令



**•镜像名称一般分两部分组成：[repository]:[tag]。**

**•在没有指定tag时，默认是latest，代表最新版本的镜像**



**例如  mysql:5.7**



#### **基本命令的图示**



![docker命令图示](E:\笔记整理\微服务技术\图解\docker命令图示.png)

**查看帮助**

**docker --help**

**docker images --help**





**案例：**

![docker拉取镜像案例](E:\笔记整理\微服务技术\图解\docker拉取镜像案例.png)

**未指定，拉取最新版本**



**使用docker pull nginx:latest就能拉取到了**





**案例二：导出镜像为压缩文件**



**docker save -o nginx.tar nginx:latest **



**案例三：删除镜像**



**docker rmi nginx:latest**



**案例四：查看镜像**



**docker images**



**案例五：把本地的tar文件装载为镜像**



**docker load -i nginx:latest**



**总结：**

**镜像操作有哪些？**

**•docker images**

**•docker rmi**

**•docker pull**

**•docker push**

**•docker save** 

**•docker load**