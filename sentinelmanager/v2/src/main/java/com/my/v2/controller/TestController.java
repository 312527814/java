package com.my.v2.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RestController
public class TestController {
    @Resource
    private  TestController testController;

    @PostConstruct
    public void init() {
        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setResource("check/web2");
        flowRule.setCount(1);
        flowRules.add(flowRule);
        FlowRuleManager.loadRules(flowRules);
        System.out.println("init..");
    }


    @GetMapping("/check/web2")
    @SentinelResource(value = "check/web2", blockHandler = "blockFail")
    public String alive() {
        testController.alive3();
        return "successweb2";
    }

    @GetMapping("/check/web3")
    public String alive3() {

        return "successweb3";
    }


    public String blockFail(BlockException e) {
        return "blockFail...." + e;
    }


}
