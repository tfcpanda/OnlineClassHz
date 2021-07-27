package com.tfc.ucenterservice.controller;


import com.tfc.commonutils.JwtUtils;
import com.tfc.commonutils.R;
import com.tfc.servicebase.exceptionhandler.TfcException;
import com.tfc.ucenterservice.entity.UcenterMember;
import com.tfc.ucenterservice.entity.vo.LoginVo;
import com.tfc.ucenterservice.entity.vo.RegisterVo;
import com.tfc.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author 田付成
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/ucenterservice/ucenter-member")
@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody UcenterMember ucenterMember) {
        String token = ucenterMemberService.login(ucenterMember);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo) {
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request) {
        //调用jwt工具类方法，获取request,返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库中根据id获取用户信息
        UcenterMember ucenterMember = ucenterMemberService.getById(memberId);

        return R.ok().data("userInfo",ucenterMember);
    }

}

