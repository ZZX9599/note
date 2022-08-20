### 批量导入数据到es里



**需求：批量查询酒店数据，然后批量导入索引库中**

**思路：**

**1.利用mybatis-plus查询酒店数据**

**2.将查询到的酒店数据（Hotel）转换为文档类型数据（HotelDoc）**

**3.利用JavaRestClient中的Bulk批处理，实现批量新增文档，示例代码如下**

**bulk：块**



#### 代码如下

```java
package cn.itcast.hotel;

import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
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
import java.util.List;

/**
 * @Author: ZZX
 * @Date: 2022/7/10 18:00
 * 批量导入数据库的数据到es里面
 */

@SpringBootTest
public class BulkAddDocTest {
    public RestHighLevelClient client;

    @Resource
    private IHotelService iHotelService;

    @BeforeEach
    public void before(){
        this.client=new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.220.148:9200")
        ));
    }

    @AfterEach
    public void after() throws IOException {
        this.client.close();
    }

    @Test
    public void testAddBulkDoc() throws IOException {
        // 1.创建对象【不需要参数，只是封装参数】
        BulkRequest request=new BulkRequest();

        // 2.批量添加请求
        // request.add(new IndexRequest("hotel").id("61083").source("1", XContentType.JSON));
        // request.add(new IndexRequest("hotel").id("61083").source("2", XContentType.JSON));
        // request.add(new IndexRequest("hotel").id("61083").source("3", XContentType.JSON));
        // request.add(new IndexRequest("hotel").id("61083").source("4", XContentType.JSON));

        // 查询并添加所有数据
        List<Hotel> list = iHotelService.list();
        for(int i=0;i<list.size();i++){
            Hotel hotel = list.get(i);
            HotelDoc hotelDoc=new HotelDoc(hotel);

            request.add(new IndexRequest("hotel")
                    .id(hotelDoc.getId().toString())
                    .source(JSON.toJSONString(hotelDoc),XContentType.JSON));
        }
        client.bulk(request, RequestOptions.DEFAULT);
    }

    @Test
    public void testSelectBulkDoc() throws IOException {
        List<Hotel> list = iHotelService.list();
        System.err.println("酒店数据总数:"+list.size());
        for(int i=1;i<=list.size();i++){
            Hotel hotel = list.get(i-1);
            GetResponse response = client.get(
                    new GetRequest("hotel", hotel.getId().toString()), RequestOptions.DEFAULT);
            String sourceAsString = response.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(sourceAsString, HotelDoc.class);
            System.out.println("酒店数据"+i+"=="+hotelDoc);
        }
    }
}
```



**使用GET  /hotel/_search就可以查询到全部的数据了**

