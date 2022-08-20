### Mybatis-plus的各层定义规范



**mapper要继承BaseMapper，泛型加上实体类**

```java
package com.zzx.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzx.reggie.entity.Employee;

/**
 * @author ZZX
 */
public interface EmployeeMapper extends BaseMapper<Employee>{
}
```



**service要继承IService，泛型加上实体类**

```java
package com.zzx.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zzx.reggie.entity.Employee;

/**
 * @author ZZX
 */
public interface EmployeeService extends IService<Employee> {
}
```



**service实现类要继承ServiceImpl类，泛型是实体类Mapper和实体类，同时实现Service接口**

```java
package com.zzx.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzx.reggie.entity.Employee;
import com.zzx.reggie.mapper.EmployeeMapper;
import com.zzx.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author ZZX
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService{
}
```