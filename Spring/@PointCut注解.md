## @PointCut注解

**当较多的通知增强方法使用相同的 execution 切入点表达式时，编写、维护均较为麻烦。 AspectJ 提供了@Pointcut 注解，用于定义 execution 切入点表达式。 其用法是，将@Pointcut 注解在一个方法之上，以后所有的 execution 的 value 属性值均 可使用该方法名作为切入点。代表的就是@Pointcut 定义的切入点。这个使用@Pointcut 注解 的方法一般使用 private 的标识方法，即没有实际作用的方法。**

**因为@PointCut就是一堆连接点的集合，连接点就是单独的目标方法**



**特点：当@PointCut定义在一个方法的上面，这个方法的名称就是切入点表达式的别名**

**其他的通知中，value就可以使用这个方法的名称，代替切入点表达式了**

**使用如下：**

```Java
@Aspect
public class MyAspect {
    @After(value = "myPt()))")
    public void myAfter(){
        System.out.println("执行最终通知");
    }

    @Before(value = "myPt())")
    public void myBefore(){
        System.out.println("执行前置通知");
    }

    @Pointcut(value = "execution(* *..SomeServiceImpl.doThird(..))")
    private void myPt(){
        //方法不需要任何的代码
    }
}
```