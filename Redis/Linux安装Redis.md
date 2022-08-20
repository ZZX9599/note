### Linux安装Redis

**Redis 是使用 c 语言编写的。使用源文件安装方式，需要编译 c 源文件，会使用 gcc 编译器**

**gcc 是 GNU compiler collection 的缩写，它是 Linux 下一个编译器集合(相当于 javac )，是 c 或 c++程序的编译器。 **

**使用yum进行安装gcc 。执行命令：yum -y install gcc**

**安装完成之后编译源文件   在解压的根目录使用make命令进行安装**



**如果没安装gcc的情况下，出错后，后面重新make，会出错，先把之前的编译文件清理了  make distclean**



**如果 make 命令执行过程中出现错误： error: jemalloc/jemalloc.h: No such file or directory**



**解决方式执行 make MALLOC=libc**



**然后执行make install命令**



### 启动Redis服务器

**前台启动：redis-server 配置文件**

**后台启动：redis-server 配置文件 &**

**如果配置了文件，但是没有在启动的时候指定配置文件，配置文件是不会生效的**



### 关闭Redis服务

**推荐使用的方式：redis-cli shutdown   redis 先完成数据操作，然后再关闭**

**ps-ef | grep redis   查看到主pid号  使用kill -9 pid号   直接杀死进程，数据可能会丢失**

