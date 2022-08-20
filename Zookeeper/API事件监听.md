### API事件监听



#### 监听自己

```java
/**
 * API事件监听
 * 单个节点指定监听
 */
@Test
public void testNodeCatch() throws Exception {
    //1:创建NodeCache对象
    NodeCache nodeCache=new NodeCache(client02,"/appq");

    //2:注册监听
    nodeCache.getListenable().addListener(new NodeCacheListener() {
        @Override
        public void nodeChanged() throws Exception {
            System.out.println("节点变化了");

            //拿到最新的数据
            byte[] bytes=nodeCache.getCurrentData().getData();
            System.out.println("最新数据:"+new String(bytes));
        }
    });

    //3:开启监听【设置为true，则开启监听是加载缓存数据】
    nodeCache.start(true);

    while (true){

    }
}
```



#### 监听子节点

```java
/**
 * 监听子节点【注意：不会监听自己】
 * @throws Exception
 */
@Test
public void testPathChildrenCache() throws Exception {
    //1:创建PathChildrenCache对象
    PathChildrenCache pathChildrenCache=new PathChildrenCache(client02,"/appq",true);

    //绑定监听器
    pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
        @Override
        public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
            System.out.println("子节点发生变化");

            //输出事件信息
            System.out.println(pathChildrenCacheEvent);

            //监听子节点数据变更，并拿到变更的数据

            //获取事件的类型
            PathChildrenCacheEvent.Type type = pathChildrenCacheEvent.getType();

            //比较事件的类型
            if(type.equals(PathChildrenCacheEvent.Type.CHILD_UPDATED)){
                System.out.println("数据发生了变化");
                //是更新的话，拿到最新的数据
                byte[] data = pathChildrenCacheEvent.getData().getData();
                System.out.println("最新数据:"+new String(data));
            }
        }
    });
    //开启监听器
    pathChildrenCache.start();

    while (true){

    }
}
```



#### 监听当前节点和子节点

```java
/**
 * 监听节点和子节点
 */
@Test
public void testTreeCache() throws Exception {
    //创建TreeCache对象
    TreeCache treeCache=new TreeCache(client02,"/appq");

    //注册监听
    treeCache.getListenable().addListener(new TreeCacheListener() {
        @Override
        public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
            System.out.println("节点变化了");
            System.out.println(treeCacheEvent);
            //拿到变化的数据
            TreeCacheEvent.Type type = treeCacheEvent.getType();
            if(type.equals(TreeCacheEvent.Type.NODE_UPDATED)){
                System.out.println("数据更新了");
                byte[] data = treeCacheEvent.getData().getData();
                System.out.println("最新的数据:"+new String(data));
            }
        }
    });

    //开启监听
    treeCache.start();

    while (true){

    }
}
```