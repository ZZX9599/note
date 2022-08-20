## Spring事务

**1：什么是事务？**

**2：什么时候使用事务？**

**3：事务一般是在哪里执行？**

**4：JDBC怎么处理事务？Mybatis怎么处理事务？**

**5：以上的方式有什么不足？**

**6：怎么解决不足？**

**7：处理事务，需要怎么做？做什么？**



**问题一：事务是指一组sql语句的集合， 集合中有多条sql语句。可能是insert ， update ，select ，delete， 我们希望这些多个sql语句都能成功，或者都失败， 这些sql语句的执行是一致的，作为一个整体执行。**



**问题二：当我的操作，涉及得到多个表，或者是多个sql语句的insert，update，delete。需要保证
这些语句都是成功才能完成我的功能，或者都失败，保证操作是符合要求的。只要有一个出错了，就回滚**



**问题三：在java代码中写程序，控制事务，此时事务应该放在service类的业务方法上，因为业务方法会调用多个dao方法，执行多个sql语句**



**问题四：jdbc访问数据库，处理事务  Connection conn    conn.commit()   conn.rollback()
mybatis访问数据库，处理事务， SqlSession.commit()   SqlSession.rollback()
hibernate访问数据库，处理事务， Session.commit()   Session.rollback()**



**问题五**

**1)不同的数据库访问技术，处理事务的对象，方法不同， 需要了解不同数据库访问技术使用事务的原理**
**2)掌握多种数据库中事务的处理逻辑。什么时候提交事务，什么时候回顾事务**
**3)处理事务的多种方法**

**总结： 就是多种数据库的访问技术，有不同的事务处理的机制，对象，方法，就是各种技术处理事务的方式不同，如果没有统一的机制，对于开发人员，难度就会很大！**



**问题六：**  

**spring提供一种处理事务的统一模型， 能使用统一步骤，方式完成多种不同数据库访问技术的事务处理。**

**使用spring的事务处理机制，可以完成mybatis访问数据库的事务处理**

**使用spring的事务处理机制，可以完成hibernate访问数据库的事务处理**

**使用spring的事务处理机制，可以完成其他数据库对事务处的理**



**问题七：spring处理事务的模型，使用的步骤都是固定的。把事务使用的信息提供给spring就可以了**

**1：事务内部提交，回滚事务，使用的事务管理器对象，代替你完成commit，rollback，我们只需要对spring提交回滚事务，声明数据库访问技术，spring的事务管理器会自动帮我们完成事务的提交和回滚**

**事务管理器是一个接口和他的众多实现类**
**接口：PlatformTransactionManager ，定义了事务重要方法 commit ，rollback**
**实现类：spring把每一种数据库访问技术对应的事务处理类都创建好了。**
**mybatis访问数据库---spring创建好的是DataSourceTransactionManager**
**hibernate访问数据库----spring创建的是HibernateTransactionManager**



#### 使用Spring的事务的步骤

**第一步：你需要告诉spring 你用是那种数据库的访问技术，怎么告诉spring呢？
声明数据库访问技术对于的事务管理器实现类对象， 在spring的配置文件中使用<bean>声明就可以了
例如，你要使用mybatis访问数据库，你应该在xml配置文件中
<bean id=“xxx" class="...DataSourceTransactionManager"> **



**第二步：你的业务方法需要什么样的事务，需要说明需要事务的类型。**

**说明方法需要的事务：
1）事务的隔离级别：有5个隔离级别常量。这些常量均是以 ISOLATION_开头。即形如 ISOLATION_XXX。
    ➢DEFAULT：采用 DB 默认的事务隔离级别。MySql 的默认为 REPEATABLE_READ； Oracle默认为 READ_COMMITTED。
	➢ READ_UNCOMMITTED：读未提交。未解决任何并发问题。
	➢ READ_COMMITTED：读已提交。解决脏读，存在不可重复读与幻读。
	➢ REPEATABLE_READ：可重复读。解决脏读、不可重复读，存在幻读
	➢ SERIALIZABLE：串行化。不存在并发问题。**



**第三步：配置事务的超时时间，表示一个方法最长的执行时间，如果方法执行时超过了时间，事务就回滚。单位是秒， 整数值， 默认是 -1，由于超时时间的影响因素很多，所以在项目中一般都不配置！一般不管**



**第四步：告诉spring事务的传播行为！七个事务传播行为常量（重要）  PROPAGATION【传播】**

**控制业务方法是不是有事务的， 是什么样的事务的。**

**7个传播行为，表示你的业务方法调用时，事务在方法之间是如何使用的。**

