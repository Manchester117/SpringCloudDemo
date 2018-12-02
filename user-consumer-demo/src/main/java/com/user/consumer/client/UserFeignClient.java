package com.user.consumer.client;

import com.user.consumer.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(serviceId = "user-product-demo", fallback = UserFeignClientFallback.class)
public interface UserFeignClient {
    @GetMapping("/user/{id}")
    User getUserById(@PathVariable("id") Long id);
}
