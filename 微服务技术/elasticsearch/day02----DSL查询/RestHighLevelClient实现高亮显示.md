### RestHighLevelClient实现高亮显示



![gaolianggoujain](E:\笔记整理\微服务技术\图解\DSL对应关系\gaolianggoujain.png)

```java
@SpringBootTest
public class TestDslHighLight {
    @Resource
    private RestHighLevelClient client;

    /**
     * 高亮处理，只处理name
     * @throws IOException
     */
    @Test
    public void testHighLight() throws IOException {
        SearchRequest request=new SearchRequest("hotel");
        request.source().query(QueryBuilders.matchQuery("all","如家"));
        request.source().highlighter(new HighlightBuilder().field("name").requireFieldMatch(false));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        HotelResultType hotelResultType = HandleResultUtil.handleResult(response);
        System.out.println("数据总数:"+hotelResultType.getTotal());
        System.out.println("数据如下:"+hotelResultType.getHotels());
    }
}
```



### 结果处理：

![gaoliangjieguo](E:\笔记整理\微服务技术\图解\DSL对应关系\gaoliangjieguo.png)



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