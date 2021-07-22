package com.tfc.eduservice.entity.vo;

import lombok.Data;

/**
 * @author 田付成
 * @date 2021/7/23 1:25
 */
@Data
public class CoursePublishVo {

    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
