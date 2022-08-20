### Thymeleaf介绍

Thymeleaf 是一个流行的模板引擎

该模板引擎采用 Java 语言开发 模板引擎是一个技术名词，是跨领域跨平台的概念，在 Java 语言体系下有模板引擎，在 C#、PHP 语言体系下也有模板引擎，甚至在 JavaScript 中也会用到模板引擎技术，Java 生态下 的模板引擎有 Thymeleaf 、Freemaker、Velocity、Beetl（国产） 等。



Thymeleaf 对网络环境不存在严格的要求，既能用于 Web 环境下，也能用于非 Web 环境 下。在非 Web 环境下，他能直接显示模板上的静态数据；在 Web 环境下，它能像 Jsp 一样从 后台接收数据并替换掉模板上的静态数据。我们使用的时候主要是基于 HTML 的，以 HTML 标签为载体， Thymeleaf 要寄托在 HTML 标签下实现。



实际上，Thymeleaf类似jsp，模板就是前端页面，数据就是后台的数据，模板引擎就是整合它们，展现数据

Thymeleaf 是一个服务器端 Java 模板引擎，能够处理 HTML、XML、CSS、JAVASCRIPT 等模板文件。Thymeleaf 

模板可以直接当作静态原型来使用，它主要目标是为开发者的开发工作流程带来优雅的自然模板，也是 Java 服务

器端 HTML5 开发的理想选择。



Spring Boot 集成了 Thymeleaf 模板技术，并且 Spring Boot 官方也推荐使用 Thymeleaf 来 替代 JSP 技术



Thymeleaf 是另外的一种模板技术，它本身并不属于 Spring Boot，Spring Boot 只是很好地集成这种模板技术，

作为前端页面的数据展示，在过去的 Java Web 开发中，我们 往往会选择使用 Jsp 去完成页面的动态渲染，但是 

jsp 需要翻译在服务端编译运行成字符串打给浏览器端，效率低



模板实际上就是字符串的替换



网址：www.thymeleaf.org



Thymeleaf 官方手册：https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html
