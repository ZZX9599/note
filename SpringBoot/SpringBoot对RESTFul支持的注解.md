### SpringBoot对RESTFul支持的注解



**Spring Boot 开发 RESTful 主要是几个注解实现**



**1：@PathVariable **

**获取 url 中的数据 该注解是实现 RESTFul 最主要的一个注解 **

**http://localhost:8080/myboot/student/1001**

**这个时候使用getParam()拿不到参数**

**需要使用注解**



**2： @GetMapping** 

**支持的get请求方式，  等同于 @RequestMapping( method=RequestMethod.GET)**

```java
@RequestMapping(
    method = {RequestMethod.GET}
)
public @interface GetMapping {
```



**3：@PostMapping**

**支持post请求方式 ，等同于 @RequestMapping( method=RequestMethod.POST)**

```java
@RequestMapping(
    method = {RequestMethod.POST}
)
public @interface PostMapping {
```



**4：@PutMapping**

**支持put请求方式【更新】  等同于 @RequestMapping( method=RequestMethod.PUT)**

```java
@RequestMapping(
    method = {RequestMethod.PUT}
)
public @interface PutMapping {
```



**5：@DeleteMapping**

**支持delete请求方式，  等同于 @RequestMapping( method=RequestMethod.DELETE)**

```java
@RequestMapping(
    method = {RequestMethod.DELETE}
)
public @interface DeleteMapping {
```



**6：@RestController**

**符合注解， 是@Controller 和@ResponseBody组合。**

**在类的上面使用@RestController ， 表示当前类者的所有方法都加入了 @ResponseBody**

```java
@Controller
@ResponseBody
public @interface RestController {
    @AliasFor(
        annotation = Controller.class
    )
    String value() default "";
}
```



### 代码测试

```java
package com.zzx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 */
@RestController
public class MyRestController {
    /**
     * 加入了@RestController  表示都加入了@ResponseBody注解，返回都是String
     * @PathVariable 路径变量，用来获取url里面的值 属性value:路径变量名
     * @PathVariable 位置在控制器方法形参的前面
     * http://localhost:8080/myboot/student/1001
     * stuId就是1001
     */
    @GetMapping("/student/{stuId}")
    public String queryStudent( @PathVariable(value = "stuId")Integer studentId){
        //表示把{stuId}的值传给studentId
        return "学生id:"+studentId;
    }


    /**
     * http://localhost:8080/myboot/student/周志雄/20
     * @param name
     * @param age
     * @return
     */
    @PostMapping("/student/{name}/{age}")
    public String addStudent(@PathVariable("name")String name,@PathVariable("age")Integer age){
        return "添加学生姓名:"+name+"年龄:"+age;
    }
}
```



### 把原生的post请求，转为put,delete请求

**在SpringMVC中 有一个过滤器， 支持post请求转为put ,delete**



**过滤器： org.springframework.web.filter.HiddenHttpMethodFilter**

**作用： 把请求中的post请求转为 put ， delete**



**实现步骤：**

**在springmvc中需要在xml文件中配置**

**在springboot中使用，已经配置好了，在配置文件中开启即可**

**application.properties(yml) : 开启使用 HiddenHttpMethodFilter 过滤器**

**在请求页面中，包含 _method参数， 他的值是 put， delete  ，  发起这个请求使用的post方式**



```java
@PutMapping("/student/test")
public String test(){
    return "put请求";
}
```



```html
<form action="student/test" method="post">
    <input type="hidden" name="_method" value="put">
    <input type="submit" value="测试请求方式"/>
</form>
```