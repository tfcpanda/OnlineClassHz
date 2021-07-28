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
import org.springframework.beans.BeanUtils;
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
    public String login(UcenterMember ucenterMember) {
        String mobile = ucenterMember.getMobile();
        String password = ucenterMember.getPassword();
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

        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        //校验参数
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {

            throw new TfcException(20001, "error");
        }
        //校验校验验证码
        //从redis获取发送的验证码
//        String mobleCode = redisTemplate.opsForValue().get(mobile);
//        if (!code.equals(mobleCode)) {
//            throw new TfcException(20001, "error");
//        }
        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new
                QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (count.intValue() > 0) {
            throw new TfcException(20001, "error");
        }
        //添加注册信息到数据库
        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(member);

    }

    //统计一天注册的人数
    @Override
    public Integer countRegisterByDay(String day) {
        Integer integer = baseMapper.selectRegisterCount(day);
        return integer;
    }


}
