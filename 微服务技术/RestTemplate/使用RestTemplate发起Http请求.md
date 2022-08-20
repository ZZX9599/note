### 使用RestTemplate发起Http请求



**需求：查询订单的时候，查询购买者的信息**



**先创建出RestTemplate对象，交给容器管理**

```java
@Bean
public RestTemplate getRestTemplate(){
    return new RestTemplate();
}
```



**注入，使用RestTemplate**

```java
//查询
User object = restTemplate.getForObject
("http://localhost:8081/user/" + order.getUserId(), User.class);
```

**RestTemplate非常智能，可以直接写需要的类型，比如写成User.class**

**会自动转化成User对象**