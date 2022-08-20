### FastDFS环境搭建



### 安装前的准备

#### 1：检查Linux上是否安装了 gcc、libevent、libevent-devel

**1： yum list installed | grep gcc**

**2： yum list installed | grep libevent**

**3： yum list installed | grep libevent-devel**

#### 2：如果没有安装，则需进行安装

**yum install gcc libevent libevent-devel -y**



### 正式安装

**1：安装 libfastcommon 库**

**libfastcommon 库是 FastDFS 文件系统运行需要的公共 C 语言函数库**

**注意：这个并不是用yum来安装，而是自己下载压缩包上传，解压**



**1）将下载好的libfastcommon文件上传到Linuxs（/home/soft）**                         

**2）解压下载下来的tar.gz压缩包到当前目录**

**3）tar -zxvf libfastcommon-1.0.36.tar.gz**

**4）切换到解压后的libfastcommon目录**

**5）cd libfastcommon-1.0.36**

**6）执行make脚本进行编译**

**7）执行make install安装**

**至此 libfastcommon库安装完毕**





**2：安装FastDFS**

**FastDFS没有Windows版本，不能在Windows下使用。FastDFS需要安装部署在Linux环境下**

**下载地址：https://github.com/happyfish100/fastdfs/archive/V5.11.tar.gz**



**1：将下载好的FastDFS文件上传到Linux（home/soft）**

**2：解压下载下来的tar.gz压缩包到当前目录**

**tar -zxvf fastdfs-5.11.tar.gz**

**3：切换到解压后FastDFS的目录**

**cd fastdfs-5.11**

**4：执行make脚本进行编译**

**./make.sh**

**5：执行make install进行安**



**至此FastDFS安装完成**

**所有编译出来的文件存放在/usr/bin目录下【生成了很多的命令，可以在任意目录执行】**

**所有配置文件存放在/etc/fdfs目录下**



**6：查看安装后的效果**

**查看FastDFS相关的可执行程序**

**ll /usr/bin/fdfs***

##### 查看FastDFS的配置文件

**ll /etc/fdfs/**



#### 注意：需要把解压后的fastdfs-5.11/conf目录下的两个文件拷贝到/etc/fdfs/ 

#### 否则后续会有很多奇怪问题不好解决

**cp http.conf /etc/fdfs/**

**cp mime.types /etc/fdfs/**



## FastDFS配置

#### 首先去掉/etc/fdfs/目录下FastDFS配置文件的后缀名



### 修改tracker.conf文件

**默认指向的FastDFS作者余庆的目录，因为在我们的机器上不存在，所有手动改一下**

**base_path=/opt/fastdfs/tracker        #配置tracker存储数据的目录**



### 修改storage.conf文件

**base_path=/opt/fastdfs/storage            #storage存储数据目录**

**store_path0=/opt/fastdfs/storage/files     #真正存放文件的目录**

**tracker_server=192.168.235.128:22122    #注册当前存储节点的跟踪器地址**



