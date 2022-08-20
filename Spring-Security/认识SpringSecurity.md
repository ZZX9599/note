### 认识SpringSecurity

**spring security 是基于 spring 的安全框架。**

**它提供全面的安全性解决方案，同时在 Web 请求级和方法调用级处理身份确认和授权。**

**在 Spring Framework 基础 上，spring security 充分利用了依赖注入（DI）和面向切面编程（AOP）功能**

**为应用系统提供声明式的安全访问控制功能，减少了为企业系统安全控制编写大量重复代码的工作。**

**是一个轻量级的安全框架。它与 Spring MVC 有很好地集成.**



#### spring security 核心功能

**（1）认证（你是谁，用户/设备/系统）** 

**（2）验证（你能干什么，也叫权限控制/授权，允许执行的操作）**



#### spring security 原理

**基于 Filter , Servlet, AOP 实现身份认证和权限验证**