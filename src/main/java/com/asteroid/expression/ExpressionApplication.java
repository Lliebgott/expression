package com.asteroid.expression;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.asteroid.expression.demo.dao")
public class ExpressionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpressionApplication.class, args);
    }

}
