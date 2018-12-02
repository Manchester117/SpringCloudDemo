package com.user.consumer.client;

import com.user.consumer.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public User getUserById(Long id) {
        User user = new User();
        user.setMessage(String.format("未查找到用户: %d", id));
        return user;
    }
}
