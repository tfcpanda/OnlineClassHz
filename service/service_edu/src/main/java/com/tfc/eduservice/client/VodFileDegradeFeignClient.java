package com.tfc.eduservice.client;

import com.tfc.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 田付成
 * @date 2021/7/24 9:00
 */
@Component
public class VodFileDegradeFeignClient implements VodClient{
    @Override
    public R removeAlyiVideo(String id) {
        return R.error().message("time out");    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("time out");
    }
}
