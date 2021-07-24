package com.tfc.cmsservice.controller;

import com.tfc.cmsservice.entity.CrmBanner;
import com.tfc.cmsservice.service.CrmBannerService;
import com.tfc.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 田付成
 * @date 2021/7/24 22:40
 */

@Api(description = "网站首页Banner列表")
@RestController
@RequestMapping("/cmsservice/crm-banner")
@CrossOrigin
public class CrmBannerFontController {

    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public R index() {
        List<CrmBanner> list = bannerService.selectIndexList();
        return R.ok().data("bannerList", list);
    }
}
