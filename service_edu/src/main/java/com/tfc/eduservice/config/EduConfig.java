package com.tfc.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author 田付成
 * @date 2021/6/24 17:42
 */

@Configuration
@MapperScan("com.tfc.eduservice.mapper")
public class EduConfig {
}
