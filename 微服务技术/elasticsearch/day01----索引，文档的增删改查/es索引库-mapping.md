### es索引库-mapping



#### **mapping是对索引库中文档的约束，常见的mapping属性包括：**



**1：数据类型**

**2：是否索引**

**3：分词器**

**4：子属性**



#### **•type：字段数据类型，常见的简单类型有：**

****

**•字符串：text（可分词的文本）、keyword（精确值，例如：品牌、国家、ip地址）**

**•数值：long、integer、short、byte、double、float、**

**•布尔：boolean**

**•日期：date**

**•对象：object**

****

#### **•index：是否创建索引，默认为true**

#### **•analyzer：使用哪种分词器**

#### **properties：该字段的子字段**

```json
{
  "age": 21,
  "weight": 52.1,
  "isMarried": false,
  "info": "黑马程序员Java讲师",  #text
  "email": "zy@itcast.cn",     #keyword
  "score": [99.1, 99.5, 98.9],
  "name": {
    "firstName": "云",
    "lastName": "赵"
  }
}
```

**mapping常见属性有哪些？**

#### **•type：数据类型**

****

**type常见的有哪些？**

**•字符串：text、keyword**

**•数字：long、integer、short、byte、double、float**

**•布尔：boolean**

**•日期：date**

**对象：object**

****

#### **•index：是否索引**

#### **•analyzer：分词器**

#### **•properties：子字段**



#### 创建索引库

**ES中通过Restful请求操作索引库、文档。请求内容用DSL语句来表示。创建索引库和mapping的DSL语法如下：**



```json
PUT /索引库名称
{
  "mappings": {
    "properties": {
      "字段名":{
        "type": "text",
        "analyzer": "ik_smart"
      },
      "字段名2":{
        "type": "keyword",
        "index": "false"
      },
      "字段名3":{
        "properties": {
          "子字段": {
            "type": "keyword"
          }
        }
      },
      // ...略
    }
  }
}
```

#### 示例：

```json
PUT /user
{
  "mappings":{
    "properties":{
      
      "info":{
        "type":"text",
        "analyzer":"ik_smart",
        "index":true
      },
      
      "email":{
        "type":"keyword",
        "index":false
      },
      
      "name":{
        "type":"object",
        "properties":{
          "firstName":{
            "type":"keyword",
            "index":true
          },
          "lastName":{
            "type":"keyword",
            "index":false
          }
        }
      }
    }
  }
}
```



#### 查看索引库

**GET /索引库名**

**GET  /user**



#### 删除索引库

**DELETE /索引库名**

**DELETE /user**



#### 修改索引库

**注意：并不是真正的修改，因为一旦确定了索引结构，就会创建倒排索引，再次修改的话，会导致不可用**

**这里的修改，就是单纯的添加另外的属性**

**索引库和mapping一旦创建无法修改，但是可以添加新的字段，语法如下**



```json
PUT /索引库名/_mapping
{
  "properties": {
    "新字段名":{
      "type": "integer"
    }
  }
}
```



**示例：**

```json
PUT /user/_mapping
{
  "properties":{
    "age":{
      "type":"integer"
    }
  }
}
```

