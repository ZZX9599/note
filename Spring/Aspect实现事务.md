### Aspect实现事务

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
<bean id="xx" class="DataSourceTransactionManager">
```



**3) 声明方法需要的事务类型（配置方法的事务属性【隔离级别，传播行为，超时】**



**4) 配置aop：指定哪些哪类要创建代理**

**使用 AspectJ 的 AOP 配置管理事务**

**使用 XML 配置事务代理的不足，每个目标类都需要配置事务代理。当目标类较多，配置文件会变得非常臃肿。**

