### RestHighLevelClient实现聚合



![pinpaijuhe](E:\笔记整理\微服务技术\图解\DSL对应关系\pinpaijuhe.png)



### ![jieguojuhe](E:\笔记整理\微服务技术\图解\DSL对应关系\jieguojuhe.png)



```java
@SpringBootTest
public class TestDslAgg {

    @Resource
    private RestHighLevelClient client;

    @Test
    public void testBucketTerm() throws IOException {
        SearchRequest request=new SearchRequest("hotel");
        request.source().size(0);
        request.source().aggregation(AggregationBuilders
                .terms("brandAgg")
                .field("brand")
                .size(10));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        Aggregations aggregations = response.getAggregations();
        Terms terms = aggregations.get("brandAgg");
        List<? extends Terms.Bucket> buckets = terms.getBuckets();
        for (Terms.Bucket bucket : buckets) {
            String keyAsString = bucket.getKeyAsString();
            long docCount = bucket.getDocCount();
            System.out.println("酒店品牌:"+keyAsString+"\n"+"数量:"+docCount);
        }
    }
}
```