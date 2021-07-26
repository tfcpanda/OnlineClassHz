package com.tfc.eduservice.controller.font;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tfc.commonutils.R;
import com.tfc.eduservice.entity.EduCourse;
import com.tfc.eduservice.entity.frontvo.CourseQueryVo;
import com.tfc.eduservice.entity.frontvo.CourseWebVo;
import com.tfc.eduservice.entity.section.ChapterVo;
import com.tfc.eduservice.service.EduChapterService;
import com.tfc.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author 田付成
 * @date 2021/7/26 12:01
 */
@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class CourseFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery) {
        Page<EduCourse> pageParam = new Page<>(page, limit);
        Map<String, Object> map = eduCourseService.pageListFront(pageParam, courseQuery);
        return R.ok().data(map);

    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping(value = "{courseId}")
    public R getById(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {
        //查询课程信息和讲师信息
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);
        //查询当前课程的章节信息
        List<ChapterVo> chapterVoList = eduChapterService.getAllChapterByCourseId(courseId);
        return R.ok().data("course", courseWebVo).data("chapterVoList", chapterVoList);

    }

}
