### 快速开始Mybatis-Plus

```xml
连接地址url
MySQL5.7版本的url：
jdbc:mysql://localhost:3306/mybatis_plus?characterEncoding=utf-8&useSSL=false
MySQL8.0版本的url：
jdbc:mysql://localhost:3306/mybatis_plus?
serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false
```



#### 初始化工程

创建一个空的 Spring Boot 工程（工程将以 Mysql 作为默认数据库进行演示）



#### 声明实体类

```java
public class User {
    /*
        id是自增的，需要指定主键信息
        value:主键字段的名称，如果是id，可以不写
        type:指定主键的类型，主键的值是如何生成的，IdType.AUTO 表示自动增长
     */
    @TableId(
            value = "id",
            type = IdType.AUTO
    )
    private Integer id;
    private String name;
    private String email;
    private Integer age;
}
```

#### 添加依赖

引入 Spring Boot Starter 父工程：

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.6.4</version>
    <relativePath/>
</parent>
```

#### 引入 

`spring-boot-starter`、`spring-boot-starter-test`、`mybatis-plus-boot-starter`、`lombok` 、`mybatis` 依赖：

```xml
        <!--起步依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <!--测试-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        
        <!--mp-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.5.1</version>
        </dependency>
        
        <!--lombok-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        
        <!--mysql-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
            <version>5.1.43</version>
        </dependency>
```

#### 编写配置文件

```yaml
#配置数据库的信息
spring:
  datasource:
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mp
    username: root
    password: JXLZZX79
```

#### 在 Spring Boot 启动类中添加 `@MapperScan` 注解，扫描 Mapper 文件夹：

```java
@SpringBootApplication
@MapperScan(value = "com.zzx.mapper")
public class MpFirstApplication {
    public static void main(String[] args) {
        SpringApplication.run(MpFirstApplication.class, args);
    }
}
```

#### 编写 Mapper 类 `UserMapper.java`

```java
/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * 自定义的Mapper，就是dao接口
 * 1：要实现BaseMapper
 * 2:指定实体类
 * BaseMapper是mp框架中的对象，定义了17个基本的方法(crud)
 */

public interface UserMapper extends BaseMapper<User> {
    //不写任何东西都可以
}
```

#### 实际上mp使用动态代理来创建的代理对象，调用BaseMapper的方法来进行CRUD操作！