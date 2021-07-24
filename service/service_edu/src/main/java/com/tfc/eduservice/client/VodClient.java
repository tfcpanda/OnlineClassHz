package com.tfc.eduservice.client;

import com.tfc.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 田付成
 * @date 2021/7/24 1:06
 */

@FeignClient("service-vod")
@Component
public interface VodClient {

    @DeleteMapping("/eduvod/video/removeAlyiVideo/{id}")
    public R removeAlyiVideo(@PathVariable("id") String id);


    @DeleteMapping("/eduvod/video/delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
