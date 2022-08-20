### 注册mapper文件的四种方法



**1:使用resource注册**

```xml
    <mappers>
        <mapper resource="Student.xml"></mapper>
    </mappers>
```

**2:使用url注册**

```xml
	<mappers>
		<mapper url="file:///E:/UserMapper.xml"></mapper>
	</mapper>
```

**3:使用class注册**

**动态代理就使用这种方式进行注册**

**注意: class的值是接口的完全限定名称.**

```xml
	<mappers>
		<mapper class="com.bjpowernode.mapper.UsersMapper"></mapper>
	</mapper>
```

**4:使用<package>注册**

```xml
	<mappers>
		<package name="com.bjpowernode.mapper"/>
	</mapper>
```

