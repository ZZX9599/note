### Redis的Hash类型

**redis hash 是一个 string 类型的 field 和 value 的映射表，hash 特别适合用于存储对象。**



#### 1）：hset

**语法：hset  hash表的key   field   value **

**作用：将哈希表 key 中的域 field 的值设为 value，如果 key 不存在**

**则新建 hash 表，执行 赋值，如果有 field ,则覆盖值。 **

**返回值： **

**①如果 field 是 hash 表中新 field，且设置值成功，返回 1 **

**②如果 field 已经存在，旧值覆盖新值，返回 0**

**举例：hset website baidu www.baidu.com**



#### 2）：hget

**语法：hget  key  field **

**作用：获取哈希表 key 中给定域 field 的值 **

**返回值：field 域的值，如果 key 不存在或者 field 不存在返回 nil**

**例如：hget website baidu**



#### 3）：hmset

**语法：hmset key field value [field value…] **

**说明：同时将多个 field-value (域-值)设置到哈希表 key 中，此命令会覆盖已经存在的 field **

**hash 表 key 不存在，创建空的 hash 表，执行 hmset. **

**返回值：设置成功返回 ok，如果失败返回一个错误**

**举例：hmset web baidu www.baidu.com address beijing**



#### 4）：hmget

**语法：hmget key field [field…] **

**作用:获取哈希表 key 中一个或多个给定域的值 **

**返回值：返回和 field 顺序对应的值**

**如果 field 不存在，返回 nil**



#### 5）：hgetall

**语法：hgetall key **

**作用：获取哈希表 key 中所有的域和值 **

**返回值：以列表形式返回 hash 中域和域的值**

**key 不存在，返回空 hash**

**举例：hmset fruit apple apple1 banane banane1**

**hgetall fruit结果如下**

**apple**

**apple1**

**banane**

**banane1**



#### 5）：hdel

**语法：hdel key field [field…] **

**作用：删除哈希表 key 中的一个或多个指定域 field，不存在 field 直接忽略 **

**返回值：成功删除的 field 的数量**

**hmset num n1 v1 n2 v2 n3 v3 n4 v4**

**hdel num n1 n2 n4**

**hgetall num    结果是   n3 v3**



#### 6）：hkeys

**语法：hkeys key **

**作用：查看哈希表 key 中的所有 field 域 **

**返回值：包含所有 field 的列表，key 不存在返回空列表**

**hmset website baidu www.baidu.com wangyi www.wangyi.com**

**hkeys website   ----->  baidu  wangyi**



#### 7）：hvals

**语法：hvals key **

**作用：返回哈希表中所有域的值 **

**返回值：包含哈希表所有域值的列表，key 不存在返回空列表**

**hmset website baidu www.baidu.com wangyi www.wangyi.com**

**hvals website   ----->  www.baidu.com   www.wangyi.com**



#### 8）：hexists

**语法：hexists key field **

**作用：查看哈希表 key 中，给定域 field 是否存在 **

**返回值：如果 field 存在，返回 1，其他返回 0**

**hmset num n1 v1 n2 v2**

**hexists num n1  --->  1**

