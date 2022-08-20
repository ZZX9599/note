### RestHighLevelClient实现排序



![paixufenye](E:\笔记整理\微服务技术\图解\DSL对应关系\paixufenye.png)

```java
package com.zzx;

import com.zzx.domain.HotelResultType;
import com.zzx.utils.HandleResultUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: ZZX
 * @Date: 2022/7/12 8:54
 */
@SpringBootTest
public class TestDslSort {
    @Resource
    private RestHighLevelClient client;

    //排序
    @Test
    public void testSort() throws IOException {
        SearchRequest request=new SearchRequest("hotel");
        request.source().sort("price", SortOrder.DESC);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        HotelResultType hotelResultType = HandleResultUtil.handleResult(response);
        System.out.println("数据总数:"+hotelResultType.getTotal());
        System.out.println("数据如下:"+hotelResultType.getHotels());
    }
}
```