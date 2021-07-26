package com.tfc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tfc.eduservice.entity.EduChapter;
import com.tfc.eduservice.entity.EduVideo;
import com.tfc.eduservice.entity.section.ChapterVo;
import com.tfc.eduservice.entity.section.VideoVo;
import com.tfc.eduservice.mapper.EduChapterMapper;
import com.tfc.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tfc.eduservice.service.EduVideoService;
import com.tfc.servicebase.exceptionhandler.TfcException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-19
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {


    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getAllChapterByCourseId(String courseId) {
        //1.查询所有的chapter的章节
        QueryWrapper<EduChapter> wrapperChapter = new QueryWrapper<>();
        wrapperChapter.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(wrapperChapter);

        //2.查询所有video的小姐
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(wrapperVideo);

        //创建list集合，用于最后的封装
        List<ChapterVo> finallList = new ArrayList<>();
        //封装所有的chapter的章节
        //遍历所有的list集合
        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            //把eduChapter对里面的内容复制
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finallList.add(chapterVo);


            //创建集合用来封装
            List<VideoVo> videoList = new ArrayList<>();

            for (int j = 0; j < eduVideoList.size(); j++) {
                EduVideo eduVideo = eduVideoList.get(j);

                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    //进行封装
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    //放入到小章节中
                    videoList.add(videoVo);
                }


            }
            chapterVo.setVideoList(videoList);
        }

        //封装所有的video的章节
        return finallList;
    }


    //删除章节
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据id来查询章节id,如果有数据就不删除
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id", chapterId);
        int count = eduVideoService.count(wrapper);
        if (count > 0){
            //有小节不删除
            throw new TfcException(20001,"有小节不能删除");
        }else{
            int flag =   baseMapper.deleteById(chapterId);
            return flag >0;
        }

    }

    @Override
    public void removeByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }

    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        return null;
    }


}
