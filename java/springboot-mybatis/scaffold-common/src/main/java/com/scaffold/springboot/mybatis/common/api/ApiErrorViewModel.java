package com.scaffold.springboot.mybatis.common.api;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author majian
 */
@Data
@Builder
public class ApiErrorViewModel {
    private String message;
    private List<Object> errors;
}
