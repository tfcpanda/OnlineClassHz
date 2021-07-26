package com.tfc.eduservice.controller.font;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tfc.commonutils.R;
import com.tfc.eduservice.entity.EduCourse;
import com.tfc.eduservice.entity.EduTeacher;
import com.tfc.eduservice.service.EduCourseService;
import com.tfc.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 田付成
 * @date 2021/7/26 4:29
 */

@CrossOrigin
@RestController
@RequestMapping("/eduservice/index")
public class TeacherFrontController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "getFrontList/{page}/{limit}")
    public R getTeacherFrontList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit){
        Page<EduTeacher> pageTeacher = new Page<>(page, limit);
        Map<String, Object> map = eduTeacherService.getTeacherList(pageTeacher);
        return R.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "getTeacher/{teacherId}")
    public R getById(@PathVariable String teacherId){
        //查询讲师信息
        EduTeacher eduTeacher = eduTeacherService.getById(teacherId);
        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = eduCourseService.selectByTeacherId(teacherId);
        return R.ok().data("eduTeacher", eduTeacher).data("courseList", courseList);
    }



}
