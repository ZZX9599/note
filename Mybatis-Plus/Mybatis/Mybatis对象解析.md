### Mybatis对象解析



#### (1)      Resources 类

Resources 类，顾名思义就是资源，用于读取资源文件。其有很多方法通过加载并解析资源文件，返回不同类型的 IO 流对象，这个流对象里面封装了mybatis主配置文件的信息



#### (2)      SqlSessionFactoryBuilder 类

SqlSessionFactory 的 创 建 ， 需 要 使 用 SqlSessionFactoryBuilder 对 象 的 build() 方 法 。 由 于SqlSessionFactoryBuilder对象在创建完工厂对象后，就完成了其历史使命，即可被销毁。所以，一般会将该 对象创建为一个方法内的局部对象，方法结束，对象销毁。



#### (3)      SqlSessionFactory 接口

SqlSessionFactory 接口对象是一个**重量级对象**（系统开销大的对象），是线程安全的，所以一个应用只需要一个该对象即可。创建 SqlSession 需要使用 SqlSessionFactory 接口的的 openSession()方法。

A.  openSession(true)：创建一个有自动提交功能的 SqlSession

B.  openSession(false)：创建一个非自动提交功能的 SqlSession，需手动提交

C.  openSession()：同 openSession(false)



#### (4)      SqlSession 接口

SqlSession 接口对象用于执行持久化操作。一个 SqlSession 对应着一次数据库会话，一次会话以SqlSession 对象的创建开始，以 SqlSession 对象的关闭结束。

SqlSession 接口对象是线程不安全的，所以每次数据库会话结束前，需要马上调用其 close()方法，将其关闭。再次需要会话，再次创建。 SqlSession 在方法内部创建，使用完毕后关闭