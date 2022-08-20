### RestTemplate



**接口：**

```java
package com.zzx.controller;

import com.zzx.domain.Student;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ZZX
 * @Date: 2022/6/22 15:25
 */

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/get")
    public String get(String name){
        return name;
    }

    /**
     * json提交
     */
    @PostMapping("/post01")
    public String post01(@RequestBody Student student){
        return student.toString();
    }

    
    /**
     * 表单提交
     */
    @PostMapping("/post02")
    public String post02(Student student){
        return student.toString();
    }
}
```



**测试：**

```java
package com.zzx;

import com.zzx.domain.Student;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class RestTemplateApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void testRestTemplate(){
        //创建对象
        RestTemplate restTemplate=new RestTemplate();

        //路径
        String address="http://www.baidu.com";

        //测试方法【返回的类型自己指定】
        String result = restTemplate.getForObject(address, String.class);

        //返回HTML代码
        System.out.println(result);
    }

    @Test
    public void testGetForObject(){
        RestTemplate restTemplate = new RestTemplate();

        String url="http://localhost:8080/test/get?name=zzx";

        String result = restTemplate.getForObject(url,String.class);

        System.out.println(result);
    }


    @Test
    public void testGetForEntity(){
        RestTemplate restTemplate = new RestTemplate();

        String url="http://localhost:8080/test/get?name=zzx";

        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);

        System.out.println(forEntity);
    }

    @Test
    public void testPost01(){
        RestTemplate restTemplate = new RestTemplate();

        Student student=new Student("zzx",20);

        String url="http://localhost:8080/test/post01";

        //默认是json参数，采用jackson

        String result = restTemplate.postForObject(url, student, String.class);

        System.out.println(result);

    }

    @Test
    public void testPost02(){
        RestTemplate restTemplate = new RestTemplate();

        String url="http://localhost:8080/test/post02";

        //构建表单参数

        LinkedMultiValueMap<Object, Object> linkedMultiValueMap = new LinkedMultiValueMap<>();

        linkedMultiValueMap.add("name","zzx");

        linkedMultiValueMap.add("age",20);

        String result = restTemplate.postForObject(url, linkedMultiValueMap, String.class);

        System.out.println(result);

    }
}

```

