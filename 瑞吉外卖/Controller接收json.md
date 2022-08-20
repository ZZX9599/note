### Controller接收json

**当controller接收json参数的时候，直接是接受不到的，需要加入@RequestBody注解**



```java
/**
 * 新增员工
 * @param employee
 * @return
 */
@PostMapping
public R<String> save(@RequestBody Employee employee){
    log.info("新增员工 员工信息："+employee);
    return null;
}
```