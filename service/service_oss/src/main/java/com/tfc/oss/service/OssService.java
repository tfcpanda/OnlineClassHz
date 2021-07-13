package com.tfc.oss.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 田付成
 * @date 2021/7/13 7:12
 */

@Component
public interface OssService {
         String uploadFileAvatar(MultipartFile file);
}
