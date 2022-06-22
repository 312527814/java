package com.my.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.ListUtils;

import java.util.List;

/**
 * 描 述: <br/>
 * 作 者: liuliang14<br/>
 * 创 建:2022年05月27⽇<br/>
 * 版 本:v1.0.0<br> *
 * 历 史: (版本) 作者 时间 注释 <br/>
 */
public class IndexOrNameDataListener extends AnalysisEventListener<Object> {
    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    private List<Object> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    @Override
    public void invoke(Object data, AnalysisContext context) {
        System.out.println("解析到一条数据:"+data);
        cachedDataList.add(data);
        if (cachedDataList.size() >= BATCH_COUNT) {
            saveData();
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        //log.info("所有数据解析完成！");
        System.out.println("所有数据解析完成");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        System.out.println("条数据，开始存储数据库！"+cachedDataList.size());
        //log.info("{}条数据，开始存储数据库！", cachedDataList.size());
        //log.info("存储数据库成功！");
        System.out.println("存储数据库成功");
    }
}
