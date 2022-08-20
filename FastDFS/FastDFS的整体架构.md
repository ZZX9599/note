### FastDFS的整体架构

**FastDFS文件系统由两大部分构成，一个是客户端，一个是服务端**



#### 客户端：

**客户端通常指我们的程序，比如我们的Java程序去连接FastDFS、操作FastDFS，那我们的Java程序就是一个客**

**户端，FastDFS提供专有API访问，目前提供了C、Java和PHP几种编程语言的API，用来访问FastDFS文件系统。**



#### 服务端：

**服务端由两个部分构成：一个是跟踪器（tracker），一个是存储节点（storage）**



#### 跟踪器：

**跟踪器（tracker）主要做调度工作，在内存中记录集群中存储节点storage的状态信息，是前端Client和后端存储节点storage的枢纽。因为相关信息全部在内存中，Tracker server的性能非常高，一个较大的集群（比如上百个group）中有3台就足够了。**



#### 储存节点

**存储节点（storage）用于存储文件，包括文件和文件属性（meta data）都保存到存储服务器磁盘上，完成文件管理的所有功能：文件存储、文件同步和提供文件访问等**



**类似于Nginx的master进程和worker进程**

