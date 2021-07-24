package com.tfc.eduservice.controller.font;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tfc.commonutils.R;
import com.tfc.eduservice.entity.EduCourse;
import com.tfc.eduservice.entity.EduTeacher;
import com.tfc.eduservice.service.EduCourseService;
import com.tfc.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 田付成
 * @date 2021/7/24 23:00
 */

@RestController
@CrossOrigin
@RequestMapping("/eduservice/index")
public class IndexController {
    @Autowired
    private EduCourseService courseService;
    @Autowired
    private EduTeacherService teacherService;

    //查询前8条热门课程，查询前4条名师
    @GetMapping("index")
    public R index() {
//查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduList = courseService.list(wrapper);
//查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.list(wrapperTeacher);
        return R.ok().data("eduList", eduList).data("teacherList", teacherList);
    }
}
