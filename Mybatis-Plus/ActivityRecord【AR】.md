### ActivityRecord【AR】

**AR负责把自己持久化，在AR中封装了对数据库的访问，通过对象自己实现CURD，实现对数据库操作**

**要求：实体类必须继承MP框架的Model，Model定义了CURD方法，实体类属性和表的列名一样**



```java
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * 使用AR的要求
 * 实体类需要继承MP框架的Model
 * 在Model里面提供了对数据库的CURD操作
 * 添加泛型更加方便，也可以不添加【推荐加上】
 */
public class Dept extends Model<Dept> {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
    private String mobile;
    private Integer manager;
}
```



#### 增加

```Java
@Test
public void insert(){
    Dept dept=new Dept("销售部","17628624139",1);
    //调用实体类自身的方法
    //INSERT INTO dept ( name, mobile, manager ) VALUES ( ?, ?, ? )
    boolean flag = dept.insert();
    System.out.println("插入操作:"+flag);
}
```



#### 更新

```java
@Test
public void update01(){
    Dept dept=new Dept(1004,"市场部","17628624139",2);
    //调用实体类自身的方法
    //UPDATE dept SET name=?, mobile=?, manager=? WHERE id=?
    boolean flag = dept.updateById();
    System.out.println("更新操作:"+flag);
}

@Test
public void update02(){
    Dept dept=new Dept();
    dept.setId(1004);
    dept.setName("活动部");
    //调用实体类自身的方法
    //UPDATE dept SET name=? WHERE id=?
    boolean flag = dept.updateById();
    System.out.println("更新操作:"+flag);
}

@Test
public void update03(){
    Dept dept=new Dept();
    dept.setId(1004);
    dept.setName("活动部");
    //这里把manager设置为了基本数据类型
    //调用实体类自身的方法
    //UPDATE dept SET name=?, manager=? WHERE id=?
    //会把默认值0更改进去，推荐设置包装类，和之前的一样
    boolean flag = dept.updateById();
    System.out.println("更新操作:"+flag);
}
```

**更新的时候，参考BaseMapper的更新注意事项**



#### 删除

```java
/**
 * 无参数
 */
@Test
public void deleteById01(){
    Dept dept=new Dept();
    dept.setId(1004);
    //DELETE FROM dept WHERE id=?
    boolean flag = dept.deleteById();
    System.out.println("删除结果:"+flag);
}

/**
 * 带参数id
 */
@Test
public void deleteById02(){
    Dept dept=new Dept();
    //DELETE FROM dept WHERE id=?
    boolean flag = dept.deleteById(1005);
    System.out.println("删除结果:"+flag);
}
```



#### 查询

```Java
/**
 * 不带参数
 * 查询不到结果返回null，不报错
 */
@Test
public void selectById01(){
    Dept dept=new Dept();
    dept.setId(1007);
    //SELECT id,name,mobile,manager FROM dept WHERE id=?
    Dept dept1 = dept.selectById();
    System.out.println("查询结果:"+dept1);
}

/**
 * 带参数
 * 查询不到结果返回null，不报错
 */
@Test
public void selectById02(){
    Dept dept=new Dept();
    //SELECT id,name,mobile,manager FROM dept WHERE id=?
    Dept dept1 = dept.selectById(1111);
    System.out.println("查询结果:"+dept1);
}
```

