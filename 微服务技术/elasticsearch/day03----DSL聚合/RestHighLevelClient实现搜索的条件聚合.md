### RestHighLevelClient实现搜索的条件聚合



**参数构建：**

```java
package com.zzx.utils;

import com.zzx.domain.HotelRequestParam;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.common.geo.GeoPoint;
import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.util.StringUtils;

/**
 * @Author: ZZX
 * @Date: 2022/7/12 9:33
 */
public class HandelParamUtil {
    public static SearchRequest handleParam(HotelRequestParam hotelRequestParam){
        //取参数
        String key = hotelRequestParam.getKey();
        Integer page = hotelRequestParam.getPage();
        Integer size = hotelRequestParam.getSize();
        String city = hotelRequestParam.getCity();
        String starName = hotelRequestParam.getStarName();
        String brand = hotelRequestParam.getBrand();
        Integer minPrice = hotelRequestParam.getMinPrice();
        Integer maxPrice = hotelRequestParam.getMaxPrice();
        String location = hotelRequestParam.getLocation();

        //构建request对象
        SearchRequest request=new SearchRequest("hotel");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        //封装参数
        if (!StringUtils.isEmpty(key)&&!"".equals(key)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("all",key));
        }else {
            boolQueryBuilder.must(QueryBuilders.matchAllQuery());
        }
        if (!StringUtils.isEmpty(city)&&!"".equals(city)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("city",city));
        }
        if (!StringUtils.isEmpty(brand)&&!"".equals(brand)) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("brand",brand));
        }
        if (!StringUtils.isEmpty(starName)&&!"".equals(starName)) {
            boolQueryBuilder.must(QueryBuilders.termQuery("starName",starName));
        }
        if(minPrice!=null&&maxPrice!=null){
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(minPrice).lte(maxPrice));
        }
        //根据距离来排序
        if (!StringUtils.isEmpty(location)&&!"".equals(location)) {
            request.source().sort(SortBuilders
                    .geoDistanceSort("location",new GeoPoint(location))
                    .unit(DistanceUnit.KILOMETERS)
                    .order(SortOrder.ASC)
            );
        }
        request.source().from((page-1)*size).size(size);

        request.source().query(boolQueryBuilder);

        //boolQueryBuilder:原始查询
        FunctionScoreQueryBuilder functionScoreQueryBuilder =
                QueryBuilders.functionScoreQuery(boolQueryBuilder,
                //具体的functionScore
                new FunctionScoreQueryBuilder.FilterFunctionBuilder[]{
                        //过滤条件
                        new FunctionScoreQueryBuilder.FilterFunctionBuilder(
                                QueryBuilders.termQuery("isAD",true),
                                //算分函数
                                ScoreFunctionBuilders.weightFactorFunction(1000)
                        )
                });

        return request;
    }
}
```



**处理：**

```java
package com.zzx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.domain.Hotel;
import com.zzx.domain.HotelRequestParam;
import com.zzx.domain.HotelResultType;
import com.zzx.mapper.HotelMapper;
import com.zzx.service.HotelService;
import com.zzx.utils.HandelParamUtil;
import com.zzx.utils.HandleResultUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZZX
 * @Date: 2022/7/11 23:06
 */

@Service
public class HotelServiceImpl extends ServiceImpl<HotelMapper, Hotel> implements HotelService {

    @Resource
    private RestHighLevelClient client;

    @Override
    public HotelResultType search(HotelRequestParam hotelRequestParam) throws IOException {
        SearchRequest request = HandelParamUtil.handleParam(hotelRequestParam);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        return HandleResultUtil.handleResult(response);
    }

    @Override
    public Map<String, List<String>> filters(HotelRequestParam hotelRequestParam) throws IOException {
        Map<String,List<String>>result=new HashMap<>();

        SearchRequest request=new SearchRequest("hotel");

        //封装请求参数
        request= HandelParamUtil.handleParam(hotelRequestParam);

        //组装参数
        buildAgg(request);

        SearchResponse response = client.search(request, RequestOptions.DEFAULT);

        Aggregations aggregations = response.getAggregations();
        
        List<String> cityList = getAggKeyList(aggregations, "cityAgg");
        result.put("city",cityList);

        List<String> brandList = getAggKeyList(aggregations, "brandAgg");
        result.put("brand",brandList);

        List<String> starList = getAggKeyList(aggregations, "starAgg");
        result.put("starName",starList);

        return result;
    }

    /**
     * 结果解析
     * @param aggregations
     * @param aggName
     * @return
     */
    private List<String> getAggKeyList(Aggregations aggregations,String aggName) {
        Terms cityTerm = aggregations.get(aggName);
        List<? extends Terms.Bucket> cityBucket = cityTerm.getBuckets();
        List<String> aggList =new ArrayList<>();
        for (Terms.Bucket bucket : cityBucket) {
            String name = bucket.getKeyAsString();
            aggList.add(name);
        }
        return aggList;
    }

    /**
     * 参数构建
     * @param request
     */
    private void buildAgg(SearchRequest request) {
        request.source().aggregation(AggregationBuilders
                .terms("cityAgg")
                .field("city")
                .size(20));
        request.source().aggregation(AggregationBuilders
                .terms("brandAgg")
                .field("brand")
                .size(20));
        request.source().aggregation(AggregationBuilders
                .terms("starAgg")
                .field("starName")
                .size(20));
    }
}
```





**效果：**

![效果](E:\笔记整理\微服务技术\图解\DSL对应关系\效果.png)



**在没有点击的时候，默认查询全部，聚合整个索引，显示了所有的聚合信息**

**点击了一个条件之后，把这个条件的查询结果再进行聚合**

**也就是假设上海只有800以上的酒店，我点击了价格300-600，上海就会消失，因为调用了请求**

**把800以上的条件进行聚合，这个时候按照city聚合，就不会出现上海的选项了**

