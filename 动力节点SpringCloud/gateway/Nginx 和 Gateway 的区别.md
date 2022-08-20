### Nginx 和 Gateway 的区别

**Nginx 在做路由，负载均衡，限流之前，都有修改 nginx.conf 的配置文件，把需要负载均衡， 路由，限流的规则加在里面。Eg:使用 nginx 做 tomcat 的负载均衡 但是 gateway 不同，gateway 自动的负载均衡和路由，gateway 和 eureka 高度集成，实现 自动的路由，和 Ribbon 结合，实现了负载均衡（lb），gateway 也能轻易的实现限流和权 限验证。 Nginx（c）比 gateway（java）的性能高一点。**

**本质的区别呢？ Nginx (更大 服务器级别的) Gateway （项目级别的）**



![区别](E:\笔记整理\动力节点SpringCloud\图解\区别.png)





![访问流程](E:\笔记整理\动力节点SpringCloud\图解\访问流程.png)