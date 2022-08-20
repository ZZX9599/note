### 序列化API



**JSON常用方法：**



```java
package com.alibaba.fastjson;

public abstract class JSON {
    // 将Java对象序列化为JSON字符串，支持各种各种Java基本类型和JavaBean
    public static String toJSONString(Object object, SerializerFeature... features);

    // 将Java对象序列化为JSON字符串，返回JSON字符串的utf-8 bytes
    public static byte[] toJSONBytes(Object object, SerializerFeature... features);

    // 将Java对象序列化为JSON字符串，写入到Writer中
    public static void writeJSONString(Writer writer, 
                                       Object object, 
                                       SerializerFeature... features);

    // 将Java对象序列化为JSON字符串，按UTF-8编码写入到OutputStream中
    public static final int writeJSONString(OutputStream os,
                                            Object object,
                                            SerializerFeature... features);
}
```

