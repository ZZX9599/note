### 返回值是map

**如果返回的数据实体类无法包含,可以使用map返回多张表中的若干数据.**

**返回后这些数据之间没有任何关系.就是Object类型.**

**返回的map的key就是列名或别名.**



#### **1：返回单行的map**

```java
//返回值是一个值,是map类型,根据主键查用户对象
Map<String,Object> getReturnMapOne(int id);
```



```sql
<select id="getReturnMapOne" resultType="map" parameterType="int">
    select id myid,username myusername,sex mysex,address myaddress,birthday mybirthday
    from users
    where id=#{id}
</select>
```



**返回的map的key就是列名**

```java
@Test
public void testGetReturnMapOne(){
    Map<String,Object> map = mapper.getReturnMapOne(7);
    System.out.println(map);
    System.out.println(map.get("username"));
}
```





#### 2：返回多行的map

```java
//使用map封装返回多个map的集合--->List<Map<String,Object>>
List<Map<String,Object>> getReturnMap();
```



```sql
<select id="getReturnMap" resultType="map" >
    select <include refid="columns"></include>
    from users
</select>
```



```java
@Test
public void testGetReturnMap(){
    List<Map<String,Object>> list = mapper.getReturnMap();
    list.forEach(map-> System.out.println(map));
}
```

