### AspectJ实现事务

**这个方式适合大型项目，有很多的类，方法，需要大量的配置事务，使用aspectj框架功能，在spring配置文件中
声明类，方法需要的事务。这种方式业务方法和事务配置完全分离**



**实现步骤： 都是在xml配置文件中实现**

**1)要使用的是aspectj框架，需要加入依赖**

```Java
<dependency>
	<groupId>org.springframework</groupId>
	<artifactId>spring-aspects</artifactId>
	<version>5.2.5.RELEASE</version>
</dependency>
```

**2）声明事务管理器对象**
	 

```Java
<!--声明事务管理器对象-->
<bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager" id="transactionManager">
    <!--数据库的信息-->
    <property name="dataSource" ref="dataSource"/>
</bean>
```



**3) 声明方法需要的事务类型（配置方法的事务属性【隔离级别，传播行为，超时】**

```Java
<!--声明业务方法的事务属性，隔离级别，传播行为-->
<!--id:自定义名称：来表示tx:advice和</tx:advice>之间的配置内容，配置事务通知-->
<!--transaction-manage:事务管理器对象的id-->
<tx:advice id="myAdvice" transaction-manager="transactionManager">
    <!--tx:代表事务  attributes:代表属性，也就是配置事务属性-->
    <tx:attributes>
        <!--需要配置事务的方法，可以有多个-->
        <!--name:写法1【完整的方法名，不带有包和类名】 2【可以使用通配符*】-->
        <!--propagation:传播行为-->
        <!--isolation:隔离级别-->
        <!--read-only:是否只读-->
        <!--rollback-for:出现异常回滚，全限定类名-->
        <tx:method name="buy" propagation="REQUIRED" isolation="DEFAULT" read-only="false"
rollback-for ="com.zzx.exception.NotEnoughException,com.zzx.exception.NotEnoughException"/>

        <!--也可以使用通配符-->
        <!--指定添加方法-->
        <tx:method name="add*" propagation="REQUIRES_NEW"/>
        <!--修改方法-->
        <tx:method name="modify*" propagation="REQUIRES_NEW"/>
        <!--查询方法-->
        <tx:method name="search*" propagation="SUPPORTS" read-only="true"/>
    </tx:attributes>
</tx:advice>
```

**4) 配置aop：指定哪些哪类要创建代理**

```java
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
```

**思考为什么上面配置了方法，这里还要配置呢？因为上面直写了方法名，没有写包名，所以说是找不到的**

**总结：先配置一个事务管理器DataSourceTransactionManager，在事务管理器里面引用数据源，然后在tx:advice中配置你的方法具有什么属性，什么传播行为，隔离级别，异常，是否只读等，最后在aop:config里面指定切入点表达式，并使用aop:advisor把advice和aop连接起来，使其找到相应的位置，执行上面tx:advice里面的事务设置**



**使用 AspectJ 的 AOP 配置管理事务**

**使用 XML 配置事务代理的不足，每个目标类都需要配置事务代理。当目标类较多，配置文件会变得非常臃肿。**

