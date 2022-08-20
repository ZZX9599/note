### Mybatis主配置文件解析

```json
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
```

**引入头约束文件**

**头文件里面包含了主配置文件里面能些什么标签，标签是否含有子标签，标签的顺序等等**



```xml
<configuration>
<!--    将文件流指向db.properties,最终是为了从中读取数据库的一堆配置信息-->
    <properties resource="db.properties"></properties>
<!--    要进行数据库的访问，所以要进行数据库访问的配置，驱动，url,username,password-->
<!--    environments:进行环境变量(连接数据库)的配置
                     可以进行多个数据库连接配置,可以上线一套,开发一套,可以mysql一套,oracle一套等
        default:本次配置中使用的环境变量的名称,多套配置,default决定哪套配置生效
-->
    <environments default="development">
<!--environment：进行具体环境变量的配置
    id: 为当前的配置的环境变量起个名称,为了在environments的default中使用
-->
        <environment id="development">
<!--            transactionManager:事务管理
                type: JDBC:就是程序员自己来管理事务的提交和回滚
                      MANAGED:由容器来进行事务的管理,例如:spring
-->
            <transactionManager type="JDBC"></transactionManager>
<!--            dataSource:数据源的配置
                      type:指定数据源的配置方式,是否是连接池
                           "POOLED":表明使用数据库连接池
                           "UNPOOLED":不使用连接池
                           "JNDI":java命名目录接口,由服务器端负责连接池的管理
                      property: driver:数据库驱动
                                url:数据库的路径
                                username:访问数据库的用户名
                                password:访问数据库的用户的密码
-->
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>
<!--    注册StudentDaoImpl.xml文件,注意.xml后缀要带上-->
    <mappers>
        <mapper resource="StudentDaoImpl.xml"></mapper>
    </mappers>
</configuration>

```

