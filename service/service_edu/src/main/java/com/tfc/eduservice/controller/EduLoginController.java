package com.tfc.eduservice.controller;

import com.tfc.commonutils.R;
import org.springframework.web.bind.annotation.*;

/**
 * @author 田付成
 * @date 2021/7/11 22:28
 */

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    //login
    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }


    //info
    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "");
    }

}
