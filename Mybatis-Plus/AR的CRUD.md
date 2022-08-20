### AR的CRUD



### 添加

```java
@Test
public void testInsert(){
    Dept dept=new Dept("蒋雪丽","13438636246",102);
    //自身的添加操作！
    //INSERT INTO dept ( name, mobile, manager ) VALUES ( ?, ?, ? )
    flag=dept.insert();
    System.out.println("插入的结果:"+flag);
}
```



### 更新

```java
@Test
public void testUpdate01(){
    Dept dept=new Dept(1003,"蒋雪丽","15983474509",103);
    //根据对象的主键来更新记录
    //UPDATE dept SET name=?, mobile=?, manager=? WHERE id=?
    flag=dept.updateById();
    System.out.println("更新的结果:"+flag);
}
```

```Java
@Test
public void testUpdate02(){
    Dept dept=new Dept();
    dept.setId(1002);
    dept.setName("李四");
    //根据对象的主键来更新记录
    //UPDATE dept SET name=? WHERE id=?
    //证明AR只修改非null的字段,null的字段不做任何处理
    flag=dept.updateById();
    System.out.println("更新的结果:"+flag);
}
```



### 删除

```Java
@Test
public void testDeleteByIdParam(){
    Dept dept=new Dept();
    //根据主键id删除数据,带参数
    //DELETE FROM dept WHERE id=?
    flag=dept.deleteById(1001);
    System.out.println("删除的结果:"+flag);
}
```

```Java
@Test
public void testDeleteByIdNoParam(){
    Dept dept=new Dept();
    //根据主键id删除数据，无参数
    //flag = dept.deleteById();
    //com.baomidou.mybatisplus.core.exceptions.MybatisPlusException: deleteById primaryKey is null
    //如果不设置id，就会报错
    //设置id
    dept.setId(1002);
    flag=dept.deleteById();
}
```



### 查询

```Java
/**
 * 有记录的话，返回对象，没有就返回null
 */
@Test
public void testSelectByIdParam(){
    Dept dept=new Dept();
    //SELECT id,name,mobile,manager FROM dept WHERE id=?
    dept = dept.selectById(1003);
    System.out.println("查询的结果:"+dept);
}
```

```Java
@Test
public void testSelectByIdNoParam(){
    Dept dept=new Dept();
    dept.setId(1003);
    //SELECT id,name,mobile,manager FROM dept WHERE id=?
    dept = dept.selectById();
    System.out.println("查询的结果:"+dept);
}
```