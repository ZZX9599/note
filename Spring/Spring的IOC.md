# **Spring的IOC**

### **IoC (Inversion of Control) : 控制反转， 是一个理论，概念，思想。**

**描述的是把对象的创建，赋值，管理工作都交给代码之外的容器实现， 也就是对象的创建是有其它外部资源成**

**控制： 创建对象，对象的属性赋值，对象之间的关系管理。**
**反转： 把原来的开发人员管理，创建对象的权限转移给代码之外的容器实现。 由容器代替开发人员管理对象。创建对象，给属性赋值。**

**正转：由开发人员在代码中，使用new 构造方法创建对象， 开发人员主动管理对象。**

**Student s=new Student()**

**在代码中， 创建对象-->正转。**

**容器：是一个服务器软件， 一个框架（spring）**

**为什么要使用 ioc ： 目的就是减少对代码的改动， 也能实现不同的功能。 实现解耦合。** 

**ioc的体现：  servlet   因为Tomcat就是一个容器【存放servlet,filfter,listener】**



```java
1： 创建类继承HttpServelt 

2:  在web.xml 注册servlet， 使用
    <servlet-name> myservlet </servlet-name>
	<servelt-class>com.zzx.MyServlet1</servlet-class>
        
3. 没有创建 Servlet对象， 没有MyServlet myservlet = new MyServlet()

4. Servlet 是Tomcat服务器它能你创建的。 Tomcat也称为容器
Tomcat作为容器：里面存放的有Servlet对象， Listener ， Filter对象
```



### **IOC的技术实现DI**

**DI是IOC的技术实现【DI叫做依赖注入】**
**DI（Dependency Injection） :依赖注入， 只需要在程序中提供要使用的对象名称就可以， 至于对象如何在容器中创建，赋值，查找都由容器内部实现。**

**spring是使用的di实现了ioc的功能， spring底层创建对象，使用的是反射机制。**

**spring是一个容器，管理对象，给属性赋值， 底层是反射创建对象。**

**DI：创建对象，给对象的属性赋值**



**IOC能够实现业务对象之间的解耦合，例如service和dao之间的耦合**
