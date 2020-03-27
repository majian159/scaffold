package com.scaffold.springboot.mybatis.common;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author majian
 */
@Data
@AllArgsConstructor
public class PageResult<T> {
    private List<T> items;
    private Long total;
}
