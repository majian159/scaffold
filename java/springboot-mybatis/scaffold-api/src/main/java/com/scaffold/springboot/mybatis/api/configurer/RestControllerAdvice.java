package com.scaffold.springboot.mybatis.api.configurer;

import com.scaffold.springboot.mybatis.common.api.ApiErrorViewModel;
import com.scaffold.springboot.mybatis.common.exception.ApiException;
import com.scaffold.springboot.mybatis.common.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author majian
 */
@Slf4j
@ControllerAdvice
public class RestControllerAdvice implements WebMvcConfigurer {

    @ExceptionHandler(value = {ServiceException.class, ApiException.class})
    @Order(Integer.MIN_VALUE)
    public ResponseEntity<ApiErrorViewModel> handleException(Throwable throwable) throws Throwable {

        ApiException apiException = null;
        if (throwable instanceof ApiException) {
            apiException = (ApiException) throwable;
        } else if (throwable instanceof ServiceException) {
            apiException = new ApiException((ServiceException) throwable);
        }

        if (apiException == null) {
            log.error("发生了未知错误", throwable);
            throw throwable;
        }

        ApiErrorViewModel model = ApiErrorViewModel.builder()
                .message(apiException.getMessage())
                .build();

        return ResponseEntity.status(apiException.getStatus()).body(model);
    }

}
