package com.user.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sun.javafx.binding.StringFormatter;
import com.user.consumer.client.UserFeignClient;
import com.user.consumer.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
//@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private DiscoveryClient discoveryClient;
    // 在复杂方式中使用LoadBalancerClient
    // 这里要注意的是:不使用RibbonLoadBalancerClient
    // 否则会报'org.springframework.cloud.netflix.ribbon.RibbonLoadBalancerClient' that could not be found
//    @Autowired
//    private LoadBalancerClient balancerClient;
    @Autowired
    private UserFeignClient feignClient;


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

//    /**
//     * 使用Eureka的服务发现,实现基于服务的访问(不是基于URL)
//     * @param id - 用户ID
//     * @return User对象
//     */
//    @GetMapping("{id}")
//    public User getUserById(@PathVariable("id") Long id) {
//        // 从服务拉取以某个实例ID为标识的服务列表
//        List<ServiceInstance> instanceList = discoveryClient.getInstances("user-product-demo");
//        // 因为只有一个服务,所以就只取第1个
//        ServiceInstance instance = instanceList.get(0);
//        System.out.println(instance.getHost() + ":" + instance.getPort());
//        // 隐藏具体的服务地址和端口号
//        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }

//    /**
//     * 使用Ribbon实现负载均衡(复杂方式)
//     * @param id - 用户ID
//     * @return User对象
//     */
//    @GetMapping("{id}")
//    public User getUserById(@PathVariable("id") Long id) {
//        ServiceInstance instance = balancerClient.choose("user-product-demo");
//        // 隐藏具体的服务地址和端口号
//        String url = "http://" + instance.getHost() + ":" + instance.getPort() + "/user/" + id;
//        System.out.println(url);
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }

//    /**
//     * 使用Ribbon实现负载均衡(简单方式)
//     * @param id - 用户ID
//     * @return User对象
//     */
//    @GetMapping("{id}")
//    public User getUserById(@PathVariable("id") Long id) {
//        // 隐藏具体的服务地址和端口号
//        String url = "http://user-product-demo/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }

//    /**
//     * 增加Hystrix服务降级(单独方法)
//     * @param id - 用户ID
//     * @return User对象
//     */
//    @GetMapping("{id}")
//    @HystrixCommand(fallbackMethod = "getUserByIdFallBack")
//    public User getUserById(@PathVariable("id") Long id) {
//        // 隐藏具体的服务地址和端口号
//        String url = "http://user-product-demo/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }
//
//    public User getUserByIdFallBack(Long id) {
//        User user = new User();
//        user.setMessage(String.format("查询用户失败,查询用户ID: %d", id));
//        return user;
//    }

//    /**
//     * 增加Hystrix服务降级(类级配置-单独方法)
//     * @param id - 用户ID
//     * @return User对象
//     */
//    @GetMapping("{id}")
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//    })
//    public User getUserById(@PathVariable("id") Long id) {
//        // 隐藏具体的服务地址和端口号
//        String url = "http://user-product-demo/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }
//
//    public User defaultFallback() {
//        User user = new User();
//        user.setMessage("类级作用域的降级方法");
//        return user;
//    }

//    /**
//     * 增加Hystrix服务降级(全局配置)
//     * @param id - 用户ID
//     * @return User对象
//     */
//    @GetMapping("{id}")
//    @HystrixCommand         // 如果是全局配置,这里不需要写任何参数
//    public User getUserById(@PathVariable("id") Long id) {
//        // 隐藏具体的服务地址和端口号
//        String url = "http://user-product-demo/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }
//
//    public User defaultFallback() {
//        User user = new User();
//        user.setMessage("全局配置作用域的服务降级");
//        return user;
//    }

//    /**
//     * 增加Hystrix服务熔断
//     * @param id - 用户ID
//     * @return User对象
//     */
//    @GetMapping("{id}")
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
//            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
//            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
//    })
//    public User getUserById(@PathVariable("id") Long id) {
//        if (id % 2 == 0) {
//            throw new RuntimeException("超时和抛出异常过多可以引起熔断");
//        }
//        // 隐藏具体的服务地址和端口号
//        String url = "http://user-product-demo/user/" + id;
//        User user = restTemplate.getForObject(url, User.class);
//        return user;
//    }
//
//    public User defaultFallback() {
//        User user = new User();
//        user.setMessage("全局配置作用域的服务降级");
//        return user;
//    }

    @GetMapping("{id}")
    public User getUseerId(@PathVariable("id") Long id) {
        return feignClient.getUserById(id);
    }
}
