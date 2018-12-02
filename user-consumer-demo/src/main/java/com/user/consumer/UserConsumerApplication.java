package com.user.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@EnableCircuitBreaker           // 启用Hystrix降级/熔断
//@EnableDiscoveryClient          // 启动Eureka的服务发现
//@SpringBootApplication          // SpringBoot启动类的注解
@EnableFeignClients             // 启用feign--声明web服务客户端
@SpringCloudApplication         // SpringCloud的整合注解(整合了Eureka和Hystrix以及SpringBoot启动类注解)
public class UserConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserConsumerApplication.class);
    }

    // 如果使用了Feign客户端,就不需要RestTemplate了,并且负载均衡也被整合到了Feign当中
//    @Bean                   // 获得RestTemplate对象
//    @LoadBalanced           // 添加负载均衡的注解(简单方式)
//    public RestTemplate getRestTemplate() {
//        return new RestTemplate();
//    }
}
