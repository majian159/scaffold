package com.scaffold.springboot.mybatis.api.department;

import com.scaffold.springboot.mybatis.api.department.dao.DepartmentExtensionMapper;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author majian
 */
@RestController
@RequestMapping("/api/v1/departmentLeaders")
public class DepartmentLeaderController {

    private DepartmentExtensionMapper extensionMapper;

    public DepartmentLeaderController(DepartmentExtensionMapper extensionMapper) {
        this.extensionMapper = extensionMapper;
    }

    @ApiOperation(value = "list", nickname = "listDepartmentLeader")
    @GetMapping
    public ResponseEntity<List<DepartmentLeaderModel>> list() {

        List<DepartmentLeaderModel> model = extensionMapper.getLeaders()
                .stream()
                .map(DepartmentLeaderModel::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(model);
    }

    @ApiOperation(value = "head", nickname = "headDepartmentLeader")
    @RequestMapping(value = "{name}", method = RequestMethod.HEAD)
    public ResponseEntity<Void> head(@PathVariable String name) {
        if (extensionMapper.exist(name)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @Data
    @AllArgsConstructor
    static class DepartmentLeaderModel {
        private String name;
    }
}
