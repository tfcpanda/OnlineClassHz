package com.tfc.ucenterservice.service;

import com.tfc.ucenterservice.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tfc.ucenterservice.entity.vo.LoginVo;
import com.tfc.ucenterservice.entity.vo.RegisterVo;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-27
 */

@Component
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    //注册
    void register(RegisterVo registerVo);
}
