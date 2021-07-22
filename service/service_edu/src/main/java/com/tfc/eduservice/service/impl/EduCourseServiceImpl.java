package com.tfc.eduservice.service.impl;

import com.tfc.eduservice.entity.EduCourse;
import com.tfc.eduservice.entity.EduCourseDescription;
import com.tfc.eduservice.entity.vo.CourseInfoVo;
import com.tfc.eduservice.entity.vo.CoursePublishVo;
import com.tfc.eduservice.mapper.EduCourseMapper;
import com.tfc.eduservice.service.EduCourseDescriptionService;
import com.tfc.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tfc.servicebase.exceptionhandler.TfcException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-19
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 课程表中添加信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            throw new TfcException(20001, "添加数据失败");
        }

        //得到课程的ID
        String courseId = eduCourse.getId();

        //2课程描述表中添加内容
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(courseId);

        eduCourseDescriptionService.save(eduCourseDescription);

        return courseId;
    }

    //得到所有的课程信息
    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1查询课程的信息
        EduCourse eduCourse = baseMapper.selectById(courseId);

        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);


        //2查询描述的信息
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(eduCourseDescription.getDescription());
        courseInfoVo.setId(eduCourseDescription.getId());

        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //修改课程表
        EduCourse eduCourse = new EduCourse();

        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);

        if (update == 0) {
            throw new TfcException(20001, "修改课程信息失败");

        }

        //2修改课程信息表
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseInfoVo.getId());
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.updateById(eduCourseDescription);


    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        CoursePublishVo coursePublishVo = baseMapper.getPublishCourseInfo(id);

        return coursePublishVo;
    }


}
