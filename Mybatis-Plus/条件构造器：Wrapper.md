### 条件构造器：Wrapper



#### 1：allEq方法【所有条件相等】

```java
@Test
public void testAllEq01(){
    //创建对象
    QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
    //封装查询条件
    Map<String,Object>map=new HashMap<>();
    //map的key就是列名
    map.put("name","张三");
    map.put("age",20);
    map.put("status",1);
    //添加查询的条件，封装起来
    queryWrapper.allEq(map);
    //SELECT id,name,age,email,status FROM student WHERE 
    //(name = ? AND age = ? AND status = ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}

@Test
public void testAllEq02(){
    //创建对象
    QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
    //封装查询条件
    Map<String,Object>map=new HashMap<>();
    //map的key就是列名
    map.put("name","张三");
    map.put("age",null);
    map.put("status",1);
    //添加查询的条件，封装起来【重载方法，添加boolean值】
    queryWrapper.allEq(map,true);
    //SELECT id,name,age,email,status FROM student WHERE 
    //(name = ? AND age IS NULL AND status = ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}

@Test
public void testAllEq03(){
    //创建对象
    QueryWrapper<Student> queryWrapper=new QueryWrapper<>();
    //封装查询条件
    Map<String,Object>map=new HashMap<>();
    //map的key就是列名
    map.put("name","张三");
    map.put("age",null);
    map.put("status",1);
    //添加查询的条件，封装起来【重载方法，添加boolean值】
    queryWrapper.allEq(map,false);
    //SELECT id,name,age,email,status FROM student WHERE (name = ? AND status = ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```

**结论：如果allEq传入map，则是直接使用AND连接，如果后面传入参数true，就代表如果存在null的话，就直接**

**使用这个null，不会忽略null的值，如果传入false的话，就代表忽略null的值**

**注意：如果不带boolean值，则默认就是true**



#### 2：eq【单个条件相等】

**语法  eq("列名","值")**

**查询age是20岁的学生列表**

