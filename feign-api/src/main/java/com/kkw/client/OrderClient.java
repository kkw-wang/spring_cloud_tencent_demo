package com.kkw.client;

import com.kkw.config.HeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 订单客户端
 *
 * @author kkw
 * @date 2023/11/21  12:15
 */
@FeignClient(name = "order-server", configuration = HeaderConfiguration.class)
public interface OrderClient {
    
    @GetMapping("/test")
    String test();
    
}
