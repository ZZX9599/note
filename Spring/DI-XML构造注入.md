# DI-XML构造注入

### 构造注入

**实体类**

```java
public class Student {
    private Integer age;
    private String name;

    public Student(Integer age, String name) {
        this.age = age;
        this.name = name;
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
    <bean class="com.zzx.domain.bao03.Student" id="myStudent">
        <!--name表示形参名字 value或者ref用来赋值-->
        <!--前提必须有构造方法-->
        <!--这样就不会执行无参构造了-->
        <constructor-arg name="age" value="20"/>
        <constructor-arg name="name" value="周志雄"/>
    </bean>
</beans>
```

**还可以通过index来为构造注入设值，但是不建议使用**