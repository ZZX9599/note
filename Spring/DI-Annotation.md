## DI-Annotation

#### 对于 DI 使用注解，将不再需要在 Spring 配置文件中声明 bean 实例。Spring 中使用注解， 需要在原有 Spring 运行环境基础上再做一些改变。 需要在 Spring 配置文件中配置组件扫描器，用于在指定的基本包中扫描注解。

#### 使用注解，需要使用组件扫描器  context:component-scan



#### 1）@Component可以带参数 参数的值就是bean的id值，也可以不写，默认是类名的小写

####       @Component的位置是放在类的上面的

**实体类**

```Java
@Component("myStudent")
public class Student {
    private Integer age;
    private String name;
    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
```

**配置文件**

```Java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">
    <!--组件扫描器，指定包名，扫描所有的注解，创建对象放进spring容器-->
    <!--工作方式：会扫描指定包和子包的所有含有注解的类，按照注解的功能创建对象并放进容器-->
    <!--加入组件扫描器，配置文件的变化：加入新的约束文件spring-context.xsd.还会加入新的命名空间-->
    <context:component-scan base-package="com.zzx.bao01"/>
</beans>
```

**执行的时候，依然是先执行无参构造，创建对象**



**组件扫描器的三种使用**

**1)使用多个 context:component-scan 指定不同的包路径**

**<context:component-scan base-package="com.zzx.bao01"/>**

**<context:component-scan base-package="com.zzx.bao02"/>**



**2)分隔符可以使用逗号（,）分号（;）还可以使用空格，不建议使用空格。**

**<context:component-scan base-package="com.zzx.bao01,com.zzx.bao02"/>**

**<context:component-scan base-package="com.zzx.bao01;com.zzx.bao02"/>**



**3)base-package 是指定到父包名**

**<context:component-scan base-package="com.zzx"/>**



#### 以下三个注解与@Component的使用方式一样，但是侧重点不同！

#### 2)@Repository 用于对 DAO 实现类进行注解，访问数据库的，持久层对象

#### 3)@Service 用于对 Service 实现类进行注解，对业务处理，具有事务功能，业务层对象

#### 4)@Controller 用于对 Controller 实现类进行注解，能够接受用户参数和显示处理结果，控制层对象



#### 这三个注解与@Component 都能创建对象，但这三个注解还有其他的含义，@Service 创建业务层对象，业务层对象可以加入事务功能，@Controller 注解创建的对象可以作为处 理器接收用户的请求@Repository，@Service，@Controller 是对@Component 注解的细化，标注不同层的对象。即持久层对象，业务层对象，控制层对象。用于给项目分层！

#### 什么时候使用@Component呢？不满足以上三种，但是还需要创建对象放进容器，就使用