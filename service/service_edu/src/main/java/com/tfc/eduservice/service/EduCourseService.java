package com.tfc.eduservice.service;

import com.tfc.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tfc.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-19
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //得到所有的课程信息
    CourseInfoVo getCourseInfo(String courseId);
    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);
}
