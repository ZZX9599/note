## @After

**无论目标方法是否抛出异常，该增强均会被执行。**

**一般做资源回收，清除无用变量，关闭资源等**

**@After的参数  value【切入点表达式】**

**最终通知的方法定义：public void 方法名【没有参数，有的话就是JoinPoint】**

**目标方法**

```Java
public class SomeServiceImpl implements SomeService {

    @Override
    public void doThird(String name, Integer age) {
        System.out.println("执行业务方法doThird");
    }
}
```

**切面类**

```Java
@Aspect
public class MyAspect {
    @After(value = "execution(* *..SomeServiceImpl.doThird(..))")
    public void myAfter(){
        System.out.println("执行最终通知");
    }
}
```

**就算目标方法产生异常了，一样会执行切面类的方法**