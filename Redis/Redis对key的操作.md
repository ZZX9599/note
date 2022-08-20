### Redis对key的操作



#### 1)：keys parrern

**keys pattern 作用：查找所有符合模式 pattern 的 key         pattern 可以使用通配符。**

***：表示 0-多个字符，例如：keys * 查询所有的 key。 **

**？：表示单个字符，例如：wo?d , 匹配 word , wood**



#### 2）：exists key [key…]

**作用：判断 key 是否存在 返回值：整数**

**存在 key 返回 1，不存在则返回 0**

**使用多个 key，返回存在的 key 的数量。**



#### 3）：expire key seconds

**作用：设置 key 的生存时间，超过时间，key 自动删除。单位是秒。**

**返回值：设置成功返回数字 1，其他情况是 0 。**



#### 4）：ttl key

**作用：以秒为单位，返回 key 的剩余生存时间**

**返回值：**

**⚫ -1 ：没有设置 key 的生存时间， key 永不过期**

**⚫ -2：key 不存在 **

**⚫ 数字：key 的剩余时间，秒为单位**



#### 5）：type key

**查看 key 所存储值的数据类型 返回值：字符串表示的数据类型 **

**⚫ none (key 不存在) **

**⚫ string (字符串)**

**⚫ list (列表)**

**⚫ set (集合) **

**⚫ zset (有序集) **

**⚫ hash (哈希表)**



#### 6）：del key [key…]

**作用：删除存在的 key，不存在的 key 忽略。 **

**返回值：数字，删除的 key 的数量。**



#### 7）：move key index

**把数据移动到某个库里面去，本来的库被删除**

**返回值：移动成功返回1，失败返回0**



#### 8）：rename key newname

**作用：将key改为名newkey**

**当 key 和 newkey 相同，或者 key 不存在时，返回一个错误。**

**当 newkey 已经存在时， RENAME 命令将覆盖旧值**

