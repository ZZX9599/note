### javaAPI建立连接



```java
/**
 * 建立连接
 */
@Test
public void testConnect(){
    //第一种方式连接客户端
    /**
     * 参数一：连接字符串【zkServer的地址和端口,集群的话用,分开  192.168.220.138:2181,192.168.220.139:2181】
     * 参数二：会话超时时间【当前连接建立好之后，多少时间没和zookeeper连通，就超时】
     * 参数三：连接超时时间【多久连接不上，报错超时】
     * 参数四：重试策略
     */
    //重试策略
    RetryPolicy retryPolicy=new ExponentialBackoffRetry(3000,10);
    CuratorFramework client01 = CuratorFrameworkFactory.newClient("192.168.220.138:2181",
            60 * 1000, 15 * 1000, retryPolicy);
    //开启连接
    client01.start();


    //第二种方式连接客户端
    CuratorFramework client02 = CuratorFrameworkFactory.builder().connectString("192.168.220.138:2181").
            sessionTimeoutMs(60 * 1000).
            connectionTimeoutMs(15 * 1000).
            retryPolicy(retryPolicy).
            /**
             * 注意：加入了namespace的话，默认每次操作就以namespace作为根路径进行操作
             */
            namespace("zzx").
            build();

    //开启连接
    client02.start();
}
```