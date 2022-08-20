### Java Config

**JavaConfig: 使用java类作为xml配置文件的替代， 是配置spring容器的纯java的方式。 在这个java类这可以创建java对象，把对象放入spring容器中（注入到容器）**



**使用两个注解：**

**1）@Configuration ： 放在一个类的上面，表示这个类是作为配置文件使用的。**

**2）@Bean：声明对象，把对象注入到容器中。**



**@Configuration相当于把类作为了配置文件**

**@Bean相当于<bean>   </bean>把对象放进spring的容器**



```java
/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * @Configuration 相当于spring.xml
 */
@Configuration
public class SpringConfig {
    /**
     * 方法：返回值是对象，在方法上面加上@Bean，返回值就会注入到spring容器
     * @Bean代表把返回值注入到spring容器
     * 属性：name【不写默认就是方法名，写了就代表对象名字】
     */
    @Bean
    public Date createDate(){
        Date date=new Date();
        return date;
    }
}
```



```java
public class SpringConfigTest {
    public static void main(String[] args) {
        //使用注解的话，就使用AnnotationConfigApplicationContext实现类
        //配置文件的话，就使用ClassPathXmlApplicationContext实现类
        ApplicationContext ctx=new AnnotationConfigApplicationContext(SpringConfig.class);
        //使用的是方法名来获取对象
        Date date=(Date) ctx.getBean("createDate");
        System.out.println(date);
    }
}
```





## @ImporResource

**@ImportResource 作用导入其他的xml配置文件， 等于在xml** 

```
<import resources="其他配置文件"/>
```

**例如**

```java
@Configuration
@ImportResource(value = "classpath:spring.xml")
public class SpringConfig {
    /**
     * value属性是一个数组
     * 会把配置文件的对象，导入到这个配置类中
     * 在spring.xml里面配置的类就可以直接通过容器来拿到
     */
    @Bean(name = "aaa")
    public Date createDate(){
        Date date=new Date();
        return date;
    }
}
```



## @PropertyResource

**@PropertyResource: 读取properties属性配置文件。 使用属性配置文件可以实现外部化配置 ，**

**在程序代码之外提供数据。一般读取数据库的配置文件**

**相当于： <context:property-placeholder location="classpath:config.properties"/>**

**步骤：**

1. **在resources目录下，创建properties文件， 使用k=v的格式提供数据**
2. **在PropertyResource 指定properties文件的位置**
3. **使用@Value（value="${key}"）**



```Java
@Configuration
@PropertySource(value = "classpath:jdbc.properties")
@ComponentScan(basePackages = "com.zzx.vo")
public class SpringConfig {
    /**
     * 方法：返回值是对象，在方法上面加上@Bean，返回值就会注入到spring容器
     * @Bean代表把返回值注入到spring容器
     * 属性：name【不写默认就是方法名，写了就代表对象名字】
     */
}
```



```Java
package com.zzx.vo
    
@Component("mysql")
public class Mysql {
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String userName;
    @Value("${jdbc.password}")
    private String password;
}
```



```java
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/SpringBoot
jdbc.username=root
jdbc.password=JXLZZX79
```

