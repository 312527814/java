package io.seata.sample.controller;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import io.seata.sample.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jimin.jm@alibaba-inc.com
 * @date 2019/06/14
 */

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/create", produces = "application/json")
    public Boolean create(String userId, String commodityCode, Integer count) {

        orderService.create(userId, commodityCode, count);
        return true;
    }

    @Bean
    public IRule myRule() { // 负载均衡算法。。。。

//        return new RoundRobinRule();
        return new RoundRobinRule();
    }

}
