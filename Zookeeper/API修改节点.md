### API修改节点

```java
/**
 * 1：修改节点数据
 * 2：根据版本号修改节点数据
 */
@Test
public void testSet() throws Exception {
    //1：修改节点数据
    client02.setData().forPath("/app1","modify".getBytes());


    //2：根据版本号进行修改
    /**
     * 查询的时候先查询版本，从状态信息里面拿到version
     * 修改的时候如果版本不一致，修改就不做，类似乐观锁
     */

    //状态对象
    Stat stat=new Stat();
    client02.getData().storingStatIn(stat).forPath("/app1");

    //拿到状态里面的版本
    int version=stat.getVersion();

    client02.setData().withVersion(100).forPath("/app1","我又改了".getBytes());
}
```



```xml
版本不一致：报错
org.apache.zookeeper.KeeperException$BadVersionException: 
KeeperErrorCode = BadVersion for /zzx/app1
```

