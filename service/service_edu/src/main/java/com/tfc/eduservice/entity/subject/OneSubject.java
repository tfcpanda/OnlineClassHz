package com.tfc.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 田付成
 * @date 2021/7/19 2:13
 */

@Data
public class OneSubject {

    private String id;

    private String title;

    //一级分类里面的二级分离
    private List<TwoSubject> children = new ArrayList<>();
}
