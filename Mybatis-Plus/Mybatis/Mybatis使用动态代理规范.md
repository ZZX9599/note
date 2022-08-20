### Mybatis使用动态代理规范



**MyBatis框架使用动态代理的方式来进行数据库的访问.**

**Mapper接口的开发相当于是过去的Dao接口的开发。由MyBatis框架根据接口定义创建动态代理对象，代理对象的方法体同Dao接口实现类的方法。在设计时要遵守以下规范.**

**\1.    Mapper接口与Mapper.xml文件在同一个目录下**

**\2.    Mapper接口的完全限定名与Mapper.xml文件中的namespace的值相同。**

**\3.    Mapper接口方法名称与Mapper.xml中的标签的statement 的ID完全相同。**

**\4.    Mapper接口方法的输入参数类型与Mapper.xml的每个sql的parameterType的类    型相同**

**\5.    Mapper接口方法的输出参数与Mapper.xml的每个sql的resultType的类型相同。**

**\6.    Mapper文件中的namespace的值是接口的完全限定名称.**

**\7.    在SqlMapConfig.xml文件中注册时,使用class属性=接口的完全限定名.**



**UsersMapper.java和UsersMapper.xml文件必须在同一个目录下，且必须同名。**

**在UsersMapper.xml文件中添加namespace属性为接口的完全路径名。**

**使用Session对象的getMapper()  传入接口的class就能拿到代理对象了，直接执行方法就能操作数据库了**

