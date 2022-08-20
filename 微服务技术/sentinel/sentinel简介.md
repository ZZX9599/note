### sentinel简介

**Sentinel是阿里巴巴开源的一款微服务流量控制组件。官网地址：https://sentinelguard.io/zh-cn/index.html**

**Sentinel 具有以下特征:**

**•丰富的应用场景：Sentinel 承接了阿里巴巴近 10 年的双十一大促流量的核心场景，例如秒杀（即突发流量控制在系统容量可以承受的范围）、消息削峰填谷、集群流量控制、实时熔断下游不可用应用等。**

**•完备的实时监控：Sentinel 同时提供实时的监控功能。您可以在控制台中看到接入应用的单台机器秒级数据，甚至 500 台以下规模的集群的汇总运行情况。**

**•广泛的开源生态：Sentinel 提供开箱即用的与其它开源框架/库的整合模块，例如与 Spring Cloud、Dubbo、gRPC 的整合。您只需要引入相应的依赖并进行简单的配置即可快速地接入 Sentinel。**

**•完善的 SPI 扩展点：Sentinel 提供简单易用、完善的 SPI 扩展接口。您可以通过实现扩展接口来快速地定制逻辑。例如定制规则管理、适配动态数据源等。**



**sentinel官方提供了UI控制台，方便我们对系统做限流设置。大家可以在[GitHub]**

**https://github.com/alibaba/Sentinel/releases下载**



**默认的账户和密码都是sentinel**



**如果要修改Sentinel的默认端口、账户、密码，可以通过下列配置：**



![默认](E:\笔记整理\微服务技术\sentinel\图解\默认.png)



**java -jar sentinel-dashboard-1.8.1.jar -Dserver.port=8090**

**java -jar sentinel-dashboard-1.8.1.jar --server.port=8090**