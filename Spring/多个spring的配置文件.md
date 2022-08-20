## 使用多个spring的配置文件

#### 优点：

##### **1：避免多人竞争带来的冲突**

**2：每个文件都比较小，效率高**



#### 多文件的分配方式

**1：假如项目有多个模块，一般一个模块一个配置文件，方便管理，每个人专门在独自的模块用自己的配置文件**

**就不容易和他人的配置文件起冲突**

**2：按照功能分配：数据库相关的配置在一个文件，做事务的在一个配置文件，做service在一个配置文件**



**主配置文件**

```Java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--表示主配置文件-->
    <!--用来包含其他的配置文件，一般不定义对象-->
    <!--加载文件列表-->
    <import resource="classpath:bao05/spring-school.xml"/>
    <import resource="classpath:bao05/spring-student.xml"/>
        
    <!--使用通配符-->
    <!--但是注意别把主配置文件加进去了，不然会死循环造成出错-->
    <import resource="classpath:bao05/spring-*.xml"/>
</beans>
```

**配置文件-学生**

```Java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--配置学生配置文件-->
    <bean class="com.zzx.domain.bao05.Student" id="myStudent" autowire="byType">
        <property name="address" value="广东深圳"/>
        <property name="name" value="蒋雪丽"/>
        <property name="age" value="19"/>
    </bean>
</beans>
```

**配置文件-学校**

```Java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--配置学校配置文件-->
    <bean class="com.zzx.domain.bao05.School" id="mySchool">
        <property name="name" value="北京大学"/>
        <property name="address" value="北京"/>
    </bean>
</beans>
```