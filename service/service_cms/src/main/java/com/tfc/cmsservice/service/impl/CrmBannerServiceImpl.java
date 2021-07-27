package com.tfc.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tfc.cmsservice.entity.CrmBanner;
import com.tfc.cmsservice.mapper.CrmBannerMapper;
import com.tfc.cmsservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;



import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-24
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {


    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectIndexList() {
        //根据id进行降序排列，显示排列之后的前两条记录
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //末尾手动拼接s'q'l
        wrapper.last("limit 2");

        List<CrmBanner> crmBannersList = baseMapper.selectList(wrapper);
        return crmBannersList;
    }
}
