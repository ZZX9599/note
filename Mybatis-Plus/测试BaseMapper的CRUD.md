### 测试BaseMapper的CRUD



#### 接口

```java
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzx.domian.User;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * 定义Mapper接口，继承BaseMapper接口
 * 基本mapper：里面封装了一些基本的方法，单表的增删改查完全没问题
 * BaseMapper是mp框架中的对象，里面有17个方法
 */
public interface UserMapper extends BaseMapper<User> {
}
```



#### 添加


```java
    @Test
    public void testUserInsert(){
        //创建User对象
        for(int i=0;i<10;i++){
            User user  = new User();
            user.setName("zhangsan"+i);
            user.setAge(20 + i);
            user.setEmail("zhangsan@sina.com");
            //调用UserMapper的方法， 也就是父接口BaseMapper中的提供的方法
            int rows = userDao.insert(user);
            System.out.println("insert 的结果:"+rows);
        }
```





#### 更新

```java
    @Test
    public void testUpdateUser(){
        User user = new User();
        user.setName("修改的数据");
        user.setAge(22);
        user.setEmail("edit@163.com");
        user.setId(2);
        //执行更新，根据主键值更新
        /*UPDATE user SET name=?, email=?, age=? WHERE id=?
         *更新了所有非null属性值， 条件where id = 主键值
         */
        int rows = userDao.updateById(user);
        System.out.println("update rows:"+rows);
    }
```



```java
    /**
     * 控制更新的属性
     */
    @Test
    public void testUpdateUser2(){
        User user  = new User();
        user.setId(2);
        user.setName("zhangsan");
        //更新数据
        //UPDATE user SET name=? WHERE id=?
        int i = userDao.updateById(user);
        System.out.println("i:"+i);
    }
```



```java
   /**
     * 更新数据： 实体类的属性是基本类型 - int age
     */
    @Test
    public void testUpdateUser3(){
        User user  = new User();
        user.setId(3);
        user.setEmail("lisi@sina.com");
        //实体对象 user: [name = null , email = "lisi@sina.com" , age = 0  ]
        //没有修改 name ,age
        //判断字段是否要修改， 加入到set语句， 是根据属性值是否为null .
        //UPDATE user SET email=?, age=? WHERE id=?
        int rows = userDao.updateById(user);
        System.out.println("rows："+rows);

    }
```



### 通过测试：发现更新操作，更新的是非空字段的值，不是null的就会更新

### 所以推荐将属性设置为包装类型，也就是设置为基本数据类型的话，传入的

### 参数就是默认值0这些，就会认为是值，就会更新掉，如果全部设置为包装

### 类，默认值就全部为null，得到的结果对象就是[属性:null,属性:value]，这

### 样就只会更新带有属性的





#### 删除

```Java
   /**
     * 按主键删除一条数据
     * 方法是deleteById()
     * 参数：主键值
     * 返回值：是删除的成功记录数
     */
    @Test
    public void testDeleteById(){

        //DELETE FROM user WHERE id=?
        int rows  = userDao.deleteById(3);
        System.out.println("deleteById:"+rows);
    }
```



```Java
   /**
     * 按条件删除数据， 条件是封装到Map对象中
     * 方法：deleteByMap(map对象);
     * 返回值：删除成功的记录数
     */
    @Test
    public void testDeleteByMap(){
        //创建Map对象，保存条件值
        Map<String,Object> map  = new HashMap<>();
        //put("表的字段名",条件值) ， 可以封装多个条件
        map.put("name","zs");
        map.put("age",20);
        //调用删除方法
        //DELETE FROM user WHERE name = ? AND age = ?
        int rows = userDao.deleteByMap(map);
        System.out.println("deleteByMap rows:"+rows);
    }
```



```Java
    /**
     * 批处理方式：使用多个主键值，删除数据
     * 方法名称：deleteBatchIds()
     * 参数： Collection<? extends Serializable> var1
     * 返回值：删除的记录数
     */
    @Test
    public void deleteBatchIds(){
        List<Integer>list=new ArrayList<>();
        list.add(1008);
        list.add(1010);
        //DELETE FROM user WHERE id IN ( ? , ? )
        int result = userMapper.deleteBatchIds(list);
        System.out.println("deleteBatchIds rows:"+result);
    }
```





#### 查询

```Java
   /**
     * 实现查询 selectById ,根据主键值查询
     * 参数：主键值：
     * 返回值： 实体对象(唯一的一个对象)
     */
    @Test
    public void testSelectById(){
        /**
         * 生成的sql: SELECT id,name,email,age FROM user WHERE id=?
         * 如果根据主键没有查找到数据， 得到的返回值是 null
         */
        User user = userDao.selectById(6);
        System.out.println("selectById:"+user);

        //在使用对象之前，需要判断对象是否为null
        if(user != null){
            //业务方法的调用
        }

```



```Java 
   /**
     * 实现批处理查询，根据多个主键值查询， 获取到List
     * 方法：selectBatchIds
     * 参数：id的集合
     * 返回值：List<T>
     */
    @Test
    public void testSelectBatchId(){
        List<Integer> ids = new ArrayList<>();
        ids.add(6);
        ids.add(9);
        ids.add(10);

        //查询数据
        //SELECT id,name,email,age FROM user WHERE id IN ( ? , ? , ? )
        List<User> users = userDao.selectBatchIds(ids);
        System.out.println("size:"+users.size());
        for (User u:users){
            System.out.println("查询的用户："+u);
        }
    }
```



```Java
   /**
     * 使用Map做多条件查询
     * 方法：selectByMap()
     * 参数：Map<String,Object>
     * 返回值：List<T>
     *
     */
    @Test
    public void testSelectMap(){
        //创建Map,封装查询条件
        Map<String,Object> map = new HashMap<>();
        //key是字段名， value：字段值 ，多个key，是and 联接
        map.put("name","zhangsan");
        map.put("age",20);

        //根据Map查询
        //SELECT id,name,email,age FROM user WHERE name = ? AND age = ?
        List<User> users = userDao.selectByMap(map);
        users.forEach(user -> {
            System.out.println("selectByMap:"+user);
        });
    }
}

```

