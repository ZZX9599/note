### 多个参数使用map

**入参是map,是因为当传递的数据有多个,不适合使用指定下标或指定名称的方式来进行传参**

**又加上参数不一定与对象的成员变量一致,考虑使用map集合来进行传递**

**map使用的是键值对的方式.当在sql语句中使用的时候#{键名},${键名},用的是键的名称**



```java
 List<Users> getByMap(Map map);
```

```sql
<select id="getByMap" resultType="users" >
     select <include refid="allColumns"></include>
     from users
     where birthday between #{birthdayBegin} and #{birthdayEnd}
</select>
```



**#{birthdayBegin}中的birthdayBegin实际上是map的key**



**所以在涉及到多个参数的sql语句，都不会使用参数的下标来执行sql语句**

**一般情况下，我们都是使用map封装，或者创建vo实体类**

**什么时候创建vo实体类？如果这个vo会经常使用，我们就封装vo类，不然我们基本上都是使用map**

