### Docker容器命令

![docker容器命令](E:\笔记整理\微服务技术\图解\docker容器命令.png)



**暂停：进程挂起，内存信息等，保留，进程恢复了，继续运行**

**停止：直接杀死进程**



```sh
docker run     把镜像创建为创建容器
docker exec    进入容器执行命令
docker logs    查看容器运行日志
docker ps      查看所以运行的容器和状态
docker pause   暂停容器
docker unpause 恢复运行
docker stop    停止容器
docker start   开始运行容器
docker rm      删除容器
docker ps -a   查看所有容器
```



![运行容器命令解读](E:\笔记整理\微服务技术\图解\运行容器命令解读.png)



**左边的端口就是宿主机端口，右边是docker容器端口**



```sh
docker run --name mynginx -p 80:80 -d nginx:latest
```

**返回了一个很长的字符串，这个是容器的唯一id**

```sh
docker ps
```



**使用docker ps来查看运行的容器**

**浏览器访问nginx**

**查看日志  docker logs mynginx**

**查看日志根据容器的名称来查看**

**就能看到访问的日志**



**但是每次都需要手动查看日志**

**添加-f  就有持续的日志输出**

```sh
docker logs -f mynginx
```

**停止容器：docker stop**





#### 案例二：进入nginx的容器，修改html文件，添加“ZZX欢迎你"的内容

```
进入容器  docker exec
```

```sh
命令解读：
docker exec -it mynginx bash

-it:给当前进入的容器创建一个标准输入、输出终端，允许我们与容器交互
mynginx:要进入的容器的名称
bash：进入容器后执行的命令，bash是一个linux终端交互命令
```

**这个时候，控制台发送变化，变为root+容器的id**

**表明已经进入了容器的内部**



**但是并不知道nginx放在了哪里，只有打包这个镜像的作者知道，就去它的官网dockerHub找nginx的镜像说明**

```sh
How to use this image
Hosting some simple static content
$ docker run --name some-nginx -v /some/content:/usr/share/nginx/html:ro -d nginx
Alternatively, a simple Dockerfile can be used to generate a new image that includes the necessary content (which is a much cleaner solution than the bind mount above):

FROM nginx
COPY static-html-directory /usr/share/nginx/html
Place this file in the same directory as your directory of content ("static-html-directory"), run docker build -t some-content-nginx ., then start your container:

$ docker run --name some-nginx -d some-content-nginx
```

**COPY static-html-directory /usr/share/nginx/html   根据这一行，我们就知道了静态文件在这里**



**编辑的时候发现问题，找不到vim**

**原因：虽然这个镜像容器有自己的文件系统，但是nginx并不需要这些，所以很多没用的东西都没有加上去，需要使用的话，需要自己添加**



**把vim安装即可使用**

```sh
apt-get update
apt-get install vim
```



**退出容器：exit**

**停掉容器：docker stop mynginx**

**删除容器的时候，不能删除运行时候的容器**

**docker rm -f 容器名**

**强制删除**





**总结：**

**查看容器状态：**

**•docker ps**

**•添加-a参数查看所有状态的容器**

**删除容器：**

**•docker rm**

**•不能删除运行中的容器，除非添加 -f 参数**

**进入容器：**

**•命令是docker exec -it [容器名] [要执行的命令]**

**•exec命令可以进入容器修改文件，但是在容器内修改文件是不推荐的**