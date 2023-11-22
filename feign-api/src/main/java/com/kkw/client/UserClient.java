package com.kkw.client;

import com.kkw.config.HeaderConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 用户客户端
 *
 * @author kkw
 * @date 2023/11/21  14:33
 */
@FeignClient(name = "user-server", configuration = HeaderConfiguration.class)
public interface UserClient {
    
    @GetMapping("/test")
    String test();
}
