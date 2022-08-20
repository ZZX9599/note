## Spring整合Mybatis

**使用的技术：IOC**

**因为IOC就是把Mybtis的重要对象放进Spring容器**

**开发人员从spring中获取对象。开发人员就不用同时面对两个或多个框架了， 就面对一个spring**



#### Mybatis使用的步骤

**1）定义dao接口 ，StudentDao**

**2）定义mapper文件 StudentDao.xml**

**3）定义mybatis的主配置文件 mybatis.xml**

**4）创建dao的代理对象**

**StudentDao dao = SqlSession.getMapper(StudentDao.class)**

**List<Student> students  = dao.selectStudents()**



**注意：要使用dao对象，需要使用getMapper()方法    怎么能使用getMapper()方法，需要哪些条件？**



**1.创建SqlSessionFactory对象【 通过读取mybatis的主配置文件，能创建SqlSessionFactory对象】**

```Java
String resource = "SqlMapConfig.xml"       

InputStream inputStream = Resources.getResourceAsStream(resource)

SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream)
```

**2：获取SqlSession对象【需要使用SqlSessionFactory的openSession()方法】**

```Java
SqlSession session = factory.openSession()
```

**3：获取Mapper**

```java
StudentDao studentDao = session.getMapper(StudentDao.class) 
```



**需要SqlSessionFactory对象， 使用Factory能获取SqlSession ，有了SqlSession就能有dao ， 目的就是获取dao对象
Factory创建需要读取主配置文件**

**我们会使用独立的连接池类替换mybatis默认自己带的， 把连接池类也交给spring创建**

**通过以上的说明，我们需要让spring创建以下对象
1.独立的连接池类的对象， 使用阿里的druid连接池对象
2.SqlSessionFactory对象
3.创建出的dao对象**

**需要学习就是上面三个对象的创建语法，使用xml的bean标签。**



#### Mybatis主配置文件的信息

**1：数据库的信息**

**2：mapper文件的位置**



**Mybatis主配置文件**

```Java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">

<configuration>
    <!--settings：控制mybatis全局行为-->
    <settings>
        <!--mybatis输出日志-->
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>

    <!--设置别名-->
    <typeAliases>
        <!--实体类的包名-->
        <package name="com.zzx.domain"/>
    </typeAliases>

    <!--sql映射文件的位置-->
    <mappers>
        <!-- name:包名  这个包中的所有mapper.xml文件一次性都能加载-->
        <package name="com.zzx.dao"/>
    </mappers>
</configuration>
```

**Spring配置文件**

```Java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       https://www.springframework.org/schema/context/spring-context.xsd">
    <!--引入数据库信息的配置文件-->
    <context:property-placeholder location="classpath:jdbc.properties"/>

    <!--声明数据源dataSources，连接数据库-->
    <!--配置数据库的连接池，采用阿里巴巴德鲁伊连接池-->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
          init-method="init" destroy-method="close">
        <!--set注入连接数据库-->
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
        <!--最大的连接数-->
        <property name="maxActive" value="20"/>
    </bean>

    <!--声明mybatis的SqlSessionFactoryBean的类，这个类内部是创建SqlSessionFactory的-->
    <!--SqlSessionFactoryBean是由mybatis官方提供的，在他们的整合包中-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <!--创建SqlSessionFactory对象需要读取配置文件，配置文件包括数据库的信息和mapper文件位置-->
        <!--现在数据库的信息声明在了德鲁伊连接池，mapper文件的位置在mybatis主配置文件中-->
        <!--因此需要把两个配置文件赋值给它-->
        <!--数据源：也就是数据库的信息-->
        <property name="dataSource" ref="dataSource"/>
        <!--mybatis主配置文件：引入mapper映射文件位置-->
        <!--注意赋值是value-->
        <property name="configLocation" value="classpath:mybatis.xml"/>
    </bean>

    <!--创建dao对象，使用SqlSession的getMapper(StudentDao.class)-->
    <!--由mybatis提供，在内部调用getMapper()生成每个dao接口的代理对象-->
    <!--不需要id，因为很多的代理对象，默认都是首字母小写的对象-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <!--创建dao对象必须要SqlSession和dao接口类的位置-->
        <!--指定sqlSessionFactory的id-->
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <!--指定dao接口类的位置-->
        <!--扫描这个包的所有接口，对所有接口都执行getMapper方法，得到dao代理对象-->
        <!--创建好的dao对象都在spring容器中-->
        <property name="basePackage" value="com.zzx.dao"/>
    </bean>

    <!--组件扫描器-->
    <context:component-scan base-package="com.zzx.dao"/>
    <context:component-scan base-package="com.zzx.service"/>
</beans>
```

**JDBC配置文件**

```Java
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/mybatis
jdbc.username=root
jdbc.password=JXLZZX79
```
