package com.tfc.eduservice.controller;


import com.tfc.commonutils.R;
import com.tfc.eduservice.entity.EduChapter;
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

    //添加章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){
        ChapterService.save(eduChapter);
        return R.ok();
    }


    //根据Id来查询
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId){
        EduChapter eduChapter = ChapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);

    }

    //修改章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody(required = false) EduChapter eduChapter){
        System.out.println(eduChapter.getTitle());
        ChapterService.updateById(eduChapter);
        return R.ok();
    }

    //删除章节
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){
        ChapterService.deleteChapter(chapterId);
        return R.ok();
    }

}

