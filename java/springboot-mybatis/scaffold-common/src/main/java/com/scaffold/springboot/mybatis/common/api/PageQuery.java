package com.scaffold.springboot.mybatis.common.api;

import lombok.ToString;

/**
 * @author majian
 */
@ToString
public class PageQuery {
    public static Integer DEFAULT_PAGE_NUMBER = 1;
    public static Integer DEFAULT_PAGE_SIZE = 10;
    private Integer pageNumber;
    private Integer pageSize;

    public Integer getPageNumber() {
        if (pageNumber == null) {
            return DEFAULT_PAGE_NUMBER;
        }

        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        if (pageNumber < DEFAULT_PAGE_NUMBER) {
            throw new IllegalArgumentException(String.format("pageNumber Not less than %s.", DEFAULT_PAGE_NUMBER));
        }

        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return DEFAULT_PAGE_SIZE;
        }

        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        if (pageSize < 0) {
            throw new IllegalArgumentException("pageNumber Not less than 0.");
        }

        this.pageSize = pageSize;
    }
}
