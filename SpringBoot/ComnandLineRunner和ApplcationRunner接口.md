### ComnandLineRunner和ApplcationRunner接口

开发中可能会有这样的情景。需要在容器启动后执行一些内容。比如读取配置文件，数据库连接之类的。SpringBoot 给我们提供了两个接口来帮助我们实现这种需求。这两个接口分别为 CommandLineRunner 和 ApplicationRunner。【**他们的执行时机为容器启动完成的时候**】。 这两个接口中有一个 run 方法，我们只需要实现这个方法即可。这两个接口的不同之处 在于： ApplicationRunner 中 run 方 法 的 参 数 为 ApplicationArguments ， 而 CommandLineRunner 接口中 run 方法的参数为 String 数组



```java
@FunctionalInterface
public interface CommandLineRunner {
    void run(String... args) throws Exception;
}

@FunctionalInterface
public interface ApplicationRunner {
    void run(ApplicationArguments args) throws Exception;
}

```



```Java
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Resource
    private HelloService service;
    public static void main(String[] args) {
        System.out.println("1:创建容器");
        ApplicationContext ctx=SpringApplication.run(Application.class,args);
        System.out.println("2:创建容器之后");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("3:创建好容器之后执行的方法");
        //在容器创建好后执行的方法，可以读取配置文件，连接数据库等
        service.say();
    }
}

输出结果的顺序：   1，3，2
```

