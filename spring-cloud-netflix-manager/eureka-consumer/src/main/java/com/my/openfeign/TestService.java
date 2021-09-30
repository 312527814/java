package com.my.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(value = "producer",url = "localhost:8084")
@FeignClient(value = "producer1")
public interface TestService {
    @RequestMapping(method = RequestMethod.GET,value = "/api/check/web")
    String checkWeb();


}
