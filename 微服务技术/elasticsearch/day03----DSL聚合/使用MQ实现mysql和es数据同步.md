### 使用MQ实现mysql和es数据同步



**利用课前资料提供的hotel-admin项目作为酒店管理的微服务。当酒店数据发生增、删、改时，要求对**

**elasticsearch中数据也要完成相同操作。**

**步骤：**

**•导入课前资料提供的hotel-admin项目，启动并测试酒店数据的CRUD**

**•声明exchange、queue、RoutingKey**

**•在hotel-admin中的增、删、改业务中完成消息发送**

**•在hotel-demo中完成消息监听，并更新elasticsearch中数据**

**启动并测试数据同步功能**





![mq解决](E:\笔记整理\微服务技术\图解\DSL对应关系\mq解决.png)



#### 实际上就是数据库那边发生了数据的变化，就声明消息发送到消息队列里面去

```java
//以下的大写字母为我自己定义的一些常量。就是交换机，队列，key等信息，对于ES来说，增加和更新都是一样的

#发送更新消息到MQ
rabbitTemplate.convertAndSend
    (MqConstant.HOTEL_EXCHANGE,MqConstant.HOTEL_UPDATE_KEY,hotel.getId());

#发送增加消息到MQ
rabbitTemplate.convertAndSend
    (MqConstant.HOTEL_EXCHANGE,MqConstant.HOTEL_INSERT_KEY,hotel.getId());

#发送删除消息到MQ
rabbitTemplate.convertAndSend
    (MqConstant.HOTEL_EXCHANGE,MqConstant.HOTEL_DELETE_KEY,id);
```





#### **在ES端声明接收的信息【交换机，队列，key等信息】**

```java
@Component
public class HotelMonitor {

    @Resource
    private HotelService hotelService;

    /**
     * 增加
     * @param id
     */
    @RabbitListener(bindings = @QueueBinding(
            value =@Queue(MqConstant.HOTEL_INSERT),
            exchange = @Exchange(MqConstant.HOTEL_EXCHANGE),
            key = MqConstant.HOTEL_INSERT_KEY
    ))
    public void monitorInsert(Long id){
        System.out.println("监听到增加的数据id为:"+id);
        hotelService.selectById(id);
    }

    /**
     * 更新
     * @param id
     */
    @RabbitListener(bindings = @QueueBinding(
            value =@Queue(MqConstant.HOTEL_UPDATE),
            exchange = @Exchange(MqConstant.HOTEL_EXCHANGE),
            key = MqConstant.HOTEL_UPDATE_KEY
    ))
    public void monitorUpdate(Long id){
        System.out.println("监听到更新的数据id为:"+id);
        hotelService.selectById(id);
    }

    /**
     * 删除
     * @param id
     */
    @RabbitListener(bindings = @QueueBinding(
            value =@Queue(MqConstant.HOTEL_DELETE),
            exchange = @Exchange(MqConstant.HOTEL_EXCHANGE),
            key = MqConstant.HOTEL_DELETE_KEY
    ))
    public void monitorDelete(Long id){
        System.out.println("监听到删除的数据id为:"+id);

        hotelService.deleteById(id);
    }
}
```



#### 真实调用的ES操作：

```java
@Override
public void deleteById(Long id) {
    DeleteRequest request=new DeleteRequest("hotel",id.toString());
    DeleteResponse delete = null;
    try {
        delete = client.delete(request, RequestOptions.DEFAULT);
    } catch (IOException e) {
        e.printStackTrace();
    }
    String string = delete.toString();
    System.out.println("true".equals(string)?"删除成功":"删除失败");
}

@Override
public void selectById(Long id) {

    Hotel hotel = getById(id);
    HotelDoc hotelDoc = new HotelDoc(hotel);
    IndexRequest request=new IndexRequest("hotel").id(hotelDoc.getId().toString());
    request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
    try {
        client.index(request, RequestOptions.DEFAULT);
    } catch (IOException e) {
        e.printStackTrace();
    }
    System.out.println("添加/更新文档成功");
}
```