```java
@Test
public void testEq(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.eq("age",20);
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (age = ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 3：ne【不相等】

**查询age不是20岁的学生列表**

```java
@Test
public void testNe(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.ne("age",20);
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (age <> ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 4：gt【大于】

**查询age大于20岁的学生列表**

```java
@Test
public void testGt(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.gt("age",21);
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (age > ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 5：ge【大于等于】

**查询age大于等于20岁的学生列表**

```java
@Test
public void testGe(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.ge("age",21);
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (age >= ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 6：lt【小于】

**查询age小于21岁的学生列表**

```java
@Test
public void testLt(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.lt("age",21);
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (age < ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 7：le【小于等于】

**查询age小于等于21岁的学生列表**

```java
@Test
public void testLe(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.lt("age",21);
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (age <= ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```





#### 8：between【在?和?之间】

**语法：between("filed",start,end)**

**查询的是 age>=20&&age<=23**

**查询年龄在20岁到23岁之间的学生列表**

```java
@Test
public void testBetween(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.between("age",20,23);
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (age BETWEEN ? AND ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 9：notBetween【不在?和?的范围】

**语法：notBetween("filed",start,end)**

**查询的是age<20&&age>23**

**查询年龄不在20岁到23岁的学生列表**

```java
@Test
public void testNotBetween(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.notBetween("age",20,23);
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (age NOT BETWEEN ? AND ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



****

****



### 模糊查询

#### like匹配的是  %值%

#### notLike不匹配  %值%

#### MP框架会在value的值前后加上%%



#### 10：like

**语法：like("filed",value)**

**查询名字含有张的学生信息**

```java
@Test
public void testLike(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.like("name","张");
    //查询
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```

```yml
Preparing: SELECT id,name,age,email,status FROM student WHERE (name LIKE ?)
Parameters: %张%(String)
```



#### 11：notLike

**语法：notLike("filed",value)**

**查询名字不含有张的学生信息**

```java
@Test
public void testNotLike(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.notLike("name","张");
    //查询
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```

```yml
Preparing: SELECT id,name,age,email,status FROM student WHERE (name NOT LIKE ?)
Parameters: %张%(String)
```



#### 12：likeLeft

**SQL里面使用的值：%值**

**%代表的是前面为任意的字符串**

**语法：likeLeft("filed",value)**

**查询名字以张结尾的的学生信息**

```Java
@Test
public void testLikeLeft(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.likeLeft("name","张");
    //查询
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```

```yml
Preparing: SELECT id,name,age,email,status FROM student WHERE (name LIKE ?)
Parameters: %张(String)
```



#### 13：likeRight

**SQL里面使用的值：值%**

**%代表的是后面为任意的字符串**

**语法：likeRight("filed",value)**

**查询名字以张开头的的学生信息**

```java
@Test
public void testLikeRight(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.likeRight("name","张");
    //查询
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```

```yml
Preparing: SELECT id,name,age,email,status FROM student WHERE (name LIKE ?)
Parameters: 张%(String)
```



#### 14：isNull【判断字段值为null】

**语法：isNull("filed",value)**

**查询email为空的学生列表**

```java
@Test
public void testIsNull(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.isNull("email");
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (email IS NULL)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 15：isNotNull【判断字段不为空】

**语法：isNotNull("filed",value)**

**查询email不为空的学生列表**

```java
@Test
public void testIsNotNull(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.isNotNull("email");
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (email IS NOT NULL)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 16：in【在范围里面】

**查看姓名可能为张三，李四，王五，赵六，周志雄的学生列表**



##### 方法一：直接写入参数的数据

```java
@Test
public void testIn(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.in("name","张三","李四","王五","赵六","周志雄");
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (name IN (?,?,?,?,?))
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



##### 方法二：把可能出现的值存放到Collection集合

```java
@Test
public void testIn02(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    Collection<String>collection=new ArrayList<>();
    collection.add("张三");
    collection.add("李四");
    collection.add("王五");
    collection.add("赵六");
    collection.add("周志雄");
    queryWrapper.in("name",collection);
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (name IN (?,?,?,?,?))
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 17：notIn【不在范围里面】

**查看姓名不为张三，李四，王五，赵六，周志雄的学生列表**



##### 方法一：直接写入参数的数据

```java
@Test
public void testNotIn01(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.notIn("name","张三","李四","王五","赵六","周志雄");
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (name NOT IN (?,?,?,?,?))
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



##### 方法二：把可能出现的值存放到Collection集合

```java
@Test
public void testNotIn02(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    Collection<String>collection=new ArrayList<>();
    collection.add("张三");
    collection.add("李四");
    collection.add("王五");
    collection.add("赵六");
    collection.add("周志雄");
    queryWrapper.notIn("name",collection);
    //查询
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 18：inSql【常用来做子查询，类型in()】

**语法：inSql("filed","String")**

```java
@Test
public void testInSql(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.inSql("age","select age from student where id=1");
    //查询
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```

```java
SELECT id,name,age,email,status FROM student 
WHERE (age IN (select age from student where id=1))
```



#### 19：notInSql

```java
@Test
public void testNotInSql(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.notInSql("age","select age from student where id=1");
    //查询
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```

```java
SELECT id,name,age,email,status FROM student 
WHERE (age NOT IN (select age from student where id=1))
```



#### 20：groupBy

**根据name进行分组**

```java
@Test
public void testGroup(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.select("name,count(*) as personNumbers");
    queryWrapper.groupBy("name");
    //查询
    //SELECT name,count(*) as personNumbers FROM student GROUP BY name
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 21：orderByAsc

**按age升序排序**

```java
@Test
public void testOrderByDAsc(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.orderByAsc("age");
    //查询
    //SELECT id,name,age,email,status FROM student ORDER BY age ASC
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```

**按名字和age升序排序**

```java
@Test
public void testOrderByDAsc(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.orderByAsc("name","age");
    //查询
    //SELECT id,name,age,email,status FROM student ORDER BY name ASC,age ASC
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```

**orderDesc一样的使用方式**



#### 22：orderBy

**参数一：是否启用条件  true/false    true的话就把参数二和参数三的语句加在aql语句中**

**参数一：true/fasle    true代表是否采用Asc排序**

**参数二：属性名**

**根据id升序排列**

```java
@Test
public void testOrder(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.orderBy(true,true,"id");
    //查询
    //SELECT id,name,age,email,status FROM student ORDER BY id ASC
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```

**参数一改为false     SELECT id,name,age,email,status FROM student**

**参数二改为false     SELECT id,name,age,email,status FROM student ORDER BY id DESC**



#### 多字段混合排序

**orderBy返回的结果是this，也就是queryWrapper对象**

**按照id升序，age降序排列**

```java
@Test
public void testOrder02(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.orderBy(true,true,"id").orderBy(true,false,"age");
    //查询
    //SELECT id,name,age,email,status FROM student ORDER BY id ASC,age DESC
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 23：or【连接条件使用or】

**默认连接条件是and**

**查询name是张三或者李四的学生列表**

```java
@Test
public void testOr(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.eq("name","张三").or().eq("name","李四");
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (name = ? OR name = ?)
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



**24：and【连接条件是and】**

**查询name是张三，年龄是20的学生列表**

```java
@Test
public void testAnd(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.eq("name","张三").eq("age",20);
    //查询
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```

**不写默认是and，当然也可以写and()**



#### 25：last【拼接sql语句】

**查询name等于张三或者age等于20的记录，取第一条数据**

```java
@Test
public void testLast(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.eq("name","张三").or().eq("age",20).last("limit 1");
    //查询
    //SELECT id,name,age,email,status FROM student WHERE (name = ? OR age = ?) limit 1
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



**26：exists【拼接sql语句】**

**判断有没有年龄大于24的学生，如果有，就查询所有学生**

```Java
@Test
public void testExist(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.exists("select id from student where age>24");
    //查询
    //SELECT id,name,age,email,status FROM student 
    //WHERE (EXISTS (select id from student where age>24))
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```



#### 27：notExists【拼接sql】

**如果不存在年龄大于24的，就查询所有**

```java
@Test
public void testNotExist(){
    //创建条件构造器对象
    QueryWrapper<Student>queryWrapper=new QueryWrapper<>();
    //封装条件
    queryWrapper.notExists("select id from student where age>24");
    //查询
    //SELECT id,name,age,email,status FROM student 
    //WHERE (NOT EXISTS (select id from student where age>24))
    List<Student> list = studentMapper.selectList(queryWrapper);
    if(list!=null){
        for(Student student:list){
            System.out.println(student);
        }
    }
}
```