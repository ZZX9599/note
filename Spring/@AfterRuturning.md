## @AfterRuturning

**@AfterReturning后置通知**

**方法定义：公共方法，没有返回值，方法名自定义，有参数，**

**推荐Object res，因为目标方法的返回值可能是任何类型**

**Object res就是目标方法的返回值**

**@AfterReturning注解的参数**

**1：value 切入点表达式**

**2：returning 自定义的变量，表示目标方法的返回值，自定义变量必须和通知方法的形参一致，就是上面的res**

**执行时间在目标方法之后，能够获取到目标方法的返回值，可以修改返回值**

**后置通知的执行顺序：先执行目标方法，然后再把结果传给后置通知方法的参数**

**目标类**

```Java
public class SomeServiceImpl implements SomeService {
    @Override
    public String doOther() {
        System.out.println("doOther执行");
        return "你好";
    }
}
```

**切面类**

```Java
@Aspect
public class MyAspect {
    /**
     * 后置通知定义方法
     */
    @AfterReturning(value = "execution(* com.zzx.bao02.impl.SomeServiceImpl.doOther())",returning = "res")
    public void myAfterReturning(Object res){
        //Object res=doOther()
        System.out.println("后置通知：目标方法的返回值是:"+res);
        if(res!=null) {
            res = "你也好";
        }
    }
}
```

**测试类的结果是你好，并不是你也好**

**执行顺序：Object res=doOther()   然后myAfterReturning(res)**

**对于@AfterReturning，里面能修改返回值，但是并不能影响返回的结果！**