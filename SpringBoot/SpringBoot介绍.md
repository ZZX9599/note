### **SpringBoot介绍**



1. **为什么要使用 Spring Boot**

   **因为Spring， SpringMVC 需要使用的大量的配置文件 （xml文件）**

   **还需要配置各种对象，把使用的对象放入到spring容器中才能使用对象**

   **需要了解其他框架配置规则。**

2. **SpringBoot 就相当于不需要配置文件的Spring+SpringMVC。 常用的框架和第三方库都已经配置好了。**

   **拿来就可以使用了。**

3. **SpringBoot开发效率高，使用方便多了**



**=============================SpringBoot介绍以及优点===============================**

**SpringBoot是Spring中的一个成员， 可以简化Spring，SpringMVC的使用。 他的核心还是IOC容器。**

**特点：**
**1：- Create stand-alone Spring applications**
**创建独立的spring应用**

**2：- Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)**
**内嵌的tomcat， jetty ， Undertow服务器【也就是不需要安装tomcat就能执行web应用】**

**3：- Provide opinionated 'starter' dependencies to simplify your build configuration**
**提供了【starter】起步依赖，简化应用的配置。   
比如使用MyBatis框架 ， 之前在Spring项目中，必须要配置MyBatis的对象 SqlSessionFactory，Dao的代理对象**
**在SpringBoot项目中，在pom.xml里面, 加入一个 mybatis-spring-boot-starter依赖**
**就能够直接使用，不需要重新配置【减少了配置xml的难度】**

**4：- Automatically configure Spring and 3rd party libraries whenever possible**

**尽可能去配置spring和第三方库。叫做自动配置**
**也就是【把spring中的第三方库中的对象都创建好，放到容器中， 开发人员可以直接使用】**
**例如：SpringMVC中要配置中央调度器，在SpringBoot中就不需要配置了**

**5：- Provide production-ready features such as metrics, health checks, and externalized configuration**
**提供了健康检查， 统计，外部化配置**

**6：- Absolutely no code generation and no requirement for XML configuration**
**不用生成代码， 不用使用xml，做配置**

