package com.tfc.orderservice.controller;


import com.tfc.commonutils.R;
import com.tfc.orderservice.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author 田付成
 * @since 2021-07-27
 */
@RestController
@RequestMapping("/orderservice/t-order")
@CrossOrigin
public class TOrderController {
    @Autowired
    private TOrderService orderService;
    //根据课程id和用户id创建订单，返回订单id
//    @PostMapping("createOrder/{courseId}")
//    public R save(@PathVariable String courseId, HttpServletRequest request) {
//        String orderId = orderService.saveOrder(courseId,
//                JwtUtils.getMemberIdByJwtToken(request));
//        return R.ok().data("orderId", orderId);
//    }
}

