### 利用JavaRestClient实现文档的CRUD



**去数据库查询酒店数据，导入到hotel索引库，实现酒店数据的CRUD。**

**基本步骤如下：**

**1.初始化JavaRestClient**

**2.利用JavaRestClient新增酒店数据**

**3.利用JavaRestClient根据id查询酒店数据**

**4.利用JavaRestClient删除酒店数据**

**利用JavaRestClient修改酒店数据**





![doc](E:\笔记整理\微服务技术\图解\doc.png)





#### 新增文档代码

```java
package cn.itcast.hotel;

import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @author ZZX
 * @date 2022/5/31 9:25
 */
@SpringBootTest
public class TestDoc {
    private RestHighLevelClient client;

    @Resource
    private IHotelService service;

    /**
     * 初始化客户端
     */
    @BeforeEach
    public void setUp(){
        this.client=new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.220.147:9200")
        ));
    }

    /**
     * 销毁客户端
     * @throws IOException
     */
    @AfterEach
    public void close() throws IOException {
        this.client.close();
    }

    @Test
    public void testInit(){
        System.out.println(client);
    }

    @Test
    public void testAddDoc() throws IOException {

        // 1.查询数据库hotel数据
        Hotel hotel = service.getById(61083L);
        // 2.转换为HotelDoc
        HotelDoc hotelDoc = new HotelDoc(hotel);
        // 3.转JSON
        String json = JSON.toJSONString(hotelDoc);

        // 1.准备Request
        IndexRequest request = new IndexRequest("hotel").id(hotelDoc.getId().toString());
        // 2.准备请求参数DSL，其实就是文档的JSON字符串
        request.source(json, XContentType.JSON);
        // 3.发送请求
        client.index(request, RequestOptions.DEFAULT);
    }
}
```



#### 查询文档代码

```java
@Test
public void testQueryDoc() throws IOException {
     //创建GetRequest对象
     //参数一：索引  参数二：id
     GetRequest request=new GetRequest("hotel","61083");
     //发起请求
     GetResponse response = client.get(request, RequestOptions.DEFAULT);
     //响应结果
     String json = response.getSourceAsString();
     HotelDoc hotelDoc = JSON.parseObject(json, HotelDoc.class);
     System.out.println(hotelDoc);
}
```



#### 更新文档

```java
@Test
public void testUpdateDoc() throws IOException {
     //准备UpdateRequest
     UpdateRequest request=new UpdateRequest("hotel","61083");
     //准备参数，把价格更新为999，星级更新
     request.doc(
           "price","999",
           "starName","四钻"
     );
     //发送请求
     client.update(request,RequestOptions.DEFAULT);
}
```



#### 删除文档

```java
@Test
public void testDeleteDoc() throws IOException {
     //准备UpdateRequest
     DeleteRequest request=new DeleteRequest("hotel","61083");
     //发送请求
     client.delete(request,RequestOptions.DEFAULT);
}
```



**文档操作的基本步骤：**

**•初始化RestHighLevelClient**

**•创建XxxRequest，XXX是Index、Get、Update、Delete**

**•准备参数（Index和Update时需要）**

**•发送请求。调用RestHighLevelClient#.xxx()方法，xxx是index、get、update、delete**

**解析结果（Get时需要）**