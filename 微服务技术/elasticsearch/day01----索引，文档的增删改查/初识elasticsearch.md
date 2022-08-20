### 初识elasticsearch



**es：es就是elasticsearch的缩写**

**elasticsearch是一款非常强大的开源搜索引擎，可以帮助我们从海量数据中快速找到需要的内容**

**elasticsearch结合kibana、Logstash、Beats，也就是elastic stack（ELK），es技术栈**

**被广泛应用在日志数据分析、实时监控等领域。**



![es技术栈图解](E:\笔记整理\微服务技术\图解\es技术栈图解.png)



**elasticsearch的实现技术：lucene**

**Lucene是一个Java语言的搜索引擎类库，是Apache公司的顶级项目**

**由DougCutting于1999年研发。官网地址：https://lucene.apache.org/ 。**



**Lucene的优势：**

**•易扩展**

**•高性能（基于倒排索引）**



**Lucene的缺点：**

**•只限于Java语言开发**

**•学习曲线陡峭**

**•不支持水平扩展**

**elasticsearch正是在其基础上，做了二次开发**



**2004年Shay Banon基于Lucene开发了Compass【给他老婆做饭搜素菜谱】**

**2010年Shay Banon 重写了Compass，取名为Elasticsearch。**

**官网地址: https://www.elastic.co/cn/**

**目前最新的版本是：7.12.1**

**相比与lucene，elasticsearch具备下列优势：**

**•支持分布式，可水平扩展**

**•提供Restful接口，可被任何语言调用**



**为什么学习elasticsearch？**

**搜索引擎技术排名：**

**1.Elasticsearch：开源的分布式搜索引擎**

**2.Splunk：商业项目**

**3.Solr：Apache的开源搜索引擎**





**什么是elasticsearch？**

**一个开源的分布式搜索引擎，可以用来实现搜索、日志统计、分析、系统监控等功能**

**什么是elastic stack（ELK）？**

**是以elasticsearch为核心的技术栈，包括beats、Logstash、kibana、elasticsearch**

**什么是Lucene？**

**是Apache的开源搜索引擎类库，提供了搜索引擎的核心API**