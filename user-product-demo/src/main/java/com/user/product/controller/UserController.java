package com.user.product.controller;

import com.user.product.pojo.User;
import com.user.product.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
}
