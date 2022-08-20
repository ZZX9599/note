### 分布式文件系统FastDFS的HTTP访问配置

**在文件上传的时候，上传成功的信息中有提示我们可以通过某个路径去访问上传的文件，但是我们直接访问这个路**

**径，却不可以，那么已经上传到FastDFS文件系统中的文件，我们如何在浏览器中访问呢？**

**FastDFS提供了一个Nginx扩展模块，利用该模块，我们可以通过Nginx访问已经上传到FastDFS上的文件**



#### 前期准备工作

**1：将Fastdfs的Nginx扩展模块源代码上传到Linux上**

**拓展模块src路径：/opt/resources/fastdfs-nginx-module-master/src**



**2：解压下载下来的fastdfs-nginx-module-master.zip 文件**

**unzip fastdfs-nginx-module-master.zip**



**3：安装Nginx并且添加fastDFS模块**

**因为这个模块必须在Nginx的安装的过程中才能添加，所有我们需要重新安装一个nginx**

**为了和原来已安装的Nginx进行区分，我们把新安装的Nginx取名为nginx_fdfs**



**4：切换至解压后的Nginx主目录，执行配置操作**

**./configure --prefix=/usr/local/nginx_fdfs --add-module=/opt/resources/fastdfs-nginx-module-master/src**

**Ø --prefix是指定nginx安装路径**

**Ø --add-module指定fastDFS的nginx模块的源代码路径**



**这个时候相当于重新装了一个nginx叫做nginx_fdfs，只不过添加了fastDFS模块**



**我们知道，Nginx的安装需要Linux安装相关的几个库，否则编译会出现错误，这几个库分别是：**

**Ø gcc编译器是否安装**

**检查是否安装：yum list installed | grep gcc**

**执行安装：yum install gcc -y**

**Ø openssl库是否安装**

**检查是否安装：yum list installed | grep openssl**

**执行安装：yum install openssl openssl-devel -y**

**Ø pcre库是否安装**

**检查是否安装：yum list installed | grep pcre**

**执行安装：yum install pcre pcre-devel -y**

**Ø zlib库是否安装**

**检查是否安装：yum list installed | grep zlib**

**执行安装：yum install zlib zlib-devel -y**

**yum install gcc openssl openssl-devel pcre pcre-devel zlib zlib-devel –y**





