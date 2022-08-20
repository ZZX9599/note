### DDL语句

**Data Definition Language，数据定义语言，用来定义数据库对象(数据库，表，字段) **



#### **数据库操作**

```
查询所有的数据库：show databases;
查询当前的数据库：select database();
创建数据库：create database [if not exists] 数据库名 [default charset 字符集] [collate 排序规则];
删除数据库：drop database [if exists] 数据库名;
```

**注意：在数据库里面不建议使用utf8，因为只能存储三个字节，但是有的特殊字符是四个字节，所以一般设置为utf8mb4**





#### 表操作

```tex
查询所有的表：show tables;
查看指定表结构：desc 表名;
通过这条指令，我们可以查看到指定表的字段，字段的类型、是否可以为NULL，是否存在默认值等信息
查询指定表的建表语句以及存储引擎和字符集：show create table 表名
创建表结构:create table (
	字段1 类型 comment '备注信息',
	字段2 类型 comment '备注信息',
	字段3 类型 comment '备注信息',
	字段4 类型 comment '备注信息'
)
```

