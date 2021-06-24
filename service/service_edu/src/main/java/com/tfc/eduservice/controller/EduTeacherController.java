package com.tfc.eduservice.controller;


import com.tfc.eduservice.entity.EduTeacher;
import com.tfc.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 田付成
 * @since 2021-06-24
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService teacherService;

    //查询全部功能
    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher() {
        //调用service完成查询
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

    //2.逻辑删除讲师功能
    //ID需要通过路径传递id值，
    @DeleteMapping("{id}")
    public boolean removeTeacher(@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        return flag;
    }

}

