### API删除节点

```java
/**
 * 删除节点
 */
@Test
public void testDelete() throws Exception {
    /**
     * 1：删除单个节点
     * 2：删除带有子节点的节点
     * 3：必须成功的删除
     * 4：回调的处理
     */

    //1：删除单个节点
    client02.delete().forPath("/app1");

    //2：删除带有子节点的节点
    client02.delete().deletingChildrenIfNeeded().forPath("/app4");

    //3：必须删除成功【防止网络不佳，断开和服务端的连接，常用，本质就是不停重试】
    client02.delete().guaranteed().forPath("/app2");

    //4：删除之后执行的回调函数
    client02.delete().guaranteed().inBackground(new BackgroundCallback() {
        @Override
        public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
            System.out.println("我被删除了");
            System.out.println(curatorEvent);
        }
    }).forPath("/app1");
}
```