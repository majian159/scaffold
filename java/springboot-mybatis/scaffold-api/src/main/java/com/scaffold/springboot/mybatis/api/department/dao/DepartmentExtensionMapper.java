package com.scaffold.springboot.mybatis.api.department.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author majian
 */
@Mapper
@Component
public interface DepartmentExtensionMapper {

    /**
     * 获取部门中所有的经理姓名
     *
     * @return 经理姓名集合
     */
    @Select("select leader from department group by leader")
    List<String> getLeaders();

    /**
     * 根据经理名称检索改经理是否存在
     * 通过自定义xml配置
     *
     * @param name 经理名称
     * @return true or false
     */
    boolean exist(@Param("name") String name);
}
