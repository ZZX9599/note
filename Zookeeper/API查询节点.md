### 查询节点

```java
/**
 * 查询操作
 */
@Test
public void testGet() throws Exception {
    /**
     * 1：查询节点：get
     * 2：查询子节点：ls
     * 3：查询节点状态信息：ls -s
     */

    //1:查询节点数据
    byte[] bytes = client01.getData().forPath("/app1");
    System.out.println(new String(bytes));


    //2:查询子节点【如果有namespace，就找的是namespace下开始的】
    List<String> strings = client02.getChildren().forPath("/");
    System.out.println(strings);


    //3:查询节点状态信息
    /**
     *  private long czxid;
     *  private long mzxid;
     *  private long ctime;
     *  private long mtime;
     *  private int version;
     *  private int cversion;
     *  private int aversion;
     *  private long ephemeralOwner;
     *  private int dataLength;
     *  private int numChildren;
     *  private long pzxid;
     */

    //创建一个空的状态对象
    Stat stat=new Stat();
    //把状态对象放进去
    client02.getData().storingStatIn(stat).forPath("/app4");
    System.out.println(stat);
}
```