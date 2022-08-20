### 使用format配置日期格式化





**使用：@JSONField(format = "yyyy年MM月dd日 HH:mm:ss")**

```java
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String name;
    private Integer age;
    private String address;
    @JSONField(format = "yyyy年MM月dd日 HH:mm:ss")
    private Date date;
}
```



```java
@Test
public void test01(){
    Student student = Student.builder()
            .name("张三")
            .age(20)
            .address("四川广安")
            .date(new Date())
            .build();

    String studentJson = JSON.toJSONString(student);
    System.out.println(studentJson);
}
```





**结果：{"address":"四川广安","age":20,"date":"2022年07月14日 18:41:18","name":"张三"}**