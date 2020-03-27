package com.scaffold.springboot.mybatis.api.department.service;

import com.scaffold.springboot.mybatis.common.PageResult;
import com.scaffold.springboot.mybatis.common.api.PageQuery;
import com.scaffold.springboot.mybatis.common.exception.ServiceException;
import com.scaffold.springboot.mybatis.mbg.model.Department;
import lombok.*;

/**
 * @author majian
 */
public interface DepartmentService {

    int create(Department department) throws ServiceException;

    boolean update(@NonNull String name, @NonNull Department department) throws ServiceException;

    boolean delete(@NonNull String name);

    Department getByName(@NonNull String name);

    PageResult<Department> list(DepartmentQuery query);

    boolean exist(Integer id);

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = true)
    class DepartmentQuery extends PageQuery {
        private String nameKeyword;
        private String leaderKeyword;
    }
}

