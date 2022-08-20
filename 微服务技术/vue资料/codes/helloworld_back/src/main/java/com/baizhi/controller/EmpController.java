package com.baizhi.controller;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@CrossOrigin
public class EmpController {


    @Resource
    private EmpService empService;


    @GetMapping("/emp")
    public Emp emp(Integer id) {
        return this.empService.queryById(id);
    }


    //查询所有
    @GetMapping("/emps")
    public List<Emp> emps(){
        return empService.queryAllByLimit(0,Integer.MAX_VALUE);
    }

    //添加员工信息
    @PostMapping("/emp")
    public void add(@RequestBody Emp emp){
        if(StringUtils.isEmpty(emp.getId())){
            empService.insert(emp);
        }else{
            empService.update(emp);
        }
    }


}
