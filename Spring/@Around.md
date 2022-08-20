## @Around

**在目标方法执行之前之后执行。被注解为环绕增强的方法必须要有返回值，Object 类型。并且方法可以包含一个 ProceedingJoinPoint 类型的参数。接口 ProceedingJoinPoint 其有一个 proceed()方法，用于执行目标方法。若目标方法有返回值，则该方法的返回值就是目标方法 的返回值。最后，环绕增强方法将其返回值返回。该增强方法实际是拦截了目标方法的执行**

**环绕通知方法定义**
**public 必须要返回值【推荐Object】 名称自定义，固定的参数ProceedingJoinPoint**
**加上@Around注解  属性value:切入点表达式**
**在方法的前后都能增加功能，是最强的通知**
**能够控制目标方法是否被调用执行**

**能够修改目标方法的执行结果，影响最后的结果。而@AfterReturning不能改变结果**
**参数ProceedingJoinPoint就相当于JDK动态代理的method**
**所以参数的作用就是用来执行目标方法的**
**返回值就是目标方法的返回结果，可以被修改**

**目标方法**

```Java
public class SomeServiceImpl implements SomeService {
    @Override
    public String doFirst(String name, Integer age) {
        System.out.println("doFirst执行");
        return "doFirst.....";
    }
}
```

**切面类**

```Java
@Aspect
public class MyAspect {
    @Around(value = "execution(* *..SomeServiceImpl.doFirst(..))")
    public Object myAround(ProceedingJoinPoint pjp) throws Throwable {
        //在目标方法执行前输出时间
        System.out.println("环绕通知执行前添加日志");
        //执行目标方法，跟method.invoke作用一样
        //就是Object res=doFirst()
        Object res=pjp.proceed();
        System.out.println("环绕通知执行后提交事务");
        if(res!=null){
            res="你好";
        }
        return res;
    }
}
```

**以上的返回值不再像@AfterReturning那样，而是返回了你好！**



**在环绕通知的切面类里面，可以通过ProceedingJoinPoint来拿到目标方法的情况，来判断是否执行目标方法对于执行了目标方法之后【也就是pjp.proceed()】对于其结果是可以修改的，与@AfterReturning不同的是，@AfterReturning只能拿到对应的结果并且使用，就算是修改了结果，也只是在它对应的切面方法里使用。并不能真正的改变目标方法的返回结果，环绕通知经常用来做事务！在目标方法执行前开启事务，执行了目标方法之后再提交事务**

