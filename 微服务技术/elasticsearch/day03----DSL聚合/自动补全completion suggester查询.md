### a自动补全completion suggester查询

**elasticsearch提供了[Completion Suggester](https://www.elastic.co/guide/en/elasticsearch/reference/7.6/search-suggesters.html)查询来实现自动补全功能**

**这个查询会匹配以用户输入内容开头的词条并返回。**

**为了提高补全查询的效率，对于文档中字段的类型有一些约束：**

**•参与补全查询的字段必须是completion类型。**

**•字段的内容一般是用来补全的多个词条形成的数组。**





#### 创建索引库和添加数据

```apl
// 创建索引库
PUT test
{
  "mappings": {
    "properties": {
      "title":{
        "type": "completion"
      }
    }
  }
}
```



```apl
// 示例数据
POST test/_doc
{
  "title": ["Sony", "WH-1000XM3"]
}
POST test/_doc
{
  "title": ["SK-II", "PITERA"]
}
POST test/_doc
{
  "title": ["Nintendo", "switch"]
}
```



#### 查询

```apl
// 自动补全查询
GET /test/_search
{
  "suggest": {
    "title_suggest": {
      "text": "s", // 关键字
      "completion": {
        "field": "title", // 补全查询的字段
        "skip_duplicates": true, // 跳过重复的
        "size": 10 // 获取前10条结果
      }
    }
  }
}
```



**自动补全对字段的要求：**

**•类型是completion类型**

**•字段值是多词条的数组**