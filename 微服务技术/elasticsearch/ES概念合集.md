### ES概念合集



**ES：ES就是elasticsearch的缩写**



**文档：每一条数据就是一个文档**

**词条：对文档中的内容分词，得到的词语就是词条**



**什么是正向索引？**

**基于文档id创建索引。查询词条时必须先找到文档，而后判断是否包含词条**



**什么是倒排索引？**

**对文档内容分词，对词条创建索引，并记录词条所在文档的信息。**

**查询时先根据词条查询到文档id，而后获取到文档**



**索引：相同类型的文档的集合：类似数据库的表**

**映射：索引中文档的字段约束信息，类似表的结构约束**



![mysql对比es](E:\笔记整理\微服务技术\图解\mysql对比es.png)



**Mysql：擅长事务类型操作，可以确保数据的安全和一致性**

**Elasticsearch：擅长海量数据的搜索、分析、计算**

**文档：一条数据就是一个文档，es中是Json格式**

**字段：Json文档中的字段**

**索引：同类型文档的集合**

**映射：索引中文档的约束，比如字段名称、类型**

**elasticsearch与数据库的关系：**

**数据库负责事务类型操作**

**elasticsearch负责海量数据的搜索、分析、计算**