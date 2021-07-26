package com.tfc.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tfc.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tfc.eduservice.entity.frontvo.CourseQueryVo;
import com.tfc.eduservice.entity.frontvo.CourseWebVo;
import com.tfc.eduservice.entity.vo.CourseInfoVo;
import com.tfc.eduservice.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

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

    CoursePublishVo publishCourseInfo(String id);

    //删除课程
    void removeCourse(String courseId);

    //根据讲师id查询所讲课程
    List<EduCourse> selectByTeacherId(String teacherId);

    Map<String, Object> pageListFront(Page<EduCourse> pageParam, CourseQueryVo courseQuery);

    CourseWebVo getBaseCourseInfo(String courseId);
}
