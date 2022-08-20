### RestHighLevelClient实现基本查询



### 无条件全文查询

![match-all](E:\笔记整理\微服务技术\图解\DSL对应关系\match-all.png)

```java
@SpringBootTest
public class TestDslSearch {
    @Resource
    private RestHighLevelClient client;

    //查询所有数据-match_all
    @Test
    public void testMatchAll() throws IOException {
        SearchRequest request = new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchAllQuery());
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        HotelResultType hotelResultType = HandleResultUtil.handleResult(response);
        System.out.println("数据总数:"+hotelResultType.getTotal());
        System.out.println("数据如下:"+hotelResultType.getHotels());
    }
}
```



### 结果的解析：

![jieguo](E:\笔记整理\微服务技术\图解\DSL对应关系\jieguo.png)



```java
public class HandleResultUtil {
    public static HotelResultType handleResult(SearchResponse response){
        long length = response.getHits().getTotalHits().value;
        List<HotelDoc> list=new ArrayList<>();
        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            String sourceAsString = hit.getSourceAsString();
            HotelDoc hotelDoc = JSON.parseObject(sourceAsString, HotelDoc.class);
            //处理高亮结果
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (!CollectionUtils.isEmpty(highlightFields)) {
                HighlightField field = highlightFields.get("name");
                Text fragment = field.getFragments()[0];
                String string = fragment.string();
                hotelDoc.setName(string);
            }
            Object[] sortValues = hit.getSortValues();
            if(sortValues.length>0){
                Object sortValue = sortValues[0];
                hotelDoc.setDistance(sortValue);
            }
            list.add(hotelDoc);
        }
        HotelResultType hotelResultType = new HotelResultType();
        hotelResultType.setTotal(length);
        hotelResultType.setHotels(list);
        return hotelResultType;
    }
}
```



### 全文检索查询



```java
	//全文检索查询数据-match
    @Test
    public void testMatch() throws IOException {
        SearchRequest request=new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchQuery("all","北京"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        HotelResultType hotelResultType = HandleResultUtil.handleResult(response);
        System.out.println("数据总数:"+hotelResultType.getTotal());
        System.out.println("数据如下:"+hotelResultType.getHotels());
    }
```



### 精确查询

```java
	//精准查询-term
    @Test
    public void testTerm() throws IOException {
        SearchRequest request=new SearchRequest("hotel");
        request.source().query(QueryBuilders.termQuery("city","北京"));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        HotelResultType hotelResultType = HandleResultUtil.handleResult(response);
        System.out.println("数据总数:"+hotelResultType.getTotal());
        System.out.println("数据如下:"+hotelResultType.getHotels());
    }
```



### 范围查询

```java
	//范围查询-range
    @Test
    public void testRange() throws IOException {
        SearchRequest request=new SearchRequest("hotel");
        request.source().query(QueryBuilders.rangeQuery("price").gte(1800));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        HotelResultType hotelResultType = HandleResultUtil.handleResult(response);
        System.out.println("数据总数:"+hotelResultType.getTotal());
        System.out.println("数据如下:"+hotelResultType.getHotels());
    }
```



### 组合查询

```java
    //组合查询-bool
    @Test
    public void testBool() throws IOException {
        SearchRequest request=new SearchRequest("hotel");
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(QueryBuilders.termQuery("city","北京"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(1000));
        boolQueryBuilder.filter(QueryBuilders.matchQuery("all","王府井"));
        request.source().query(boolQueryBuilder);
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        HotelResultType hotelResultType = HandleResultUtil.handleResult(response);
        System.out.println("数据总数:"+hotelResultType.getTotal());
        System.out.println("数据如下:"+hotelResultType.getHotels());
    }
```

