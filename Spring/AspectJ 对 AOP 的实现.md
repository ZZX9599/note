## AspectJ 对 AOP 的实现

**对于 AOP 这种编程思想，很多框架都进行了实现。Spring 就是其中之一，可以完成面向切面编程。然而，AspectJ 也实现了 AOP 的功能，且其实现方式更为简捷，使用更为方便， 而且还支持注解式开发。所以，Spring 又将 AspectJ 的对于 AOP 的实现也引入到了自己的框架中。 在 Spring 中使用 AOP 开发时，一般使用 AspectJ 的实现方式。**

**AspectJ 是一个优秀面向切面的框架，它扩展了 Java 语言，提供了强大的切面实现。是目前最强的切面框架**

**AspetJ 是 Eclipse 的开源项目，官网：http://www.eclipse.org/aspectj/**

**AspetJ实现AOP的两种方式   1：XML   2：注解【常用】**

**AspectJ 的通知类型**

**AspectJ的知识点如下：**

**1：切面的执行时间【Advice】**

**AspectJ 中常用的通知有五种类型：**

**（1）前置通知@Before **

**（2）后置通知 @AfterReturning**

**（3）环绕通知 @Around**

**（4）异常通知@AfterThrowing**

**（5）最终通知@After**

**2：切面的执行位置：使用切入点表达式**

**AspectJ 定义了专门的表达式用于指定切入点。表达式的原型是：**

**execution**

**(modifiers-pattern? ret-type-pattern declaring-type-pattern?name-pattern(param-pattern) throws-pattern?) **

**解释： modifiers-pattern] 访问权限类型 ret-type-pattern 返回值类型 declaring-type-pattern 包名类名 name-pattern(param-pattern) 方法名(参数类型和参数个数) throws-pattern 抛出异常类型 ？表示可选的部分**

**execution("修饰符 返回值 包名.方法名.类名(参数) 异常)"**

**以上表达式共 4 个部分。 execution(访问权限 方法返回值 方法声明(参数) 异常类型)**

**注意：【方法返回值 方法声明(参数)这两部分必须要有】**

**"  *  "用于匹配一个或者多个字符**

**"  ..  "用在方法参数里面，表示任意多个参数  用在包名之后，表示当前包及其子包**



**一些实例：**

**execution(public * *(..)) 指定切入点为：任意公共方法。**

**execution(* set*(..)) 指定切入点为：任何一个以“set”开始的方法。**

**execution(* com.xyz.service.*.*(..)) 指定切入点为：定义在 service 包里的任意类的任意方法。**

**execution(* com.xyz.service..*.*(..)) 指定切入点为：定义在 service 包或者子包里的任意类的任意方法。“..”出现在类名中时，后面必须跟“*”，表示包和子包下的所有类。**

**execution(* *..service.*.*(..)) 指定所有包下的 serivce 子包下所有类（接口）中所有方法为切入点**



### AspectJ的使用

**需要加入依赖   1：spring依赖    2：aspectj依赖**

**创建切面类，类上加注解@Aspect，类中的方法就是切面需要执行的功能代码**

**在方法的上面加上通知注解，例如@Before 【指定切入点表达式】**

**创建配置文件，声明对象，交给spring管理，还可以使用注解创建对象**

**在配置文件中引入aspectJ的自动代理生成器，自动扫描创建代理对象**

**1）声明目标对象**

**2）声明切面类对象**

**3）声明自动代理生成器，自动生成代理对象**



**注意：这个时候从容器来获得目标对象，实际上就是创建好的代理对象**
