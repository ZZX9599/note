### sql里面什么时候写参数

**实际上，除了八种基本类型和对象以外，我们都不需要写参数**

**实际上，除了对象实体类的类型，我们都不写参数**



**注意：如果参数含有多个，我们是不能写parameterType的**

```Java
//查询指定日期范围内的用户信息
List<Users> getByBirthday(Date begin, Date end);
```



**就像这样，实际上我们不能写参数，因为有多个参数，不能同时写多个参数**

**要么封装到map array list，要么只能通过下标来获取**

```sql
<select id="getByBirthday" resultType="users">
   select * from users where birthday between #{arg0} and #{arg1}
</select>
```

