package com.my.inter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "my-producer",url = "url")
public interface CloudProducterClient {
    @RequestMapping(method = RequestMethod.GET,value = "/test/get2")
    String get();
}
