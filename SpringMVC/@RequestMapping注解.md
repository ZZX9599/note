### @RequestMapping注解

**通过@RequestMapping 注解可以定义处理器对于请求的映射规则。该注解可以注解在方法上，也可以注解在类上，但意义是不同的。value 属性值常以“/”开始**

**@RequestMapping 的 value 属性用于定义所匹配请求的 URI。但对于注解在方法上与类上，其 value 属性所指定的 URI，意义是不同的。**

**一个@Controller 所注解的类中，可以定义多个处理器方法。当然，不同的处理器方法所匹配的 URI 是不同的。这些不同的 URI 被指定在注解于方法之上的@RequestMapping 的 value 属性中。但若这些请求具有相同的 URI 部分，则这些相同的 URI，可以被抽取到注解在类之上的@RequestMapping 的 value 属性中。此时的这个 URI 表示模块的名称。URI 的请求是相对于 Web 的根目录。**

**换个角度说，要访问处理器的指定方法，必须要在方法指定 URI 之前加上处理器类前定义的模块名称**

**例如：**

**销售模块的增删改查    在销售的控制类上面就应该加上@RequestMapping("sale")**

**对应的方法写上@RequestMapping("add/del/modify/query")**

**总之就是类上面的是公共的！**



### @RequestMapping对请求提交方式的定义

**对于@RequestMapping，其有一个属性 method，用于对被注解方法所处理请求的提交方式进行限制，即只有满足该 method 属性指定的提交方式的请求，才会执行该被注解方法。 Method 属性的取值为 RequestMethod 枚举常量。常用的为 RequestMethod.GET 与 RequestMethod.POST，分别表示提交方式的匹配规则为 GET 与 POST 提交。【如果不指定提交方式，那么无论get还是post，对会执行对应的方法】**

```Java
//指定get请求
@RequestMapping(value = "/some.do",method = RequestMethod.GET)
```

```java
//指定post请求
@RequestMapping(value = "/some.do",method = RequestMethod.POST)
```