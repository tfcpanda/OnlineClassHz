package com.tfc.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tfc.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 田付成
 * @since 2021-06-24
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String, Object> getTeacherList(Page<EduTeacher> pageTeacher);
}
