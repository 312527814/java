package com.my.v3.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.my.v3.util.ExceptionUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@RestController
public class TestController {

    @PostConstruct
    public void init() {
        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setResource("check/web3");
        flowRule.setCount(1);
        flowRules.add(flowRule);
        FlowRuleManager.loadRules(flowRules);
        System.out.println("init..");
    }


    @GetMapping("/check/web3")
    @SentinelResource(value = "check/web3",  blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    public String alive() {

        return "successweb3";
    }



}
