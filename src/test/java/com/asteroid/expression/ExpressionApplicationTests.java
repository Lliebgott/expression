package com.asteroid.expression;

import com.asteroid.expression.common.config.properties.YamlProps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class ExpressionApplicationTests {

    @Autowired
    YamlProps yamlProps;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(yamlProps);
        System.out.println(123);
    }

}
