# **第一个spring程序**



### XML配置文件：

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--xsd:表示约束文件的扩展名-->
    <!--告诉spring创建bean对象-->
    <!--注意：class不能是接口！因为是反射创建的-->
    <bean class="com.zzx.service.impl.SomeServiceImpl" id="myService"/>
    <!--spring把创建的对象放进了map-->
    <!-- springMap.put(myService,new SomeServiceImpl()) -->
</beans>
```



### 测试类：

```java
public class MyTest {
    @Test
    public void myTest01() {
        //配置文件
        String config="application.xml";
        //ApplicationContext表示容器
        //表示从类路径下【target/classes】开始加载spring配置文件
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        //获取对象
        SomeService service=(SomeService) ctx.getBean("myService");
        service.doSome();
    }
}
```



### **Spring创建对象的时机**

**当创建Application的对象的时候，读取配置文件，读到bean的时候创建对象**

**所以在创建容器的时候，会创建所有的对象【会调用无参构造】**



### 什么类放入spring容器

**一般我们只把controller,service,dao,utils等放入容器，一般不把domain放进去**

