### push模式实现持久化



**默认情况，持久化配置都是推送到客户端，源码没有直接实现推送远程，所以设置起来比较麻烦**



**push模式实现最为复杂，依赖于nacos，并且需要改在Sentinel控制台。整体步骤如下：**

**1.修改order-service服务，使其监听Nacos配置中心**

**2.修改Sentinel-dashboard源码，配置nacos数据源**

**3.修改Sentinel-dashboard源码，修改前端页面**

**4.重新编译、打包-dashboard源码**

**见笔记：sentinel规则持久化.md**