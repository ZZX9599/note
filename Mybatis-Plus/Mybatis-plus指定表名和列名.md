### Mybatis-plus指定表名和列名



#### 1：指定表名

**在定义实体类的时候，默认的表名跟实体类同名，如果不一致的话，可以通过注解@TableName来指定表的名称**

**语法：@TableName(value="数据库表名")**

**数据库的表名可能是tbl_Address，实体类是Addrress**

**我们就需要在实体类上面指定@TableName(value="tbl_address")**

**注意：属性依然是跟列名一致**



```java
package com.zzx.domian;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * @TableName(value="表名")
 * 位置在实体类的上面
 */
@TableName(value = "user_address")
public class Address{
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    private String street;
    private String city;
    private String zipcode;
}
```



#### 2：指定列名

**在上面的例子中，表名和实体类名不一样，但是列名和属性一致，需要添加@TableName注解**

**这里属性和列名也不一样，就需要使用@TableField注解来指定属性和列名的关系**

**语法：@TableField(value="列名")**



```java
package com.zzx.domian;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author ZZX
 * @date 2022/1/25 17:37
 * @TableName(value="表名")
 * 位置在实体类的上面
 */
@TableName(value = "user_address")
public class Address{
    @TableId(value = "user_id",type = IdType.ASSIGN_UUID)

    @TableField(value = "user_id")
    private String id;

    @TableField(value = "user_street")
    private String street;

    @TableField(value = "user_city")
    private String city;
    
    private String zipcode;
}
```



**需要注意的是：如果使用了@TableId注解：这个时候的value就需要引用列名，而不是属性名**
