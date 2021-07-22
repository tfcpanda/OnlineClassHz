package com.tfc.eduservice.service;

import com.tfc.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tfc.eduservice.entity.section.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-19
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getAllChapterByCourseId(String courseId);

    boolean deleteChapter(String chapterId);

    void removeByCourseId(String courseId);
}
