### SpringBoot打包为jar文件

**创建了一个包含了jsp的项目**

**修改pom.xml**

**指定打包后的文件名称**

```xml
<build>
   <!--打包后的文件名称-->
   <finalName>myboot</finalName>
</build>
```



**指定springboot-maven-plugin版本，这个必须要有，不然不能执行**

```xml
<plugins>
   <plugin>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-maven-plugin</artifactId>
      <!--打包jar， 有jsp文件时，必须指定maven-plugin插件的版本是 1.4.2.RELEASE-->
      <version>1.4.2.RELEASE</version>
   </plugin>
</plugins>
```

**在执行打包之前，记得跳过测试**

**最后执行 maven clean package**

**在target目录中，生成jar 文件， 例子是myboot.jar**

**执行独立的springboot项目  在cmd中 java  -jar  myboot.jar**

