### Watch事件监听



**ZooKeeper 提供了分布式数据发布/订阅功能**

**一个典型的发布/订阅模型系 统定义了一种一对多的订阅关系**

**能让多个订阅者同时监听某一个主题对象，当这个主题对象自身状态变化时，会通知所有订阅者**

**使他们能够做出相应的处理。 ZooKeeper 中，引入了 Watcher 机制来实现这种分布式的通知功能。** 

**ZooKeeper 允许客户端向服务端注册一个 Watcher 监听，当服务端的一些事件触发了这个 Watcher**

**那么就会向指定客户端发送一个事件通知来实现分布式的通知功能。** 

**触发事件种类很多，如：节点创建，节点删除，节点改变，子节点改变等。** 

**总的来说可以概括 Watcher 为以下三个过程：**

**1：客户端向服务端注册 Watcher**

**2：服务端事件发生触发 Watcher**

**3：客户端回调 Watcher 得到触发事件情况**



**三种watch**

**1：NodeCache【监听某个特定节点】**

**2：PathChildrenCache【监听一个节点的所有子节点】**

**3：TreeCache【监听节点和子节点，上面两个的组合】**