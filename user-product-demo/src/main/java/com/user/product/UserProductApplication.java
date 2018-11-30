package com.user.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 服务启动类
 */
@EnableDiscoveryClient          // 启动Eureka的服务发现
@SpringBootApplication
@MapperScan("com.user.product.mapper")
public class UserProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProductApplication.class);
    }
}
