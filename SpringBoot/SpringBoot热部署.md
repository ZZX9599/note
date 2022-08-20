### SpringBoot热部署

**SpringBoot内置了Tomcat，实际上这个Tomcat就是spring容器的对象**

**我们要让这个Tomcat感知到代码的变化**



**依赖**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```





#### 手工启动热部署【不常用】

**代码改变了：使用Crtl+F9进行项目重构，不会重新加载jai文件**





#### 自动热部署



![QQ截图20220510092009](E:\笔记整理\SpringBoot\图片\QQ截图20220510092009.png)





**开启自动Build项目**

![QQ截图20220510091909](E:\笔记整理\SpringBoot\图片\QQ截图20220510091909.png)







![QQ截图20220510092924](E:\笔记整理\SpringBoot\图片\QQ截图20220510092924.png)



**静态页面不参与热部署**





```xml
spring.devtools.restart.exclude=application.properties    不参与热部署的内容
```

