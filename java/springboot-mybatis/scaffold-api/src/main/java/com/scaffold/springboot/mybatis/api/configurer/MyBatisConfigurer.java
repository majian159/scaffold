package com.scaffold.springboot.mybatis.api.configurer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author majian
 */
@Configuration
@MapperScan({"${application.basePackage}.mbg.mapper", "${application.basePackage}.api.**.dao"})
public class MyBatisConfigurer {
}