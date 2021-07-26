package com.tfc.orderservice.service.impl;

import com.tfc.orderservice.entity.TOrder;
import com.tfc.orderservice.mapper.TOrderMapper;
import com.tfc.orderservice.service.TOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-27
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

}
