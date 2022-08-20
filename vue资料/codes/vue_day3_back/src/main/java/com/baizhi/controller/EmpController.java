package com.baizhi.controller;

import com.baizhi.entity.Emp;
import com.baizhi.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Emp)表控制层
 *
 * @author chenyn
 * @since 2021-05-10 09:51:57
 */
@RestController
@CrossOrigin  //解决跨域问题
public class EmpController {

    private static final Logger log = LoggerFactory.getLogger(EmpController.class);
    @Resource
    private EmpService empService;


    //保存员工信息方法
    @PostMapping("/emp")
    public void update(@RequestBody Emp emp) {
        log.info("当前保存或修改员工信息: {}", emp.toString());
        if (StringUtils.isEmpty(emp.getId())) {//保存
            empService.insert(emp);
        } else {
            empService.update(emp);
        }
    }

    //根据员工id删除员工信息
    @DeleteMapping("/emp/{id}")
    public void delete(@PathVariable("id") Integer id) {
        log.info("要删除的员工id: {}", id);
        empService.deleteById(id);
    }

    //查询所有员工信息的方法
    @GetMapping("/emp")
    public List<Emp> emps() {
        List<Emp> emps = empService.queryAllByLimit(0, Integer.MAX_VALUE);
        log.info("查询数据库员工列表条数: {}", emps.size());
        return emps;
    }

    //根据员工id查询一个员工信息
    @GetMapping("/emp/{id}")
    public Emp emp(@PathVariable("id") Integer id) {
        return this.empService.queryById(id);
    }

}
