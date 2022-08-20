### MybatisPlus公共字段填充



**步骤**

**1：在实体类上面加上@TableField注解    指定填充策略**

```java
package com.baomidou.mybatisplus.annotation;

public enum FieldFill {

	/**
	 * 默认不填充
	 */
	 
    DEFAULT,
    INSERT,
    UPDATE,
    INSERT_UPDATE;

    private FieldFill() {
    }
}
```

**2：按照框架的要求编写对象处理器，在此类中统一给公共字段赋值，需要实现MetaObjectHandler接口**

**MetaObjectHandler：元对象处理程序**





**示例**

**第一步：**

```java
@TableField(fill = FieldFill.INSERT)
private LocalDateTime createTime;

@TableField(fill = FieldFill.INSERT_UPDATE)
private LocalDateTime updateTime;

@TableField(fill = FieldFill.INSERT)
private Long createUser;

@TableField(fill = FieldFill.INSERT_UPDATE)
private Long updateUser;
```



**第二步：**

```java
package com.zzx.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author ZZX
 * @date 2022/5/15 14:37
 * 元数据处理器
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //插入inset数据的时候，自动填充数据
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", new Long(1));
        metaObject.setValue("updateUser", new Long(1));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //更新update数据的时候，自动填充数据
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", new Long(1));
    }
}

```