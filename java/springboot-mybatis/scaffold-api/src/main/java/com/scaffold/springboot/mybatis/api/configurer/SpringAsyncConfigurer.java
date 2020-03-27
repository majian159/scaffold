package com.scaffold.springboot.mybatis.api.configurer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * @author majian
 */
@Configuration
@EnableAsync
public class SpringAsyncConfigurer {

    @Bean(name = "pool")
    public Executor threadPoolTaskExecutor() {
        return ForkJoinPool.commonPool();
    }
}