### DSL查询基本语法



**DSL查询的话也就是只有查询类型，查询条件，条件值会改变**

```json
GET /indexName/_search
{
  "query": {
    "查询类型": {
      "查询条件": "条件值"
    }
  }
}
```



```json
// 查询所有
GET /indexName/_search
{
  "query": {
    "match_all": {
    }
  }
}
```
