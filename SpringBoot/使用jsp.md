## 使用jsp

SpringBoot不推荐使用jsp ，而是使用模板技术代替jsp



使用jsp需要配置：

  1 加入一个处理jsp的依赖。 负责编译jsp文件

```xml
<dependency>
    <groupId>org.apache.tomcat.embed</groupId>
    <artifactId>tomcat-embed-jasper</artifactId>
</dependency>
```



2) 如果需要使用servlet， jsp，jstl的功能

```xml
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>jstl</artifactId>
</dependency>

<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>javax.servlet-api</artifactId>
</dependency>

<dependency>
<groupId>javax.servlet.jsp</groupId>
	<artifactId>javax.servlet.jsp-api</artifactId>
	<version>2.3.1</version>
</dependency>

```



3) 创建一个存放jsp的目录，一般叫做webapp【记得设置为web目录，这里设置为src/main/webapp】

​    index.jsp



   4.在application.propertis文件中配置视图解析器

```java
 #配置视图解析器
 #/代表web目录的根，也就是--->src/main/webapp
 spring.mvc.view.prefix=/
 spring.mvc.view.suffix=.jsp
```

   

   5.需要在pom.xml指定jsp文件编译后的存放目录。

​      META-INF/resources

```java
    <build>
        <!--指定jsp文件编译后的存放目录-->
        <resources>
            <resource>
                <!--jsp的存放目录-->
                <directory>src/main/webapp</directory>
                <!--编译后的位置-->
                <targetPath>META-INF/resources</targetPath>
                <!--指定处理的目录和文件-->
                <includes>
                    <!--表示上面存放目录的目录和任意子目录-->
                    <include>**/*.*</include>
                </includes>
            </resource>
        </resources>
    </build>
```

   6.创建Controller， 访问jsp



**复习一下存放编译文件的位置**

**在pom.xml文件中的<build>下面写<resources>**

```java1
<resources>
    <resource>
        <!--存放目录-->
        <directory>src/main/webapp</directory>
        <!--编译后的位置-->
        <targetPath>META-INF/resources</targetPath>
        <!--指定处理的目录和文件-->
        <includes>
            <!--表示上面存放目录的目录和任意子目录-->
            <include>**/*.*</include>
        </includes>
</resource>
```

### targetPath默认指定的位置是编译后的classpath目录
