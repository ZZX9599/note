### Feign的自定义配置



![Feign自定义配置](E:\笔记整理\微服务技术\图解\Feign自定义配置.png)





### **配置方式**



![Feign自定义配置方式](E:\笔记整理\微服务技术\图解\Feign自定义配置方式.png)







![Feign自定义配置02](E:\笔记整理\微服务技术\图解\Feign自定义配置02.png)





**总结：**

**Feign的日志配置:**

**1.方式一是配置文件，feign.client.config.xxx.loggerLevel**

**①如果xxx是default则代表全局**

**②如果xxx是服务名称，例如userservice则代表某服务**

**2.方式二是java代码配置Logger.Level这个Bean**

**①如果在@EnableFeignClients注解声明则代表全局**

**②如果在@FeignClient注解中声明则代表某服务**





**一般在调试错误用FULL。除此用BASIC或者关闭**

