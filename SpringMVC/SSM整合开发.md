### SSM整合开发

**容器一：SpringMVC的容器【存放Controller对象】**

**容器二：Spring的容器【存放Service,Dao,Utils等】**

**Controller访问Service,Service访问Dao**

**Service和Dao在一个容器，直接访问**

**难点：Controller怎么访问Service？**

**SpringMVC的容器是Spring容器的子容器，类似继承，但是不是继承，子容器可以访问父容器的对象**



**pom文件**

```Java
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
  http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.zzx</groupId>
  <artifactId>ch07-ssm</artifactId>
  <version>1.0.0</version>
  <packaging>war</packaging>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <!--依赖-->
  <dependencies>
    <!-- 单元测试 -->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    <!-- servlet -->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>3.1.0</version>
      <scope>provided</scope>
    </dependency>
    <!-- jsp 依赖 -->
    <dependency>
      <groupId>javax.servlet.jsp</groupId>
      <artifactId>jsp-api</artifactId>
      <version>2.2.1-b03</version>
      <scope>provided</scope>
    </dependency>
    <!-- springmvc -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <!-- 事务的 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <!-- aspectj依赖，可以做事务配置-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-aspects</artifactId>
      <version>5.2.5.RELEASE</version>
    </dependency>
    <!-- jackson -->
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-core</artifactId>
      <version>2.9.0</version>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.9.0</version>
    </dependency>
    <!-- mybatis和spring整合 -->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis-spring</artifactId>
      <version>1.3.1</version>
    </dependency>
    <!--mybatis-->
    <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.1</version>
    </dependency>
    <!-- mysql驱动 -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.9</version>
    </dependency>
    <!-- 阿里巴巴druid连接池 -->
    <dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
      <version>1.1.12</version>
    </dependency>
  </dependencies>

  <build>
    <resources>
      <resource>
        <directory>src/main/java</directory><!--所在的目录-->
        <includes><!--包括目录下的.properties,.xml 文件都会扫描到-->
          <include>**/*.properties</include>
          <include>**/*.xml</include>
        </includes>
        <filtering>false</filtering>
      </resource>
    </resources>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.1</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```



**Mybatis主配置文件**

```Java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!--settings:控制全局行为-->

    <!--别名-->
    <typeAliases>
        <package name="com.zzx.domain"/>
    </typeAliases>

    <!--sql映射文件的位置-->
    <mappers>
        <package name="com.zzx.dao"/>
    </mappers>
</configuration>
```



**Spring主配置文件**

```Java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context" xmlns:tx="http://www.springframework.org/schema/tx"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd http://www.springframework.org/schema/aop https://www.springframework.org/schema/aop/spring-aop.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <!--配置文件引入-->
    <context:property-placeholder location="classpath:jdbc.properties"/>

    <!--声明数据源-->
    <bean class="com.alibaba.druid.pool.DruidDataSource" id="dataSource"
                    init-method="init" destroy-method="close">
        <property name="driverClassName" value="${jdbc.driver}"/>
        <property name="url" value="${jdbc.url}"/>
        <property name="username" value="${jdbc.username}"/>
        <property name="password" value="${jdbc.password}"/>
    </bean>

    <!--声明SqlSessionFactoryBean来创建SqlSessionFactory-->
    <bean class="org.mybatis.spring.SqlSessionFactoryBean" id="sqlSessionFactory">
        <property name="dataSource" ref="dataSource"/>
        <property name="configLocation" value="classpath:mybatis.xml"/>
    </bean>

    <!--扫描basePackage.创建dao对象-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
        <property name="basePackage" value="com.zzx.dao"/>
    </bean>

    <!--创建Service-->
    <context:component-scan base-package="com.zzx.service"/>

    <!--声明事务管理器对象-->
    <bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager" id="transactionManager">
        <!--数据库的信息-->
        <property name="dataSource" ref="dataSource"/>
    </bean>

    <!--声明业务方法的事务属性，隔离级别，传播行为-->
    <!--id:自定义名称：来表示tx:advice和</tx:advice>之间的配置内容，配置事务通知-->
    <!--transaction-manage:事务管理器对象的id-->
    <tx:advice id="myAdvice" transaction-manager="transactionManager">
        <!--tx:代表事务  attributes:代表属性，也就是配置事务属性-->

    </tx:advice>

    <!--配置切面的执行时间和位置-->
    <!--配置切入点表达式，指定那些包的类，要使用事务-->
    <!--id[切入点表达式名称，唯一值]-->
    <!--expression[切入点表达式，指定哪些使用事务，aspectj会创建代理对象-->
    <!--弥补上面advice里面只写了方法名，所以在这里的切入点表达式里面补充包名-->
    <aop:config>
        <!--配置切入点-->
        <aop:pointcut id="servicePt" expression="execution(* *..service..*.*(..))"/>
        <!--配置增强器：关联advice和pointcut-->
        <aop:advisor advice-ref="myAdvice" pointcut-ref="servicePt"/>
    </aop:config>
</beans>
```



**SpringMVC配置文件**

```Java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!--组件扫描器-->
    <context:component-scan base-package="com.zzx.controller"/>

    <!--视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!--注解驱动,ajax返回json,处理静态文件冲突都需要-->
    <mvc:annotation-driven/>
</beans>
```