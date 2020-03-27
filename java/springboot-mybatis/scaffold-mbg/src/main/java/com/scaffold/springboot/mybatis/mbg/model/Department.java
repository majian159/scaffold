package com.scaffold.springboot.mybatis.mbg.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Department implements Serializable {
    private Integer id;

    private String name;

    private String leader;

    private static final long serialVersionUID = 1L;
}