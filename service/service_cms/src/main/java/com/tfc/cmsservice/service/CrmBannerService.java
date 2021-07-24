package com.tfc.cmsservice.service;

import com.tfc.cmsservice.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-24
 */
public interface CrmBannerService extends IService<CrmBanner> {

    //查询前台所有
    List<CrmBanner> selectIndexList();
}
