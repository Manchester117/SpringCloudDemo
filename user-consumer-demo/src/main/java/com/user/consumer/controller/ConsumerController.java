package com.user.consumer.controller;

import com.user.consumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

//    /**
//     * 向生产者发送请求
//     * @param id - 用户ID
//     * @return User对象
//     */
//    @GetMapping("{id}")
//    public User getUserById(@PathVariable("id") Long id) {
//        String url = "http://localhost:8081/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }

    /**
     * 使用Eureka的服务发现,实现基于服务的访问(不是基于URL)
     * @param id - 用户ID
     * @return User对象
     */
    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") Long id) {
        // 从服务拉取以某个实例ID为标识的服务列表
        List<ServiceInstance> instanceList = discoveryClient.getInstances("user-product-demo");
        // 因为只有一个服务,所以就只取第1个
        ServiceInstance instance = instanceList.get(0);
        // 隐藏具体的服务地址和端口号
        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;
        User user = restTemplate.getForObject(url, User.class);
        return user;
    }
}
