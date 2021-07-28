package com.tfc.staservice.client;

import com.tfc.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 田付成
 * @date 2021/7/28 22:40
 */

@Component
@FeignClient("service-ucenter")
public interface UcenterClient {

    //统计一天注册的人数
    @GetMapping(value = "/ucenterservice/ucenter-member/countregister/{day}")
    public R registerCount(@PathVariable String day);
}
