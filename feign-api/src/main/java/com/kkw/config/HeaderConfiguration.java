package com.kkw.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Enumeration;

/**
 * 将请求的header携带转发
 *
 * @author 王家宝
 * @date 2022/10/03  19:21
 */
@Slf4j
@Configuration
public class HeaderConfiguration implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        // try起来防止由于设备上报的信息没有请求头信息导致报错,调用失败
        try {
            // 获取当前请求头信息
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            assert attributes != null;
            HttpServletRequest request = attributes.getRequest();
            //传递header里面所有内容
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    String values = request.getHeader(name);
                    // 跳过 content-length ,不跳过会导致feign.RetryableException: too many bytes written executing
                    if (name.equals("content-length")){
                        continue;
                    }
                    // 设置请求头信息
                    template.header(name, values);
                }
            }
        } catch (Exception e) {
            log.info("设备上报调用,无请求头信息");
        }
    }
}
