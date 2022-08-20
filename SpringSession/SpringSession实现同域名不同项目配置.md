### SpringSession实现同域名不同项目配置

**其余一样，只是需要设置以下Session的存放位置为域名根路径**

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
        <!--注入Cookie序列化规则对象-->
        <property name="cookieSerializer" ref="defaultCookieSerializer"></property>

    </bean>

    <!--序列化规则对象，用于改变Cookie的存放行为-->
    <bean id="defaultCookieSerializer" class="org.springframework.session.web.http.DefaultCookieSerializer">
        <!--指定springsession的sessionId存放在域名的根路径，用于实现同域名不同项目的session共享-->
        <property name="cookiePath" value="/"></property>
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

