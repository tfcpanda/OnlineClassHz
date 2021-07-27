package com.tfc.msmservice.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 田付成
 * @date 2021/7/26 0:14
 */
@Service
public interface MsmService {
    boolean send(String phone, String sms_180051135, Map<String, Object> param);
}
