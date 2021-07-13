package com.tfc.oss.controller;

import com.tfc.commonutils.R;
import com.tfc.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 田付成
* @date 2021/7/13 7:11
 *
*/

@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    public OssService ossService;

    @PostMapping
    public R uploadOssFile(MultipartFile file){
        //获取上传文件
        //返回上传的Oss路径
        String url = ossService.uploadFileAvatar(file);
        return R.ok().data("url",url);
    }

}
