package com.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @author 田付成
 * @date 2021/7/14 5:02
 */
public class ExcelListener extends AnalysisEventListener<DemoDate> {
    @Override
    public void invoke(DemoDate demoDate, AnalysisContext analysisContext) {
        System.out.println("****" + demoDate);
    }

    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext
            context) {
        System.out.println("表头信息：" + headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
