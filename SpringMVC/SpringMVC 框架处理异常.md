### SpringMVC 框架处理异常

**SpringMVC 框架处理异常的常用方式：使用@ExceptionHandler 注解处理异常**



**@ExceptionHandler 注解**



**使用注解@ExceptionHandler 可以将一个方法指定为异常处理方法。该注解只有一个可选属性 value，为一个 Class数组，用于指定该注解的方法所要处理的异常类，即所要匹配的异常**



**而被注解的方法，其返回值可以是 ModelAndView、String，或 void，方法名随意，方法 参数可以是 Exception 及其子类对象、HttpServletRequest、HttpServletResponse 等。系统会 自动为这些方法参数赋值。 对于异常处理注解的用法，也可以直接将异常处理方法注解于 Controller 之中**



**1：创建异常类，作为全局异常处理类，类上加入@ControllerAdvice**

**2：在类中定义方法，方法上面加入@ExceptionHandler**

**3：需要有组件扫描器来扫描@ControllerHandler所在的包名**

**4：加入注解驱动**



**实质就是把异常抛给了框架**

**异常方法的定义和控制器方法的定义一样，可以有多个参数和多种返回值【ModelAndView,String,Void等】**



**@ExceptionHandler()的属性  只有一个value，是class数组，存放所有可能出现的异常类**



**@ExceptionHandler()不加value，则未匹配的异常，都执行这个方法**



**Exception e代表出现的异常类**

```Java
package com.zzx.handler;

import com.zzx.exception.AgeException;
import com.zzx.exception.NameException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * @ControllerAdvice 控制器增强，也就是给控制器增加功能，类似AOP,需要组件扫描器来扫描
 */

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NameException.class)
    public ModelAndView doNameException(Exception e){
        //Exception e代表Controller抛出的异常对象
        //可以通过e来获取异常的信息
        ModelAndView mv=new ModelAndView();
        mv.addObject("msg","除张三外不能访问");
        mv.addObject("ex",e);
        mv.setViewName("nameError");
        return mv;
    }

    @ExceptionHandler(AgeException.class)
    public ModelAndView doAgeException(Exception e){
        //Exception e代表Controller抛出的异常对象
        //可以通过e来获取异常的信息
        ModelAndView mv=new ModelAndView();
        mv.addObject("msg","年龄过大");
        mv.addObject("ex",e);
        mv.setViewName("ageError");
        return mv;
    }

    @ExceptionHandler
    public ModelAndView doAllException(Exception e){
        //Exception e代表Controller抛出的异常对象
        //可以通过e来获取异常的信息
        ModelAndView mv=new ModelAndView();
        mv.addObject("msg","出现了异常");
        mv.addObject("ex",e);
        mv.setViewName("allError");
        return mv;
    }
}
```



```Java
@Controller
@RequestMapping("/user")
public class MyController {
    @RequestMapping("/some.do")
    public ModelAndView doSome(String name,Integer age,HttpServletRequest request)throws UserException {
        if (!name.equals("张三")) {
            throw new NameException("姓名不正确");
        }
        ModelAndView mv=new ModelAndView();
        mv.addObject("myName",name);
        mv.addObject("myAge",age);
        mv.setViewName("show");
        return mv;
    }
}
```