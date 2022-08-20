### java对象转json

```java
package com.zzx.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: ZZX
 * @Date: 2022/7/14 18:11
 */

@Data
@Builder
public class Student {
    private String name;
    private Integer age;
    private String address;
}
```



```java
@Test
public void test01(){
    Student student = Student.builder()
            .name("张三")
            .age(20)
            .address("四川广安").build();

    String studentJson = JSON.toJSONString(student);
    System.out.println(studentJson);
}
```

**结果：{"address":"四川广安","age":20,"name":"张三"}**



```java
@Test
public void test02(){
    List<Student> list=new ArrayList<>();
    list.add(new Student("张三",20,"北京"));
    list.add(new Student("李四",21,"上海"));
    String s = JSON.toJSONString(list);
    System.out.println(s);
}
```

**[{"address":"北京","age":20,"name":"张三"},{"address":"上海","age":21,"name":"李四"}]**



**可以看到默认情况下，得到的json数据就是属性名称**

**如果想让得到的结果的字段和对象的属性不一致，那么需要使用@JSONField(name=?)来指定**

**还可以指定的属性不序列化：@JSONField(serialize = false)**