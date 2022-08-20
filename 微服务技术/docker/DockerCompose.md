### DockerCompose



![dockercompose介绍](E:\笔记整理\微服务技术\图解\dockercompose介绍.png)



```sh
#方式一：根据镜像构建mysql容器并运行:

docker run \
--name mysql \
-e mysql_root_password=123 \
-p 3306:3306 \
-v mysql-conf:/emp/mysql/conf \
-v mysql-data:/emp/mysql/data \
-d \
mysql:5.7.25


#方式二：
#构建镜像
docker build -t mysql:5.7.25 .
#运行容器
docker run --name mysql 5.7.25 -p 3306:3306 -d mysql:5.7.25
```



**对比以上的命令：dockercompose就是docker的run的各种参数**



#### CentOS7安装DockerCompose

Linux下需要通过命令下载：

```sh
# 安装
curl -L https://github.com/docker/compose/releases/download/1.23.1/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose

# 修改权限
chmod +x /usr/local/bin/docker-compose

echo "199.232.68.133 raw.githubusercontent.com" >> /etc/hosts

# 补全命令
curl -L https://raw.githubusercontent.com/docker/compose/1.29.1/contrib/completion/bash/docker-compose > /etc/bash_completion.d/docker-compose
```





**DockerCompose有什么作用？**

**帮助我们快速部署分布式应用，无需一个个微服务去构建镜像和部署**