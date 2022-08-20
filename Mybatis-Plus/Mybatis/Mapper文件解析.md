### Mapper文件解析

**mapper文件一样拥有dtd约束头文件**

```json
<?xml version="1.0" encoding="UTF-8" ?> <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
     "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
```

```json
该文件完成数据库中student表的所有增删改查的操作.

<mapper namespace="自定义的路径名称">,在简单访问中namespace中的内容可以自定义,目的是为了区别不同<mapper>中相同id的语句.

该文件中提供<select><update><insert><delete>等数据库中的基本操作标签.
```

```xml
<?xml version="1.0" encoding="UTF-8" ?> <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
        
<mapper namespace="com.zzx">
    <!--插入学生-->
    <insert id="insert" parameterType="student">
        insert into student(id,name,age) values(#{id},#{name},#{age})
    </insert>

    <!--按照id查学生-->
    <select id="selectById" parameterType="int">
        select id,name,age from student where id=#{id}
    </select>
</mapper>
```

