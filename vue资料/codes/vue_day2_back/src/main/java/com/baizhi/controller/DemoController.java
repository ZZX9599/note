package com.baizhi.controller;

import com.baizhi.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin //用来解决跨域
public class DemoController {

    private static final Logger log = LoggerFactory.getLogger(DemoController.class);


    @PutMapping("/test1")
    public String test1(@RequestBody  User user){
        log.info("test1 ok ");
        log.info("id:{}  name:{}",user.getId(),user.getName());
        return "test1 ok ";
    }


    @PostMapping("/test")
    public String test(@RequestBody  User user){
        log.info("test ok ");
        log.info("id:{}  name:{}",user.getId(),user.getName());
        return "test ok ";
    }

    @GetMapping("/demo")
    public String demo(@RequestParam("id") String id,@RequestParam("name") String name){
        log.info("demo ok ....");
        log.info("id:{} name:{}",id,name);
        //int n = 1/0;
        return "demo ok ";
    }
}
