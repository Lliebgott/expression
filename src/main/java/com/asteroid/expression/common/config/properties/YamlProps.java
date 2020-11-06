package com.asteroid.expression.common.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: YuSai
 * @date: 2020-10-26 18:03
 */
@Configuration
public class YamlProps {

    @ConfigurationProperties(prefix = "spring.profiles")
    @Bean
    public YmlProperties getProps(){
        return new YmlProperties();
    }

}
