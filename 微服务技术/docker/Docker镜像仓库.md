### Docker镜像仓库

**镜像仓库（ Docker Registry ）有公共的和私有的两种形式：**

**公共仓库：例如Docker官方的 [Docker Hub](https://hub.docker.com/)，国内也有一些云服务商提供类似于 Docker Hub 的公开服务，比如 [网易云镜像服务](https://c.163.com/hub)、[DaoCloud](https://hub.daocloud.io/)[ ](https://hub.daocloud.io/)[镜像服务](https://hub.daocloud.io/)、[阿里云镜像服务](https://cr.console.aliyun.com/)等。**



**除了使用公开仓库外，用户还可以在本地搭建私有 Docker Registry。企业自己的镜像最好是采用私有Docker Registry来实现。**



**搭建镜像仓库可以基于Docker官方提供的DockerRegistry来实现。**

**官网地址：https://hub.docker.com/_/registry**



## 1.简化版镜像仓库

Docker官方的Docker Registry是一个基础版本的Docker镜像仓库，具备仓库管理的完整功能，但是没有图形化界面。

搭建方式比较简单，命令如下：

```sh
docker run -d \
    --restart=always \
    --name registry	\
    -p 5000:5000 \
    -v registry-data:/var/lib/registry \
    registry
```



命令中挂载了一个数据卷registry-data到容器内的/var/lib/registry 目录，这是私有镜像库存放数据的目录。

访问http://YourIp:5000/v2/_catalog 可以查看当前私有镜像服务中包含的镜像



## 2.带有图形化界面版本

使用DockerCompose部署带有图象界面的DockerRegistry，命令如下：

```yaml
version: '3.0'
services:
  registry:
    image: registry
    volumes:
      - ./registry-data:/var/lib/registry
  ui:
    image: joxit/docker-registry-ui:static
    ports:
      - 8080:80
    environment:
      - REGISTRY_TITLE=周志雄私有库
      - REGISTRY_URL=http://registry:5000
    depends_on:
      - registry
```



## 3.配置Docker信任地址

我们的私服采用的是http协议，默认不被Docker信任，所以需要做一个配置：

```sh
# 打开要修改的文件
vi /etc/docker/daemon.json
# 添加内容：
"insecure-registries":["http://192.168.220.138:8080"]
# 重加载
systemctl daemon-reload
# 重启docker
systemctl restart docker
```



**访问  192.168.220.138:8080就可以访问私有docker仓库了**





#### 私有库操作镜像



![私有库操作镜像](E:\笔记整理\微服务技术\图解\私有库操作镜像.png)



**原本有一个nginx镜像**

**执行：docker tag nginx:latest 192.168.220.138:8080/nginx:1.0**

**实际上是把原本的镜像，重命名为了  192.168.220.138:8080/nginx  版本1.0**

**id都是一样的，本质上一样的**



**查看浏览器**

![docker私有镜像界面](E:\笔记整理\微服务技术\图解\docker私有镜像界面.png)

**这个时候删除本地镜像   docker rmi 192.168.220.138:8080/nginx:1.0**

**docker rmi nginx:latest**

**这个时候，本地没有任何nginx镜像**



**从私有仓库拉取：docker  pull   192.168.220.138:8080/nginx:1.0**