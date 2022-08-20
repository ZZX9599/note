### Zookeeper注册中心

**Zookeeper 是一个高性能的，分布式的，开放源码的分布式应用程序协调服务，简称 zk**

**Zookeeper 是翻译管理是动物管理员。可以理解为 windows 中的资源管理器或者注册表。**

**他是一个树形结构。这种树形结构和标准文件系统相似。ZooKeeper 树中的每个节点被称为 Znode**

**和文件系统的目录树一样，ZooKeeper 树中的每个节点可以拥有子节点。**

**每个节点表 示一个唯一服务资源。Zookeeper 运行需要 java 环境。**



### Zookeeper安装配置

**Windows 平台 Zookeeper 安装**

**配置下载的文件 zookeeper-3.5.4-beta.tar.gz. 解压后到目录就可以了**

**复制 zoo-sample.cfg 改名为 zoo.cfg**

**tickTime: 心跳的时间，单位毫秒. Zookeeper 服务器之间或客户端与服务器之间维持心跳的 时间间隔**

**也就是每个 tickTime 时间就会发送一个心跳。表明存活状态**

**dataDir: 数据目录，可以是任意目录。存储 zookeeper 的快照文件、pid 文件，默认为 /tmp/zookeeper**

**建议在 zookeeper 安装目录下创建 data 目录**

**将 dataDir 配置改 为/usr/local/zookeeper-3.4.10/data** 

**clientPort: 客户端连接 zookeeper 的端口，即 zookeeper 对外的服务端口，默认为 2181**

**admin.serverPort   解决8080端口冲突**



**启动Zookeeper之后，看见binding to port 0.0.0.0/0.0.0.0:2181就代表成功！**



**linux配置完全一样，启动的时候  zkServer.sh start**