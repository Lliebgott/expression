package com.asteroid.expression.common.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: YuSai
 * @date: 2020-10-21 17:23
 */
@Configuration
@MapperScan("com.asteroid.expression.*.dao")
public class MyBatisConfig {

}
