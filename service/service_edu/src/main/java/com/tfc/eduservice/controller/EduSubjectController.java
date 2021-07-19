package com.tfc.eduservice.controller;


import com.tfc.commonutils.R;
import com.tfc.eduservice.entity.subject.OneSubject;
import com.tfc.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 田付成
 * @since 2021-07-17
 */

@Api("上传课程")
@RestController
@RequestMapping("/eduservice/edu-subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService subjectService;

    //1添加课程
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file){
        //1上传过来的excel文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    @GetMapping("getAllSubject")
    public R getAllSubjec(){
        List<OneSubject> list = subjectService.getAllOneSubject();
        return R.ok().data("list",list);
    }

}

