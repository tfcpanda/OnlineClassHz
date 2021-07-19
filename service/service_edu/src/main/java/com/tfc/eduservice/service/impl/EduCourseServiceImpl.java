package com.tfc.eduservice.service.impl;

import com.tfc.eduservice.entity.EduCourse;
import com.tfc.eduservice.entity.EduCourseDescription;
import com.tfc.eduservice.entity.vo.CourseInfoVo;
import com.tfc.eduservice.mapper.EduCourseMapper;
import com.tfc.eduservice.service.EduCourseDescriptionService;
import com.tfc.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tfc.servicebase.exceptionhandler.TfcException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 课程表中添加信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0){
            throw new TfcException(20001,"添加数据失败");
        }

        //得到课程的ID
        String courseName = eduCourse.getId();

        //2课程描述表中添加内容
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(courseName);

        eduCourseDescriptionService.save(eduCourseDescription);

    }
}
