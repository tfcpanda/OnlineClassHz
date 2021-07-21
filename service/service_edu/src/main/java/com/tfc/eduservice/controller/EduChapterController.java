package com.tfc.eduservice.controller;


import com.tfc.commonutils.R;
import com.tfc.eduservice.entity.section.ChapterVo;
import com.tfc.eduservice.service.EduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 田付成
 * @since 2021-07-19
 */
@RestController
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
public class EduChapterController {


    @Autowired
    private EduChapterService ChapterService;


    @GetMapping("getAllCourse/{courseId}")
    public R getAllCourse(@PathVariable String courseId){
       List<ChapterVo> list =  ChapterService.getAllChapterByCourseId(courseId);
        return R.ok().data("AllChapter",list);
    }

}

