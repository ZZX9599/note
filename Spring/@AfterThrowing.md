## @AfterThrowing

**在目标方法抛出异常后执行。该注解的 throwing 属性用于指定所发生的异常类对象。 当然，被注解为异常通知的方法可以包含一个参数 Throwable，参数名称为 throwing 指定的 名称，表示发生的异常对象。**

**方法定义：公共方法，没有返回值，方法名自定义，可以没有参数，一般有Exception，还有的话就是JoinPoint**

**@AfterThrowing注解的参数  value【切面表达式】  throwing 【变量名必须和方法的参数名一样】**

**目标类**

```Java
public class SomeServiceImpl implements SomeService {
    @Override
    public void doSecond(String name, Integer age) {
        int i=10/0;
        System.out.println("执行业务方法doSecond");
    }
}
```

**切面类**

```Java
@Aspect
public class MyAspect {
    @AfterThrowing(value = "execution(* *..SomeServiceImpl.doSecond(..))",throwing = "ex")
    /**
     * value:切入点表达式
     * throwing 变量名必须和方法的参数名一样
     */
    public void myAfterThrowing(Exception ex){
        System.out.println("发生异常的时候执行"+ex.getMessage());
    }
}
```

**当目标方法正常执行的时候，切面方法不会执行！出现异常的时候才会执行！**