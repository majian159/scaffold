package com.scaffold.springboot.mybatis.api.configurer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author majian
 */
@Configuration
@EnableSwagger2
public class Swagger2Configurer {

    @Value("${application.basePackage}")
    private String basePackage;

    private static ApiInfo apiInfo(String moduleName) {
        return new ApiInfoBuilder()
                .title(moduleName + " api")
                .version("1.0")
                .build();
    }

    private Docket getDocket(String name, String packageSuffix) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(name))
                .groupName(name)
                .select()
                .apis(RequestHandlerSelectors.basePackage(String.format("%s.%s", basePackage, packageSuffix)))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket departmentDocket() {
        return getDocket("department", "api.department");
    }

}
