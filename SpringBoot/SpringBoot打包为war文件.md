### SpringBoot打包为war文件

**1：声明打包的类型**

```Java
<groupId>com.zzx</groupId>
<artifactId>ch22-springboot-package-war</artifactId>
<version>0.0.1-SNAPSHOT</version>
<!--打包类型-->
<packaging>war</packaging>
```

**2：在pom.xml文件的bulid标签里面声明打包的文件名字**

```xml
<build>
    <!--配置打包后的文件-->
    <!--打包文件名-->
    <finalName>myBoot</finalName>
    <!--打包类型-->
    <!--打包类型在上面-->
</bulid>
```

**3：在主启动类上继承SpringBootServletInitializer，必须得实现configure方法才可以**

```java
package com.zzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author ZZX
 */
@SpringBootApplication
public class JspApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(JspApplication.class, args);
    }

    /**
     * 要想在自己的Tomcat运行，先继承SpringBootServletInitializer，重写configure方法
     * @param builder
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(JspApplication.class);
    }
}
```