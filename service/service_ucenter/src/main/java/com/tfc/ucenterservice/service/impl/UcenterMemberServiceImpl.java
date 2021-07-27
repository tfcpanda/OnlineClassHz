package com.tfc.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tfc.commonutils.JwtUtils;
import com.tfc.commonutils.MD5;
import com.tfc.servicebase.exceptionhandler.TfcException;
import com.tfc.ucenterservice.entity.UcenterMember;
import com.tfc.ucenterservice.entity.vo.LoginVo;
import com.tfc.ucenterservice.entity.vo.RegisterVo;
import com.tfc.ucenterservice.mapper.UcenterMemberMapper;
import com.tfc.ucenterservice.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-27
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /*
     * 会员登录
     * */
    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //校验参数
        if (StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(mobile)) {
            throw new TfcException(20001, "error");
        }
        //获取会员
        UcenterMember member = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));

        if (null == member) {
            throw new TfcException(20001, "error");
        }
        //校验密码
        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new TfcException(20001, "error");
        }
        //校验是否被禁用
        if (member.getIsDisabled()) {
            throw new TfcException(20001, "error");
        }
        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {

    }
}
