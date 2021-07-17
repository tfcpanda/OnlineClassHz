package com.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.*;

/**
 * @author 田付成
 * @date 2021/7/14 4:51
 */
public class TestEsayExcel {

    public static void main(String[] args) {
        String fileName = "D:\\write.xlsx";

//        EasyExcel.write(fileName, DemoDate.class).sheet("学生列表").doWrite(getList());
// 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoDate.class, new
                ExcelListener()).sheet().doRead();

    }

    public static List<DemoDate> getList() {
        List<DemoDate> list = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            DemoDate demoDate = new DemoDate();
            demoDate.setSno(i);
            demoDate.setSname("luck" + 1);
            list.add(demoDate);
        }
        return list;
    }

}
