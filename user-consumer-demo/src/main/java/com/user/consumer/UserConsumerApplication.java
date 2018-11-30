package com.user.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient          // 启动Eureka的服务发现
@SpringBootApplication
public class UserConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApplication.class);
    }

    @Bean                   // 获得RestTemplate对象
    @LoadBalanced           // 添加负载均衡的注解(简单方式)
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
