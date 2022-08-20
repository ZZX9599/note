### SpringBoot整合Dubbo

**文档：https://github.com/apache/dubbo-spring-boot-project**



## 1：公共项目

独立的maven项目： 定义了接口和实体类

```java
public class Student implements Serializable {
    private static final long serialVersionUID = 1901229007746699151L;

    private Integer id;
    private String name;
    private Integer age;
}

public interface StudentService {

    Student queryStudent(Integer id);
}

```



## 2：提供者

##### 创建SpringBoot项目

pom.xml

```xml
<dependencies>
    
   <!--加入公共项目的gav-->
   <dependency>
      <groupId>com.bjpowernode</groupId>
      <artifactId>ch20-interface-api</artifactId>
      <version>1.0.0</version>
   </dependency>

   <!--dubbo起步依赖-->
   <dependency>
      <groupId>org.apache.dubbo</groupId>
      <artifactId>dubbo-spring-boot-starter</artifactId>
      <version>2.7.8</version>
   </dependency>

   <!--zookeeper依赖-->
   <dependency>
      <groupId>org.apache.dubbo</groupId>
      <artifactId>dubbo-dependencies-zookeeper</artifactId>
      <version>2.7.8</version>
      <type>pom</type>
      <exclusions>
         <!-- 排除log4j依赖 -->
         <exclusion>
            <artifactId>slf4j-log4j12</artifactId>
            <groupId>org.slf4j</groupId>
         </exclusion>
      </exclusions>
   </dependency>
    
</dependencies>
```

##### 提供者实现接口

```java
/**
 * 使用dubbo中的注解暴露服务
 * @Component 可以不用加
 */
@DubboService(interfaceClass = StudentService.class,version = "1.0.0",timeout = 5000)

public class StudentServiceImpl implements StudentService {
    @Override
    public Student queryStudent(Integer id) {
        Student student  = new Student();
        if( 1001 == id){
            student.setId(1001);
            student.setName("1001-张三");
            student.setAge(20);
        } else if(1002  == id){
            student.setId(1002);
            student.setName("1002-李四");
            student.setAge(22);
        }

        return student;
    }
}
```



**注意：@DubboService里面的interface可以不写，默认就是那个接口**

##### application.properties

```properties
#配置服务名称 <dubbo:application name="名称"/>
spring.application.name=studentservice-provider

#配置扫描的包， 扫描的@DubboService
dubbo.scan.base-packages=com.zzx.service

#配置dubbo协议
dubbo.protocol.name=dubbo
dubbo.protocol.port=20881

#注册中心
dubbo.registry.address=zookeeper://localhost:2181
```

##### 在启动类的上面

```java
/**
 * @EnableDubbo 启用Dubbo
 * 含有以下两个注解
 * @EnableDubboConfig
 * @DubboComponentScan
 */
@SpringBootApplication
@EnableDubbo
public class ProviderApplication {
   public static void main(String[] args) {
      SpringApplication.run(ProviderApplication.class, args);
   }
}
```



## 3：消费者

##### 创建SpringBoot项目

**pom.xml**

```xml
<dependencies>

   <!--加入公共项目的gav-->
   <dependency>
      <groupId>com.bjpowernode</groupId>
      <artifactId>022-interface-api</artifactId>
      <version>1.0.0</version>
   </dependency>

   <!--dubbo依赖-->
   <dependency>
      <groupId>org.apache.dubbo</groupId>
      <artifactId>dubbo-spring-boot-starter</artifactId>
      <version>2.7.8</version>
   </dependency>

   <!--zookeeper依赖-->
   <dependency>
      <groupId>org.apache.dubbo</groupId>
      <artifactId>dubbo-dependencies-zookeeper</artifactId>
      <version>2.7.8</version>
      <type>pom</type>
      <exclusions>
         <!-- 排除log4j依赖 -->
         <exclusion>
            <artifactId>slf4j-log4j12</artifactId>
            <groupId>org.slf4j</groupId>
         </exclusion>
      </exclusions>
   </dependency>
</dependencies>
```

##### 创建了Controller 或者 Service都可以

```java
@RestController
public class DubboController {

    /**
     * 引用远程服务， 把创建好的代理对象，注入给studentService
     *
     * @DubboReference(interfaceClass = StudentService.class,version = "1.0")
     *
     * 没有使用interfaceClass，默认的就是 引用类型的 数据类型
     */
    @DubboReference(version = "1.0")
    private StudentService studentService;

    @GetMapping("/query")
    public String queryStudent(Integer id){
        Student student   = studentService.queryStudent(id);
        return "调用远程接口，获取对象："+student;
    }
}
```

**注意：在@DubboReference里面可以不写interface，默认就引入对应的interface**

##### application.properties

```properties
#指定服务名称
spring.application.name=consumer-application
#指定注册中心
dubbo.registry.address=zookeeper://localhost:2181
```



### 总结：

### 提供者使用@DubboService，声明版本号，主启动类加上@EnableDubbo

### 消费者使用@DubboReference，引用Dubbo远程服务

### 容易出错的地方，排除log4j依赖