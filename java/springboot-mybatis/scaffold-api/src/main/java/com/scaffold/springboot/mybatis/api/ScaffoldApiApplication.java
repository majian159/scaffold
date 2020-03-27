package com.scaffold.springboot.mybatis.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author majian
 */
@SpringBootApplication(scanBasePackages = "${application.basePackage}")
public class ScaffoldApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScaffoldApiApplication.class, args);
    }

}
