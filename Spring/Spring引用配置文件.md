## Spring配置文件中引用其他的配置文件

**配置文件之中使用这个标签**

```Java
<context:property-placeholder location="classpath:my.properties"/>
```

**定义my.properties**

```java
name=周志雄
age=20
```

**使用配置文件的值，使用${key}来获取对应的值**

```Java
@Value("${age}")
private Integer age;
@Value("${name}")
private String name;
```