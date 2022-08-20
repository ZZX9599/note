### 动态sql

**可以定义代码片断,可以进行逻辑判断,可以进行循环处理(批量处理),使条件判断更为简单.
  1)<sql>:用来定义代码片断,可以将所有的列名,或复杂的条件定义为代码片断,供使用时调用.
  2)<include>:用来引用<sql>定义的代码片断.**

**以上两个标签通常是相辅相成的，在一起使用，如果要使用sql定义的片段，必须要使用include引用**

```java
<mapper namespace="com.zzx">
    <sql id="all">
        id,name,age
    </sql>

    <!--按照id查学生-->
    <select id="selectById" parameterType="int">
        select <include refid="all"></include> from student where id=#{id}
    </select>
</mapper>
```





#### if标签

**进行条件判断
test条件判断的取值可以是实体类的成员变量,可以是map的key,可以是@Param注解的名称.**

```java
<if test="userName != null and userName != '' ">
    and username like concat('%',#{userName},'%')
</if>
```

**一般判断字符串是否为空就这样判断**





#### where标签

**这个标签通常是进行多条件的查询，拼接等**

**一般开发复杂业务的查询条件时，如果有多个查询条件，通常会使用<where>标签来进行控制。 标签可以自动的将第一个条件前面的逻辑运算符 (or ,and) 去掉，正如代码中写的，id 查询条件前面是有“and”关键字的，但是在打印出来的sql语句中却没有，这就是<where> 的作用**

```xml
<select id="getByCondition" resultType="users" parameterType="users">
    select <include refid="allColumns"></include>
    from users
    <where>
        <if test="userName != null and userName != '' ">
            and username like concat('%',#{userName},'%')
        </if>
        <if test="birthday != null ">
            and birthday = #{birthday}
        </if>
        <if test="sex != null and sex !=''">
            and sex = #{sex}
        </if>
        <if test="address != null and address !=''">
            and address like concat('%',#{address},'%')
        </if>
    </where>
</select>
```

**这样如果传入的user没有值，就是查全部，带一个值，就查这一个条件，带多个值就按照多个条件查询**

**可以在sql语句里面的每一个都写上and，不影响，mybatis会自动去除不需要的and**





### set标签

**有选择的进行更新处理,至少更新一列.能够保证如果没有传值进来,则数据库中的数据保持不变**

**如果没有任何参数传入  sql语句就会报错**

**这个时候sql语句是  update user  where id=?**

**没有要修改的字段**

**为什么要使用set?不使用set的情况下，加入我这里有一个记录  1001 周志雄  20**

**你修改的时候，只传入了age  不使用set语句的情况下，id和name会被置为null**



```java
<update id="updateBySet" parameterType="users">
    update users
    <set>
        <if test="userName != null and userName != ''">
            username = #{userName},
        </if>
        <if test="birthday != null">
            birthday = #{birthday},
        </if>
        <if test="sex != null and sex != ''">
            sex =#{sex},
        </if>
        <if test="address != null and address !=''">
            address = #{address}
        </if>
    </set>
    where id = #{id}
</update>
```

**我们只把传了参数的修改，没有参数的完全不修改**





### foreach标签

**用来进行循环遍历,完成循环条件查询,批量删除,批量增加,批量更新**

**主要用来进行集合或数组的遍历，主要有以下参数：**



**collection：collection 属性的值有三个分别是 list、array、map 三种**

**分别对应的参数类型为：List、数组、map 集合**



**item ：循环体中的具体对象。在list和数组中是其中的对象，在map中是value。**



**index ：在list和数组中,index是元素的序号，在map中，index是元素的key，该参数可选**



**open ：表示该语句以什么开始**

**close ：表示该语句以什么结束**

**separator ：表示元素之间的分隔符，例如在in()的时候，separator=","会自动在元素中间用“,“隔开**

**避免手动输入逗号导致sql错误，如in(1,2,)这样。该参数可选**



**根据id批量查询**

```xml
<select id="getByIds" resultType="users">
    select <include refid="columns"></include>
    from users
    where id in
        <foreach collection="list" item="id" separator="," open="(" close=")">
           #{id}
        </foreach>
</select>

```

**根据id批量删除**

```xml
    <delete id="deleteBatch" >
        delete from users
        where id in
        <foreach collection="array" item="id" separator="," open="(" close=")">
            #{id}
        </foreach>
    </delete>
```

**批量添加**

```xml
    <insert id="insertBatch">
        insert into users(username, birthday, sex, address) values
        <foreach collection="list" item="u" separator="," >
            (#{u.userName},#{u.birthday},#{u.sex},#{u.address})
        </foreach>
    </insert>
```

