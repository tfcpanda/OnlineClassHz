package com.tfc.eduservice.service;

import com.tfc.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tfc.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-17
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,EduSubjectService eduSubjectService);

    List<OneSubject> getAllOneSubject();
}
