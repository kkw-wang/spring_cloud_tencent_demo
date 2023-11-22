package com.kkw.controller;

import com.kkw.client.UserClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author kkw
 * @date 2023/11/21  14:37
 */
@RestController
public class TestController {
    
    @Resource
    private UserClient userClient;
    
    @RequestMapping("test")
    public String test(){
        return "这是order-server的test()方法";
    }
    
    @RequestMapping("testFeign")
    public String testFeign(){
        String test = userClient.test();
        return "这是order-server的testFeign()方法，feign调用返回结果是：" + test;
    }
    
}
