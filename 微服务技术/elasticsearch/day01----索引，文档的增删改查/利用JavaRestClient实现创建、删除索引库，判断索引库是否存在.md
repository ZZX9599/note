### 利用JavaRestClient实现创建、删除索引库，判断索引库是否存在



**根据课前资料提供的酒店数据创建索引库，索引库名为hotel，mapping属性根据数据库结构定义**

**基本步骤如下：**

**1.导入课前资料Demo**

**2.分析数据结构，定义mapping属性**

**3.初始化JavaRestClient**

**4.利用JavaRestClient创建索引库**

**5.利用JavaRestClient删除索引库**

**6.利用JavaRestClient判断索引库是否存在**



```sql
CREATE TABLE `tb_hotel` (

 `id` bigint(20) NOT NULL COMMENT '酒店id',

 `name` varchar(255) NOT NULL COMMENT '酒店名称；例：7天酒店',

 `address` varchar(255) NOT NULL COMMENT '酒店地址；例：航头路',

 `price` int(10) NOT NULL COMMENT '酒店价格；例：329',

 `score` int(2) NOT NULL COMMENT '酒店评分；例：45，就是4.5分',

 `brand` varchar(32) NOT NULL COMMENT '酒店品牌；例：如家',

 `city` varchar(32) NOT NULL COMMENT '所在城市；例：上海',

 `star_name` varchar(16) DEFAULT NULL COMMENT '酒店星级，从低到高分别是：1星到5星，1钻到5钻',

 `business` varchar(255) DEFAULT NULL COMMENT '商圈；例：虹桥',

 `latitude` varchar(32) NOT NULL COMMENT '纬度；例：31.2497',

 `longitude` varchar(32) NOT NULL COMMENT '经度；例：120.3925',

 `pic` varchar(255) DEFAULT NULL COMMENT '酒店图片；例:/img/1.jpg',

 PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```



**mapping要考虑的问题：**

**字段名、数据类型、是否参与搜索、是否分词、如果分词，分词器是什么？**



**ES中支持两种地理坐标数据类型：**



**•geo_point：由纬度（latitude）和经度（longitude）确定的一个点**

**值是字符串，但是类型是geo_point**

**例如："32.8752345, 120.2981576"**



**•geo_shape：有多个geo_point组成的复杂几何图形。例如一条直线，"LINESTRING (-77.03653 38.897676, -77.009051 38.889939)"**



```json
字段拷贝可以使用copy_to属性将当前字段拷贝到指定字段。示例：

"all": {
  "type": "text",
  "analyzer": "ik_max_word"
},
"brand": {
  "type": "keyword",
  "copy_to": "all"
}
```





```json
#酒店mapping

PUT /hotel
{
  "mappings": {
    "properties": {
      "id":{
        "type": "keyword"
      },
      "name":{
        "type": "text",
        "analyzer": "ik_max_word",
        "copy_to": "all"
      },
      "address":{
        "type": "keyword",
        "index": false
      },
      "price":{
        "type": "integer"
      },
      "score":{
        "type": "integer"
      },
      "brand":{
        "type": "keyword",
        "copy_to": "all"
      },
      "city":{
        "type": "keyword"
      },
      "starName":{
        "type": "keyword"
      },
      "business":{
        "type": "keyword",
        "copy_to": "all"
      },
      "location":{
        "type": "geo_point"
      },
      "pic":{
        "type": "keyword",
        "index": false
      },
      "all":{
        "type": "text",
        "analyzer": "ik_max_word"
      }
    }
  }
}
```

**copy_to：将多个可能参与搜索的字段，加入到一个all字段，相比多个字段搜索，加入到一个字段的搜素速度大大提升**





### 代码

**1：引入es的RestHighLevelClient依赖**

```xml
<dependency>
	<groupId>org.elasticsearch.client</groupId>
	<artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>7.12.1</version>
    <!--版本需要和es一致-->
</dependency>
```



**2：因为SpringBoot默认的ES版本是7.6.2，所以我们需要覆盖默认的ES版本**：

![es](E:\笔记整理\微服务技术\图解\es.png)

```xml
    <properties>
        <java.version>1.8</java.version>
        <elasticsearch.version>7.12.1</elasticsearch.version>
    </properties>
```



**3：初始化RestHighLevelClient：**

```java
package cn.itcast.hotel;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author ZZX
 * @date 2022/5/30 16:23
 */
@SpringBootTest
public class MyTest {
   
    private RestHighLevelClient client;

    /**
     * 初始化客户端
     */
    @Before
    public void setUp(){
        this.client=new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.220.147:9200")
        ));
    }

    /**
     * 销毁客户端
     * @throws IOException
     */
    @After
    public void close() throws IOException {
        this.client.close();
    }

    @Test
    public void testInit(){
        System.out.println(client);
    }
}

```





#### 创建索引库

![java创建索引库](E:\笔记整理\微服务技术\图解\java创建索引库.png)



#### **需要写的DSL语句**

```java
package common;

/**
 * @author ZZX
 * @date 2022/5/30 16:45
 */
public class HotelCommon {
    public static final String MAPPING_TEMPLATE="{\n" +
            "  \"mappings\": {\n" +
            "    \"properties\": {\n" +
            "      \"id\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"name\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"address\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"price\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"score\":{\n" +
            "        \"type\": \"integer\"\n" +
            "      },\n" +
            "      \"brand\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"city\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"starName\":{\n" +
            "        \"type\": \"keyword\"\n" +
            "      },\n" +
            "      \"business\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"copy_to\": \"all\"\n" +
            "      },\n" +
            "      \"location\":{\n" +
            "        \"type\": \"geo_point\"\n" +
            "      },\n" +
            "      \"pic\":{\n" +
            "        \"type\": \"keyword\",\n" +
            "        \"index\": false\n" +
            "      },\n" +
            "      \"all\":{\n" +
            "        \"type\": \"text\",\n" +
            "        \"analyzer\": \"ik_max_word\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";
}
```



#### 创建索引库

```java
@Test
public void createHotelIndex() throws IOException {
    //创建Request对象，创建索引名称
    CreateIndexRequest request=new CreateIndexRequest("hotel");

    //准备请求的参数：DSL语句，使用JSON类型
    request.source(HotelCommon.MAPPING_TEMPLATE, XContentType.JSON);

    //发送请求
    client.indices().create(request, RequestOptions.DEFAULT);
}
```



#### 删除索引库

```java
@Test
public void deleteIndex() throws IOException {
    //创建Request对象，创建索引名称
    DeleteIndexRequest request=new DeleteIndexRequest("hotel");

    //发送请求
    client.indices().delete(request,RequestOptions.DEFAULT);
}
```





#### 查看索引库是否存在

```java
@Test
public void getIndexExists() throws IOException {
    //创建Request对象，创建索引名称
    GetIndexRequest request=new GetIndexRequest("hotel");

    //发送请求
    boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);

    System.out.println(exists);
}
```





**索引库操作的基本步骤：**

**•初始化RestHighLevelClient**

**•创建XxxIndexRequest。XXX是Create、Get、Delete**

**•准备DSL（ Create时需要）**

**发送请求。调用RestHighLevelClient#indices().xxx()方法，xxx是create、exists、delet**

**都是调用--->client.indices()**