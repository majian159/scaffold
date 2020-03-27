package com.scaffold.springboot.mybatis.api.department.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.scaffold.springboot.mybatis.api.department.service.DepartmentService;
import com.scaffold.springboot.mybatis.common.PageResult;
import com.scaffold.springboot.mybatis.common.exception.ServiceException;
import com.scaffold.springboot.mybatis.mbg.mapper.DepartmentMapper;
import com.scaffold.springboot.mybatis.mbg.model.Department;
import com.scaffold.springboot.mybatis.mbg.model.DepartmentExample;
import lombok.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * @author majian
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentMapper mapper;

    public DepartmentServiceImpl(DepartmentMapper mapper) {
        this.mapper = mapper;
    }

    private static DepartmentExample byName(String name) {
        DepartmentExample example = new DepartmentExample();
        example.createCriteria().andNameEqualTo(name);
        return example;
    }

    @Override
    public int create(@NonNull Department department) throws ServiceException {

        String name = department.getName();
        if (this.existByName(name)) {
            throw ServiceException.alreadyExists(String.format("已经存在名称为 [%s] 的部门", name));
        }

        return mapper.insert(department);
    }

    @Override
    public boolean update(@NonNull String name, @NonNull Department department) throws ServiceException {

        department.setName(name);
        if (!this.existByName(name)) {
            throw ServiceException.notFound(String.format("找不到名称为 [%s] 的部门", name));
        }

        return mapper.updateByExampleSelective(department, byName(name)) > 0;
    }

    @Override
    public boolean delete(@NonNull String name) {
        return mapper.deleteByExample(byName(name)) > 0;
    }

    @Override
    public PageResult<Department> list(@NonNull DepartmentQuery query) {

        DepartmentExample example = new DepartmentExample();

        DepartmentExample.Criteria criteria = example.createCriteria();

        String nameKeyword = query.getNameKeyword();
        String leaderKeyword = query.getLeaderKeyword();

        if (!StringUtils.isEmpty(nameKeyword)) {
            criteria.andNameLike("%" + nameKeyword + "%");
        }

        if (!StringUtils.isEmpty(leaderKeyword)) {
            criteria.andLeaderLike("%" + leaderKeyword + "%");
        }

        Page<Department> page = PageHelper.startPage(query.getPageNumber(), query.getPageSize())
                .doSelectPage(() -> mapper.selectByExample(example));

        return new PageResult<>(new ArrayList<>(page.getResult()), page.getTotal());
    }

    @Override
    public boolean exist(Integer id) {
        DepartmentExample example = new DepartmentExample();
        example.createCriteria().andIdEqualTo(id);
        return mapper.countByExample(example) > 0;
    }

    @Override
    public Department getByName(@NonNull String name) {

        return mapper.selectByExample(byName(name)).stream().findAny().orElse(null);
    }

    private boolean existByName(@NonNull String name) {
        DepartmentExample example = new DepartmentExample();
        example.createCriteria().andNameEqualTo(name);
        return mapper.countByExample(example) > 0;
    }
}