```Java
PROPAGATION_REQUIRED【doSome调用doOther，doOSome有事务的话，就把doOther加入doSome的事务中。若 doSome()方法在调用doOther()方法时没有在事务内执行，则doOther()方法会创建一个事务，最常用的传播行为】
    
PROPAGATION_REQUIRES_NEW【总是新建一个事务，若当前存在事务，就将当前事务挂起，直到新事务执行完毕再执行之前的事务】
    
PROPAGATION_SUPPORTS【指定的方法支持当前事务，但若当前没有事务，也可以以非事务方式执行，例如查询】
以上三个需要掌握的

PROPAGATION_MANDATORY
PROPAGATION_NESTED
PROPAGATION_NEVER
PROPAGATION_NOT_SUPPORTED
```



### Spring提交事务，回滚事务的时机

**1）当你的业务方法，执行成功，没有异常抛出，当方法执行完毕，spring在方法执行后提交事务。事务管理器commit**

**2）当你的业务方法抛出运行时异常或ERROR， spring执行回滚，调用事务管理器的rollback**
**运行时异常的定义： RuntimeException和他的子类都是运行时异常， 例如NullPointException , NumberFormatException**

**3）当你的业务方法抛出非运行时异常， 主要是受查异常时，提交事务**

**受查异常，也叫做编译异常：在你写代码中，必须处理的异常。例如IOException, SQLException**



### 总结Spring的事务以及如何使用Spring的事务

**1.管理事务的是事务管理器【PlatformTransactionManager】和他的实现类**

**2.spring的事务是一个统一模型**

**3.指定要使用的事务管理器实现类，使用<bean>标签，也就是声明事务管理器的实现类**

**4.指定哪些类，哪些方法需要加入事务的功能**

**5.指定方法需要的隔离级别，传播行为，超时**



**你需要告诉spring，你的项目中类信息，方法的名称，方法的事务传播行为**



### Spring框架中提供的事务处理方案

**1.适合中小项目使用的注解方案。**
**spring框架自己用aop实现给业务方法增加事务的功能， 使用@Transactional注解增加事务。**
**@Transactional注解是spring框架自己的注解，放在public方法的上面，表示当前方法具有事务。**
**可以给注解的属性赋值，表示具体的隔离级别，传播行为，异常信息等等**



**@Transactional 的所有可选属性如下所示： **

**➢ propagation：用于设置事务传播属性。该属性类型为 Propagation 枚举**

**默认值为**Propagation.REQUIRED**

**➢ isolation：用于设置事务的隔离级别。该属性类型为 Isolation 枚举，默认值为 Isolation.DEFAULT。**

**➢ readOnly：用于设置该方法对数据库的操作是否是只读的。该属性为 boolean，默认值为 false。如果方法全是读，可以使用true**

**➢ rollbackFor：指定需要回滚的异常类。类型为 Class[]，默认值为空数组。当然，若只有 一个异常类时，可以不使用数组。**



### 实现注解的事务步骤：

**1.需要声明事务管理器对象【框架会根据配置的数据库信息自动提交回滚，开发者只需要对事务管理器操作】**

**<bean id="xx" class="DataSourceTransactionManager">**

```java
<!--使用spring的事务管理器-->
<bean class="org.springframework.jdbc.datasource.DataSourceTransactionManager" id="transactionManager">
    <!--既然是管理事务，就需要知道连接的数据库信息-->
    <!--引入上面的数据源-->
    <property name="dataSource" ref="dataSource"/>
</bean>
```

**2.开启事务注解驱动， 告诉spring框架，我要使用注解的方式管理事务**

**这个时候spring会使用aop机制，创建@Transactional所在的类的代理对象，给方法加入事务的功能。**

```Java
<!--告诉spring开启事务注解驱动，告诉spring框架使用注解来管理驱动，创建代理对象-->
<!--transaction-manager的值，就是事务管理器的id-->
<tx:annotation-driven transaction-manager="transactionManager"/>
```

**spring给业务方法加入事务：
在你的业务方法执行之前，先开启事务，在业务方法之后提交或回滚事务，使用aop的环绕通知**    

**执行过程如下：**

```
 @Around("切面表达式")
		 Object myAround(){
           开启事务，spring给你开启
			  try{
			     buy(1001,10);
				  spring的事务管理器.commit();
			  }catch(Exception e){
             spring的事务管理器.rollback();
			  }
	 	}
```

**3.在你的方法的上面加入@Trancational**

```java
@Transactional(
        //传播行为
        propagation = Propagation.REQUIRED,
        //隔离级别
        isolation = Isolation.DEFAULT,
        //是否只读
        readOnly = false,
        //可能产生的异常
        rollbackFor = {
                NotEnoughException.class,
                NullPointerException.class
        }
)
@Override
public void buy(Integer gid, Integer num)
```



**加入了事务管理器驱动之后，Service对象变成了代理对象！代理对象就是增加了事务切面的对象**

**在运行的时候，框架会首先检查方法抛出的是不是在rollbackFor的属性中，如果在这个列表中，不管是什么异常，一定是要回滚的！就算是IoException也会回滚！如果不在这个列表中，那spring会判断异常是不是RunTimeException，如果是，一定会回滚！**