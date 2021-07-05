package com.tfc.eduservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tfc.commonutils.R;
import com.tfc.eduservice.entity.EduTeacher;
import com.tfc.eduservice.entity.vo.TeacherQuery;
import com.tfc.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
@Api("讲师管理")
@RestController
@RequestMapping("/eduservice/edu-teacher")
public class EduTeacherController {

    //注入service
    @Autowired
    private EduTeacherService teacherService;

    //查询全部功能
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher() {
        //调用service完成查询
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }

    //2.逻辑删除讲师功能
    //ID需要通过路径传递id值，
    @DeleteMapping("{id}")
    public R removeTeacher(@ApiParam(name = "id", value = "讲师ID", required = true)@PathVariable String id) {
        boolean flag = teacherService.removeById(id);
        if (flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

//    //分页查询
//    @GetMapping("pageTeacher/{current}/{limit}")
//    public R pageListTeacher(@PathVariable long current,@PathVariable long limit){
//        //创建一个分页对象
//        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
//        //底层数据封装到pageTracher中
//        teacherService.page(pageTeacher,null);
//
//        //记录总数
//        long total = pageTeacher.getTotal();
//        //数据list合集
//        List<EduTeacher> records = pageTeacher.getRecords();
//
//        return R.ok().data("total",total).data("rows",records);
//    }


    /*条件查询分页*/
    @PostMapping("pageTeacher/{current}/{limit}")
    public R pageTeacherCondition(@PathVariable long current, @PathVariable long limit, @RequestBody(required = false) TeacherQuery teacherQuery){

        //创建page对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);
        //构建条件
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if (!StringUtils.isEmpty(name)){
            //构建条件
            wrapper.like("name",name);
        }

        if (!StringUtils.isEmpty(level)){
            //构建条件
            wrapper.like("level",level);
        }

        if (!StringUtils.isEmpty(begin)){
            //构建条件
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            //构建条件
            wrapper.le("gmt_create",end);
        }

        //调用方法实现条件分页查询
        teacherService.page(pageTeacher,wrapper);

        //记录总数
        long total = pageTeacher.getTotal();

        //数据list合集
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);

    }

}

