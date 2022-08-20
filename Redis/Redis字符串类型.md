### Redis字符串类型

**字符串类型是 Redis 中最基本的数据类型，它能存储任何形式的字符串，包括二进制数据，序列化后的数据，JSON 格式数据。**



#### 1）：set

**将字符串值 value 设置到 key 中 语法：set key value**

**例如   set name zhouzhixiong**

**向已经存在的 key 设置新的 value，会覆盖原来的值**



#### 2）：get

**获取 key 中设置的字符串值 语法：get key **

**例如：获取 name 这个 key 对应的 value    **

**get name**



#### 3）：incr

**将 key 中储存的数字值加 1，如果 key 不存在，则 key 的值先被初始化为 0 再执行 incr 操作（只能对数字类型的数据操作） **

**语法：incr key**

**例如  set num 100       incr num    get num    就会得到101**



#### 4）：decr

**将 key 中储存的数字值减1，如果 key 不存在，则么 key 的值先被初始化为 0 再执 行 decr 操作（只能对数字类型的数据操作）**

**语法：decr key**

**例如：set num 100    decr num   get num   就会得到99**

#### incr ，decr 在实现关注人数上，文章的点击数上。



#### 5）：append

**语法：append key value**

**说明：如果 key 存在，则将 value 追加到 key 原来旧值的末尾 **

**如果 key 不存在，则将 key 设置值为 value **

**返回值：追加字符串之后的总长度**

**例如：set name z    appand name zx    get name -->zzx**

**例如：set name zzx    appand age 20   get age-->20 **



#### 6）：strlen

**语法：strlen key **

**说明：返回 key 所储存的字符串值的长度 **

**返回值： **

**①：如果key存在，返回字符串值的长度 **

**②：key不存在，返回0**



#### 7）：getrange

**语法：getrange key start end **

**作用：获取 key 中字符串值从 start 开始到 end 结束的子字符串,包括 start 和 end**

**负数表 示从字符串的末尾开始，-1 表示最后一个字符返回值：截取的子字符串。 **

**举例：set address sichuanguangan**

**getrange address 0,-1**

**一般都这样来获取全部字符串**



#### 8）：setrange

**语法：setrange key offset value **

**说明：用 value 覆盖（替换）key 的存储的值从 offset 开始,不存在的 key 做空白字符串。 **

**返回值：修改后的字符串的长度**



#### 9）：mset

**语法：mset key value [key value…] **

**说明：同时设置一个或多个 key-value 对 返回值：OK**

**举例：mset k1 v1 k2 v2 k3 v3**



#### 10）：mget

**语法：mget key [key …] **

**作用：获取所有(一个或多个)给定 key 的值 返回值：包含所有 key 的列表**

**举例：mget num1 num2 num3 num3**

**如果key不存在 就返回(nil)**



**以上的增加减少还有另外的两个方法**

**incrby    decrby**

**语法：incrby/decrby   key   offset**

**功能：将 key 所储存的值加上减少增量值，如果 key 不存在，则 key 的值先被初始化为 0 再执行 INCRBY 命令。**

**返回值：返回增量之后的key值。**