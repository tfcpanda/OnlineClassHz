package com.tfc.eduservice.controller;


import com.tfc.commonutils.R;
import com.tfc.eduservice.client.VodClient;
import com.tfc.eduservice.entity.EduVideo;
import com.tfc.eduservice.service.EduVideoService;
import com.tfc.servicebase.exceptionhandler.TfcException;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 田付成
 * @since 2021-07-19
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodClient vodClient;

    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    //删除小节
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        //根据小节id获取视频id,调用方法删除
        EduVideo eduVideo = eduVideoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if (!StringUtils.isEmpty(videoSourceId)){
            R result = vodClient.removeAlyiVideo(videoSourceId);
            if (result.getCode() == 20001){
                throw new TfcException(20001,"删除视频失败使用熔断器");
            }
        }

        eduVideoService.removeById(id);
        return R.ok();
    }

    //修改小节

}

