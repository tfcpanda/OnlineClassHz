package com.tfc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tfc.eduservice.client.VodClient;
import com.tfc.eduservice.entity.EduVideo;
import com.tfc.eduservice.mapper.EduVideoMapper;
import com.tfc.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-19
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;

    @Override
    public void removeByCourseId(String courseId) {

        //根据id删除所有的视频
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("chapter_id",courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);

        //list<EduVideo>拆分
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i< eduVideoList.size();i++){
            EduVideo eduVideo = eduVideoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();

            if (!StringUtils.isEmpty(videoSourceId)){
                videoIds.add(videoSourceId);
            }


        }
            if (videoIds.size()>0){
                vodClient.deleteBatch(videoIds);

            }



        //==============================
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",courseId);
        baseMapper.delete(wrapper);
    }
}
