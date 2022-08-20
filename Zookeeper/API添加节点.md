### API添加节点

```java
    /**
     * 创建节点 持久，顺序，临时，顺序临时等
     */
    @Test
    public void testCreateNode() throws Exception {
        /**
         * 1：创建基本节点
         * 2：创建带有数据的节点
         * 3：设置节点类型
         * 4：创建多级节点
         */

        
        //1：基本创建节点
        /**
         * 如果创建节点，没有指定数据，默认将客户端的ip作为数据存储
         */
        String path1 = client02.create().forPath("/app1");
        //输出路径
        System.out.println(path1);

        
        
        //2：创建节点，带数据
        String path2 = client02.create().forPath("/app2", "你好".getBytes());
        //输出路径
        System.out.println(path2);

        
        
        //3：创建节点，临时
        client02.create().withMode(CreateMode.EPHEMERAL).forPath("/app3","你也好".getBytes());
        
        
        
        //4：创建多级节点
        /**
         * 不可以没有父节点就创建子节点
         */
        //client02.create().forPath("/app4/p1");   /app4不存在的话，报错
        
        //创建多级目录
        client02.create().creatingParentsIfNeeded().forPath("/app4/p1");
    }
```