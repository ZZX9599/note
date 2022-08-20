### Nginx环境搭建

**免费开源版的官方网站：http://nginx.org **

**Nginx是C语言编写的项目，安装需要确定Linux安装相关的几个库**

**否则配置和编译会出现错误， 具体的检查安装过程为**



#### 1）gcc编译器是否安装

**检查是否安装：yum list installed | grep gcc**

**执行安装：yum install gcc -y**



#### 2）openssl库是否安装

**检查是否安装：yum list installed | grep openssl**

**执行安装：yum install openssl openssl-devel -y**



#### 3）pcre库是否安装

**检查是否安装：yum list installed | grep pcre**

**执行安装：yum install pcre pcre-devel -y**



#### 4）zlib库是否安装

**检查是否安装：yum list installed | grep zlib**

**执行安装：yum install zlib zlib-devel -y**



**推荐：一次性安装，执行如下命令**

**yum install gcc openssl openssl-devel pcre pcre-devel zlib zlib-devel -y**





### 正式安装

**1： 解压下载下来的nginx文件，执行命令：tar -zxvf nginx-1.14.2.tar.gz**

**2： 切换至解压后的nginx主目录，执行命令：cd nginx-1.14.2**

**3： 在nginx主目录nginx-1.14.2下执行命令：./configure --prefix=/usr/local/nginx** 

**4： 其中--prefix是指定nginx安装路径      注意:等号左右不要有空格**

**5： 执行命令进行编译：make**

**6： 执行命令进行安装：make install**



**在安装目录下面有四个文件夹  conf html logs sbin**

**conf里面都是配置文件，里有一个nginx.conf【重要配置】**

**html里面50.html 【错误页面】 index.html【欢迎首页】都是纯静态页面**

**logs里面是日志信息**

**sbin里面是启动命令文件**

