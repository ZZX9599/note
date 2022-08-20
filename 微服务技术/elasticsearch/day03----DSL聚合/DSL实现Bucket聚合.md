### DSL实现Bucket聚合



**现在，我们要统计所有数据中的酒店品牌有几种，此时可以根据酒店品牌的名称做聚合。**

**类型为term类型，DSL示例：**



#### 1.实现基本聚合

```apl
GET /hotel/_search
{
  "size": 0,  // 设置size为0，结果中不包含文档，只包含聚合结果
  "aggs": { // 定义聚合
    "brandAgg": { //给聚合起个名字
      "terms": { // 聚合的类型，按照品牌值聚合，所以选择term
        "field": "brand", // 参与聚合的字段
        "size": 20 // 希望获取的聚合结果数量
      }
    }
  }
}
```

**结果：**

```json
{
  "took" : 12,
  "timed_out" : false,
  "_shards" : {
    "total" : 1,
    "successful" : 1,
    "skipped" : 0,
    "failed" : 0
  },
  "hits" : {
    "total" : {
      "value" : 201,
      "relation" : "eq"
    },
    "max_score" : null,
    "hits" : [ ]
  },
  "aggregations" : {
    "brandAgg" : {
      "doc_count_error_upper_bound" : 0,
      "sum_other_doc_count" : 0,
      "buckets" : [
        {
          "key" : "7天酒店",
          "doc_count" : 30
        },
        {
          "key" : "如家",
          "doc_count" : 30
        },
        {
          "key" : "皇冠假日",
          "doc_count" : 17
        },
        {
          "key" : "速8",
          "doc_count" : 15
        },
        {
          "key" : "万怡",
          "doc_count" : 13
        },
        {
          "key" : "华美达",
          "doc_count" : 13
        },
        {
          "key" : "和颐",
          "doc_count" : 12
        },
        {
          "key" : "万豪",
          "doc_count" : 11
        },
        {
          "key" : "喜来登",
          "doc_count" : 11
        },
        {
          "key" : "希尔顿",
          "doc_count" : 10
        },
        {
          "key" : "汉庭",
          "doc_count" : 10
        },
        {
          "key" : "凯悦",
          "doc_count" : 8
        },
        {
          "key" : "维也纳",
          "doc_count" : 7
        },
        {
          "key" : "豪生",
          "doc_count" : 6
        },
        {
          "key" : "君悦",
          "doc_count" : 4
        },
        {
          "key" : "万丽",
          "doc_count" : 2
        },
        {
          "key" : "丽笙",
          "doc_count" : 2
        }
      ]
    }
  }
}

```

**观察到了：brandAgg【我们定义的聚合名称】buckets【桶类型。把一个名称的酒店放进一个桶】**



#### **2.Bucket聚合结果排序**

**默认情况下，Bucket聚合会统计Bucket内的文档数量，记为_count，并且按照_count降序排序。**

**我们可以修改结果排序方式：**



```apl
GET /hotel/_search
{
  "size": 0, 
  "aggs": {
    "brandAgg": {
      "terms": {
        "field": "brand",
        "order": {
          "_count": "asc" // 按照_count升序排列
        },
        "size": 20
      }
    }
  }
}
```





#### 3.**Bucket**聚合限定聚合范围

```apl
#基本聚合，限定范围
GET /hotel/_search
{
  "query": {
    "range": {
      "price": {
        "gte": 100,
        "lte": 300
      }
    }
  }, 
  "size": 0,
  "aggs": {
    "brandAgg": {
      "terms": {
        "field": "brand",
        "size": 10,
        "order": {
          "_count": "asc"
        }
      }
    }
  }
}
```

