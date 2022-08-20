### SpringBoot整合Redis

**Redis ： 一个NoSQL数据库，常用作缓存使用 （cache）**

**Redis的数据类型： string，hash  ,set  ,zset , list**



**Redis是一个中间件： 是一个独立的服务器。**

**java中著名的客户端： Jedis ，  lettuce ， Redisson**



**Spring,SpringBoot中有 RedisTemplate 和 StringRedisTemplate ，处理和redis交互**  



**RedisTemplate 使用的  lettuce 客户端库**

```xml
<!--redis起步依赖： 直接在项目中使用RedisTemplate(StringRedisTemplate)-->
<dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
data-redis使用的lettuce客户端库

在程序中使用RedisTemplate类的方法操作redis数据， 实际就是调用的lettuce 客户端的中的方法
```



## 对比 StringRedisTemplate 和 RedisTemplate 

**StringRedisTemplate ： 把k，v 都是作为String处理， 使用的是String的序列化 ， 可读性好，推荐使用，通俗的说，key和value都是String**

**RedisTemplate ： 把k，v 经过了序列化存到redis。 k，v 是序列化的内容， 不能直接识别，不容易看懂**

**默认使用的jdk序列化，也就是说key和value都是序列化的结果，看不懂！可以修改为前提的序列化**



**序列化：把对象转化为可传输的字节序列过程称为序列化。**

**反序列化：把字节序列还原为对象的过程称为反序列化。**



**为什么需要序列化：方便在网络中传输**

**序列化最终的目的是为了对象可以跨平台存储，和进行网络传输。而我们进行跨平台存储和网络传输的方式就是IO，而我们的IO支持的数据格式就是字节数组。我们必须在把对象转成字节数组的时候就制定一种规则（序列化），那么我们从IO流里面读出数据的时候再以这种规则把对象还原回来（反序列化）**



**什么情况下需要序列化？**

**通过上面我想你已经知道了凡是需要进行“跨平台存储”和”网络传输”的数据，都需要进行序列化。**

**本质上存储和网络传输 都需要经过 把一个对象状态保存成一种跨平台识别的字节格式，然后其他的平台才可以通过字节信息解析还原对象信息。**



**序列化的方式**

**序列化只是一种拆装组装对象的规则，那么这种规则肯定也可能有多种多样，比如现在常见的序列化方式有：**

**JDK（不支持跨语言，最方便，性能差，小型使用）、JSON、XML、Hessian【只支持java语言】、Kryo（不支持跨语言，只支持java语言，速度快）、Thrift、Protofbuff、**



**转化成json格式：**

**Student( name=zs, age=20)   ----  { "name":"zs", "age":20 }**



**java的序列化： 把java对象转为byte[], 二进制数据**



**json序列化：json序列化功能将对象转换为 JSON 格式或从 JSON 格式转换对象。例如把一个Student对象转换为JSON字符串{"name":"李四", "age":29} )**

**json反序列化：(将JSON字符串 {"name":"李四", "age":29} 转换为Student对象)**





**设置key或者value的序列化方式**

```java
// 使用RedisTemplate ，在存取值之前，设置序列化
// 设置 key 使用String的序列化
redisTemplate.setKeySerializer( new StringRedisSerializer());

// 设置 value 的序列化
redisTemplate.setValueSerializer( new StringRedisSerializer());

redisTemplate.opsForValue().set(k,v);
```



**也可以直接使用StringRedisTemplate对象，默认是String序列化！**



### 示例代码

```java
@RestController
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 添加数据到redis
     * 需要注入RedisTemplate
     * RedisTemplate的两种泛型【要么不写】
     * RedisTemplate<String,String>
     * RedisTemplate<Object,Object>
     * 框架创建的对象就叫 redisTemplate
     * @return
     */
    @PostMapping("/redis/addDate")
    public String addDate(){
        //操作String类型的数据，要先获得ValueOperations对象
        //使用redisTemplate
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //设置字符串
        valueOperations.set("name","周志雄");
        return "往redis添加了数据";
    }

    /**
     * 获取redis数据
     * @return
     */
    @GetMapping("/redis/getDate")
    public String getDate(){
        ValueOperations valueOperations=redisTemplate.opsForValue();
        String value=(String) valueOperations.get("name");
        return "获取的数据是:"+value;
    }

    
    ===================================使用StringRedisTemplate============================
    @PostMapping("/redis/{key}/{value}")
    public String addStringDate(@PathVariable String key, @PathVariable String value){
        //操作String类型的数据，要先获得ValueOperations对象
        //使用stringRedisTemplate
        ValueOperations valueOperations = stringRedisTemplate.opsForValue();
        //设置字符串
        valueOperations.set(key,value);
        return "===往redis添加了数据===";
    }

    @GetMapping("/redis/{key}")
    public String getStringDate(@PathVariable String key){
        ValueOperations valueOperations=stringRedisTemplate.opsForValue();
        String value=(String) valueOperations.get(key);
        return "===获取的数据是:"+value+"===";
    }
    
    @PostMapping("/redis/addStr")
    public String addString(String key,String value){
        //使用RedisTemplate
        //设置key的序列化方式
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //设置value的序列化方式
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        
        redisTemplate.opsForValue().set(key,value);
        return "自定义序列化";
    }
}
```

### 在真实项目中，一般不用jdk序列化，都自定义序列化方式！

### **RedisTemplate更加通用，支持设置多种序列化**



### RedisTemplate一般用来储存对象，StringRedisTemplate一般用来处理字符串

