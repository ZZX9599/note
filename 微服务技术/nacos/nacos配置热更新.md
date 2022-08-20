### nacos配置热更新

**方式一：在@Value注入的变量所在类上添加注解@RefreshScope**



![热更新方式一](E:\笔记整理\微服务技术\图解\热更新方式一.png)





**方式二：使用@ConfigurationProperties注解**



![热更新方式二](E:\笔记整理\微服务技术\图解\热更新方式二.png)



**推荐使用@ConfigurationProperties**



**总结：**

**Nacos配置更改后，微服务可以实现热更新，方式：**

**①通过@Value注解注入，结合@RefreshScope来刷新**

**②通过@ConfigurationProperties注入，自动刷新**

**注意事项：**

**•不是所有的配置都适合放到配置中心，维护起来比较麻烦**

**•建议将一些关键参数，需要运行时调整的参数放到nacos配置中心，一般都是自定义配置**