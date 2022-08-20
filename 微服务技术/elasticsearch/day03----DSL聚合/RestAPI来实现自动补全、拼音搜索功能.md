### RestAPI来实现自动补全、拼音搜索功能



#### **对比记忆：**

![自带补全](E:\笔记整理\微服务技术\图解\DSL对应关系\自带补全.png)

```java
package com.zzx;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.suggest.SuggestBuilder;
import org.elasticsearch.search.suggest.SuggestBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * @Author: ZZX
 * @Date: 2022/7/13 10:50
 */
@SpringBootTest
public class TestAutoSearch {
    @Resource
    private RestHighLevelClient client;

    @Test
    public void test() throws IOException {
        SearchRequest request=new SearchRequest("hotel");
        request.source().suggest(new SuggestBuilder().addSuggestion(
                //名称，任意
                "mySuggestion",
                //添加补全的字段名称，它的值是数组
                //搜索suggestion字段中拼音前缀是h的
                SuggestBuilders.completionSuggestion("suggestion")
                        //搜索的前缀
                        .prefix("h")
                        //跳过重复
                        .skipDuplicates(true)
                        //显示数量
                        .size(10)
        ));
        SearchResponse response = client.search(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }
}
```





#### 结果解析：

![结果解析](E:\笔记整理\微服务技术\图解\DSL对应关系\结果解析.png)

```java
SearchResponse response = client.search(request, RequestOptions.DEFAULT);

Suggest suggest = response.getSuggest();
CompletionSuggestion suggestion =suggest.getSuggestion("mySuggestion");
List<CompletionSuggestion.Entry.Option> options = suggestion.getOptions();
for (CompletionSuggestion.Entry.Option option : options) {
    Text text = option.getText();
    String string = text.string();
    System.out.println(string);
}
```

