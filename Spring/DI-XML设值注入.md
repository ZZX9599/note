# **DI-XML**



### **set注入，也叫设值注入**

**1）设值注入简单类型**

**实体类如下**

```java
public class Student {
    private String name;
    private Integer age;
    private String address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
```

**XML配置文件如下：**

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--声明bean对象-->
    <bean class="com.zzx.domain.bao01.Student" id="myStudent">
        <!--通过set注入，也叫设值注入，采用xml配置文件的赋值方式，spring调用set方法进行赋值-->
        <!--在spring中规定，java基本数据类型，包装类，字符串都是简单类型，设值注入采用value-->
        <!--在spring中规定，java的引用数据类型还有对象，设值注入采用ref-->
        <property name="address" value="四川广安"/>
        <property name="name" value="周志雄"/>
        <property name="age" value="20"/>
    </bean>
</beans>
```

**执行流程：先执行无参构造创建对象，然后调用set方法给属性赋值**

**如果在XML配置文件中写了set注入，但是实体类没有写set方法，会报错**

**注意：如果有set方法，但是没赋值，不会报错，spring只是执行set方法而已，没赋值并不影响**

**注意：实体类如果没有address属性，但是写了setAddress方法，XML配置文件写了**

**<property name="address" value="北京"/>这样并不会报错！因为spring只找set方法**

**因此我们可以在创建对象的时候就能赋值【不管是不是自己写的，只要有set方法】**

**set注入的时候，就算是int类型，value赋值的时候也必须写双引号**



**2）设值注入引用类型**

**实体类**

```Java
public class School {
    private String name;
    private String address;
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}

public class Student {
    private String name;
    private Integer age;
    private String address;
    private School school;
    public void setSchool(School school) {
        this.school = school;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
```

**配置文件**

```Java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--声明bean对象-->
    <bean class="com.zzx.domain.bao02.Student" id="myStudent">
        <!--通过set注入，也叫设值注入，采用xml配置文件的赋值方式，spring调用set方法进行赋值-->
        <!--在spring中规定，java基本数据类型，包装类，字符串都是简单类型，设值注入采用value-->
        <!--在spring中规定，java的引用数据类型还有对象，设值注入采用ref-->
        <property name="address" value="四川广安"/>
        <property name="name" value="周志雄"/>
        <property name="age" value="20"/>
        <property name="school" ref="mySchool"/>
    </bean>
    <bean class="com.zzx.domain.bao02.School" id="mySchool">
        <property name="name" value="内江师范学院"/>
        <property name="address" value="四川内江"/>
    </bean>
</beans>
```

