### ORM操作Mysql

**使用MyBatis框架操作数据，  在SpringBoot框架集成MyBatis**

**步骤：**

**1：加上mybatis的起步依赖，完成mybatis对象自动配置， 对象放在容器中**

**2：pom.xml 指定把src/main/java目录中的xml文件包含到classpath下，不然找不到对应的文件**

**3：创建实体类**

**4：创建dao接口**

**5：在接口中创建对应的方法**

**6：创建mapper.xml文件，写sql语句**

**7：创建service接口和实现类，加入@Resources注解，在service中调用对应的dao实现类**

**8：创建Controller对象**

**9：写application.yml配置文件，配置数据库的连接信息**



**在SpringBoot2.0中，官方推荐使用HikariCP连接池，速度是最快的**



### 第一种方式 ： @Mapper

**@Mapper：放在dao接口的上面， 每个接口都需要使用这个注解。创建此接口的代理对象**

**缺点：每一个接口都需要加上@Mapper！**

```java
/**
 * @Mapper：告诉MyBatis这是dao接口，创建此接口的代理对象。
 * 位置：在类的上面
 */
@Mapper
public interface StudentDao {
    Student selectById(@Param("stuId") Integer id);
}
```

**在使用springboot的时候，数据库的配置文件写在application.yml文件中**

```properties
#配置连接数据库的信息
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/springboot
spring.datasource.username=root
spring.datasource.password=JXLZZX79
```



### 第二种方式  @MapperScan

**@MapperScan：在主启动类上面加上这个注解！创建此接口的代理对象，会扫描对应的dao接口和mapper文件**

```java
/**
 * @MapperScan: 找到Dao接口和Mapper文件
 * basePackages：Dao接口所在的包名
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.zzx.dao","com.jxl.dao"})
public class MySpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(MySpringBoot.class,args);
    }

```



### 第三种方式： Mapper文件和Dao接口分开管理

**默认情况下，默认是dao下面的接口，对应接口名字的xml文件，我们改变位置，需要在配置文件中指定**

 现在把Mapper文件放在resources目录下

1）在resources目录中创建子目录 （自定义的） ， 例如mapper，java目录下只存放java文件

2）把mapper文件放到mapper目录中

3）在application.properties文件中，指定mapper文件的目录

```properties
#指定mapper文件的位置
mybatis.mapper-locations=classpath:mapper/*.xml
#指定mybatis的日志
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

4) 在pom.xml中指定 把resources目录中的文件 ， 编译到目标目录中

```xml
<!--resources插件-->
<resources>
   <resource>
      <directory>src/main/resources</directory>
      <includes>
         <include>**/*.*</include>
      </includes>
   </resource>
</resources>
```



