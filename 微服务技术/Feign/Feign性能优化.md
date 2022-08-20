### Feign性能优化



**Feign底层的客户端实现：**

**•URLConnection：默认实现，不支持连接池**

**•Apache HttpClient ：支持连接池**

**•OKHttp：支持连接池**



**因此优化Feign的性能主要包括：**

**①使用连接池代替默认的URLConnection**

**②日志级别，最好用basic或none**





#### 使用步骤

**引入依赖**

```xml
<!--httpClient的依赖 -->
<dependency>
	<groupId>io.github.openfeign</groupId>
	<artifactId>feign-httpclient</artifactId>
</dependency>
```

**配置连接池**

```yaml
feign:
	client:
		config:
			default: # default全局的配置
				loggerLevel: BASIC # 日志级别，BASIC就是基本的请求和响应信息
				
	httpclient:
		enabled: true # 开启feign对HttpClient的支持
		max-connections: 200 # 最大的连接数
		max-connections-per-route: 50 # 每个路径的最大连接数
```

**总结：**

**Feign的优化：**

**1.日志级别尽量用basic**

**2.使用HttpClient或OKHttp代替URLConnection**

**①引入feign-httpClient依赖**

**②配置文件开启httpClient功能，设置连接池参数**