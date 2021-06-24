package com.tfc.eduservice.controller;


import com.tfc.eduservice.entity.EduTeacher;
import com.tfc.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("findAll")
    public List<EduTeacher> findAllTeacher(){
        //调用service完成查询
       List<EduTeacher> list =  teacherService.list(null);
        return list;
    }






}

