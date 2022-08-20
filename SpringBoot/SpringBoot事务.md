### SpringBoot事务

**Spring框架中的事务：**

**1） 管理事务的对象： 事务管理器TransactionManager（接口， 接口有很多的实现类）**

​       **例如：使用Jdbc或mybatis访问数据库，使用的事务管理器：DataSourceTransactionManager**

**2 ) 声明式事务：  在xml配置文件或者使用注解说明事务控制的内容**

​     **控制事务： 隔离级别，传播行为， 超时时间**

**3）事务处理方式：**

​      **1） Spring框架中的@Transactional**

​      **2)    aspectj框架可以在xml配置文件中，声明事务控制的内容**



**SpringBoot中使用事务： 上面的两种方式都可以。**

**1）在业务方法的上面加入@Transactional ,  加入注解后，方法有事务功能了。**

**2）明确的在主启动类的上面 ，加入@EnableTransactionManager**



```java
@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper mapper;

    /**
     * 默认的隔离级别ISOLATION-DEFAULT , 传播行为REQUIRED , 超时时间 -1
     * @param student
     * @return
     */
    @Transactional(rollbackFor = {RuntimeException.class})
    @Override
    public int addStudent(Student student) {
        System.out.println("业务方法add执行");
        int rows=mapper.insert(student);
        System.out.println("执行了sql语句");
        //抛出运行异常，回滚事务
        return rows;
    }
}
```



```java
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = {"com.zzx.dao"})
public class Ch16SpringbootTranscationalApplication {

    public static void main(String[] args) {
        SpringApplication.run(Ch16SpringbootTranscationalApplication.class, args);
    }

}
```
