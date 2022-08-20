## @Before

**在类上加入@Before**

**切面方法的定义：公共方法，没有返回值，方法名自定义，参数可有可无【参数只有固定的几种类型】**

**目标类**

```Java
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSome(String name,Integer age) {
        //给doSome方法执行之前打印时间
        System.out.println("==doSome执行==");
    }
}
```

**切面类**

```Java
@Aspect
public class MyAspect {
    @Before(value = "execution(public void com.zzx.bao01.impl.SomeServiceImpl.doSome(String,Integer))")
    /**
     *  @Before有一个value属性 value填的是切入点表达式
     *  在目标方法执行前执行，不会改变目标方法的执行结果
     *  不会影响目标方法执行
     */
    public void myBefore(){
        System.out.println("切面功能：在目标方法执行前输出时间:"+new Date());
    }
}
```

**配置文件：主要是写自动代理生成器**

```Java
<!--把对象交给spring容器-->
<bean class="com.zzx.bao01.impl.SomeServiceImpl" id="someService"/>
<!--声明切面类-->
<bean class="com.zzx.bao01.MyAspect" id="myAspect"/>
<!--声明AspectJ的自动代理生成器，是在内存中实现的，创建代理对象实际上是修改目标对象的内存结构-->
<!--aop:aspectj-autoproxy会把spring容器中的所有目标对象，按照切入点表达式和Advice生成对应的代理对象-->
<aop:aspectj-autoproxy/>
```

**注意：在配置文件<aop:aspectj-autoproxy/>没有执行的时候，这个时候容器里面就只有两个普通的类，并没有切面的功能，执行<aop:aspectj-autoproxy/>的时候，会依次去找@Aspect注解，然后根据对应的类的方法的注解以及切入点表达式的信息对目标进行改造，然后生成代理对象存放在spring容器，如果有接口，使用的是JDK动态代理，没有接口，使用的是CGLB，如果拥有接口还是想使用CGLB  需要这样配置**

**<aop:aspectj-autoproxy  proxy-target-class="true"/>**



**测试类：拿到的对象实际上是代理对象，自动代理生成器在内存中已经修改了内存结构**

```Java
public class MyTest {
    @Test
    public void test01(){
        String config="applicationContext.xml";
        ApplicationContext ctx=new ClassPathXmlApplicationContext(config);
        SomeService service=(SomeService) ctx.getBean("someService");
        System.out.println(service.getClass().getName());
        //这个对象的名字 com.sun.proxy.$Proxy==》表明仍然是动态代理
        service.doSome("周志雄",20);
    }
}
```