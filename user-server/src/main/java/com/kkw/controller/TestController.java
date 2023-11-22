package com.kkw.controller;

import com.kkw.client.OrderClient;
import com.kkw.client.UserClient;
import com.tencent.cloud.rpc.enhancement.stat.config.PolarisStatProperties;
import io.micrometer.prometheus.PrometheusConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.export.prometheus.PrometheusPushGatewayManager;
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
    private OrderClient orderClient;

    @Autowired
    private PolarisStatProperties polarisStatProperties;
    
    @RequestMapping("test")
    public String test(){
        return "这是user-server的test()方法";
    }
    
    @RequestMapping("testFeign")
    public String testFeign(){
        String test = orderClient.test();
        polarisStatProperties.getHost();
        return "这是user-server的testFeign()方法，feign调用返回结果是：" + test;
    }
    
}
