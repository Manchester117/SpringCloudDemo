package com.user.product.service;

import com.user.product.mapper.UserMapper;
import com.user.product.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
