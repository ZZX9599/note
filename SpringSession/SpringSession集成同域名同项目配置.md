### SpringSession集成同域名同项目配置

**1：在pom.xml文件中，添加Spring Session相关的依赖**

```xml
<!-- Spring session redis 依赖start -->
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
            <version>1.3.1.RELEASE</version>
        </dependency>
        <!-- Spring session redis 依赖end -->
        <!-- spring web模块依赖 start -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>4.3.16.RELEASE</version>
        </dependency>
<!-- spring web模块依赖end -->

```



**2：在web.xml文件中配置springSessionRepositoryFilter过滤器**

```xml
<filter>
    <filter-name>springSessionRepositoryFilter</filter-name>
	<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>

<filter-mapping>
    <filter-name>springSessionRepositoryFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

**添加filter是固定的**



**3：在web.xml文件中加载Spring配置文件**

```xml
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:applicationContext.xml</param-value>
</context-param>

<listener>
  <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>

```



**4：创建applicationContext-session.xml**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!--定义RedisHttpSessionConfiguration类-->
    <bean id="redisHttpSessionConfiguration"
          class="org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration">
        <!--配置Session最大生命周期：默认30分钟-->
        <property name="maxInactiveIntervalInSeconds" value="1800"></property>
    </bean>

    <!-- 配置jedis连接工厂，用于连接redis -->
    <bean id="jedisConnectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory">
        <property name="hostName" value="192.168.220.135"/>
        <property name="port" value="6379"/>
    </bean>

    <!--注解驱动-->
    <!--SpringSession用到了Spring的注解，这个标签实际上还包括了组件扫描器-->
    <context:annotation-config/>
</beans>
```



**5：创建applicationContext**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--引入配置-->
    <import resource="classpath:springsession.xml"/>
</beans>
```



**这样就实现了SpringSession对同域名的同实现Session共享**

**注意：仅仅只对同域名的同项目的Session共享有作用**

**很适合Nginx下的集群模式**