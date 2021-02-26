package com.my.v1.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleComparator;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @PostConstruct
    public void init() {
        List<FlowRule> flowRules = new ArrayList<>();
        FlowRule flowRule = new FlowRule();
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        flowRule.setResource("check/web");
        flowRule.setCount(1);
        flowRules.add(flowRule);
        FlowRuleManager.loadRules(flowRules);
        System.out.println("init..");
    }


    @GetMapping("/check/web")
    public String alive() {
        Entry entity = null;
        try {
            entity = SphU.entry("check/web");
            System.out.println("..........");

        } catch (BlockException e) {
            System.out.println("方法被流控了...");
            return "方法被流控了...";
        } finally {
            if (entity != null) {
                entity.exit();
            }
        }
        return "success";
    }


}
