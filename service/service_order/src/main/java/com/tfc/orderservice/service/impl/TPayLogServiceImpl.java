package com.tfc.orderservice.service.impl;

import com.tfc.orderservice.entity.TPayLog;
import com.tfc.orderservice.mapper.TPayLogMapper;
import com.tfc.orderservice.service.TPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author 田付成
 * @since 2021-07-27
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements TPayLogService {

}
