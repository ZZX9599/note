### DSL实现Metrics聚合



**对所有酒店的评分求平均值：**

```apl
GET /hotel/_search
{
  "size": 0,
  "aggs": {
    "brandAgg": {
      "avg": {
        "field": "score"
      }
    }
  }
}
```





**获取每个品牌的用户评分的min、max、avg等值.**

**因为有限定条件，获取每个酒店的评分，所以要和桶聚合一起使用**

```apl
GET /hotel/_search
{
  "size": 0,
  "aggs": {
    "brandAgg": {
      "terms": {
        "field": "brand",
        "size": 10,
        "order": {
          "scoreAgg.avg": "desc"
        }
      },
      "aggs": {
        "scoreAgg": {
          "stats": {
            "field": "score"
          }
        }
      }
    }
  }
}
```

