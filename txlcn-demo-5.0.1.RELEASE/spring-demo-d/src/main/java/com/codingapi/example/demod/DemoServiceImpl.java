package com.codingapi.example.demod;

import com.codingapi.example.common.db.domain.Demo;
import com.codingapi.txlcn.common.util.Transactions;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.TxcTransaction;
import com.codingapi.txlcn.tc.core.DTXLocalContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Description:
 * Date: 2018/12/25
 *
 * @author ujued
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    private final DDemoMapper demoMapper;


    @Autowired
    public DemoServiceImpl(DDemoMapper demoMapper) {
        this.demoMapper = demoMapper;
    }

    @Override
    @TxcTransaction(propagation = DTXPropagation.SUPPORTS)
    @Transactional
    public String rpc(String value) {
        {
            Demo demo = new Demo();
            demo.setId(127l);
            demo.setDemoField(value);

            demoMapper.update(demo);
        }

//        demoMapper.deleteByKId(129l);

//        Demo demo2 = new Demo();
//        demo2.setId(129l);
//        demo2.setDemoField(value);
//        demoMapper.update(demo2);
////        moreOperateMapper.update(new Date());
////        moreOperateMapper.delete();

//        {
//            Demo demo = new Demo();
//            demo.setDemoField(value);
//            demo.setCreateTime(new Date());
//            demo.setAppName(Transactions.APPLICATION_ID_WHEN_RUNNING);
//            demo.setGroupId(DTXLocalContext.getOrNew().getGroupId());
//            demo.setUnitId(DTXLocalContext.getOrNew().getUnitId());
//            demoMapper.save(demo);
//        }

        try {
            Thread.sleep(1000 * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ol-d..............");
        return "ok-d";
    }

    public String rpc2(String value) {
        /*
         * 注意 5.0.0 请用 DTXLocal 类
         * 注意 5.0.0 请自行获取应用名称
         * 注意 5.0.0 其它类重新导入包名
         */
//        log.info("GroupId: {}", TracingContext.tracing().groupId());
        Demo demo = new Demo();
        demo.setCreateTime(new Date());
        demo.setDemoField(value);
        demo.setAppName(Transactions.APPLICATION_ID_WHEN_RUNNING);  // 应用名称
        demo.setGroupId(DTXLocalContext.getOrNew().getGroupId());   // DTXLocal
        demo.setUnitId(DTXLocalContext.getOrNew().getUnitId());
        demoMapper.save(demo);
//        moreOperateMapper.update(new Date());
//        moreOperateMapper.delete();
        try {
            Thread.sleep(1000 * 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ol-d..............");
        return "ok-d";
    }
}
