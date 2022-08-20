### JSON 字符串转换为 Java 对象



**我们可以使用 JSON.parseObject() 将 JSON 字符串转换为 Java 对象。**

**注意反序列化时为对象时，必须要有默认无参的构造函数，否则会报异常:**



```java
@Test
public void test02(){

    Student student = Student.builder()
            .name("张三")
            .age(20)
            .address("四川广安")
            .date(new Date())
            .build();

    String studentJson = JSON.toJSONString(student);
    Student student1 = JSON.parseObject(studentJson, Student.class);
    System.out.println(student1);
}
```