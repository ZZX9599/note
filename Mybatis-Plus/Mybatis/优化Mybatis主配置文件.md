### 优化Mybatis主配置文件



##### 添加日志打印输出

文件加入日志配置，可以在控制台输出执行的 sql 语句和参数.

```json
<!--  设置查看mybatis生成的sql语句的日志配置-->     
<settings>
	<setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>     
```



##### 为实体类起别名

**由于mapper文件中入参和返回值都要使用实体类的对象,而我们在使用的时候必须书写实体类的完全限定类名,这样比较麻烦,我们可以通过起别名的方式来简化此操作.起别名的方式有两种**

**1:单个注册**

```xml
    <typeAliases>
        <typeAlias type="com.zzx.domain.Student" alias="student"></typeAlias>
    </typeAliases>
```

**2:批量注册**

```xml
    <typeAliases>
        <package name="com.zzx.domain"/>
    </typeAliases>
```

**批量注册的时候，别名就是类名的驼峰命名法**