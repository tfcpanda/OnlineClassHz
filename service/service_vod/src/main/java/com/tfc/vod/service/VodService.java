package com.tfc.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 田付成
 * @date 2021/7/23 5:38
 */
public interface VodService {
    String uploadAlyiVideo(MultipartFile file);

    void removeMoreAlyVideo(List videoIdList);
}
