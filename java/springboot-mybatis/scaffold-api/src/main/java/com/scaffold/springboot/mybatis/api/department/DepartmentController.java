package com.scaffold.springboot.mybatis.api.department;

import com.scaffold.springboot.mybatis.api.department.service.DepartmentService;
import com.scaffold.springboot.mybatis.common.PageResult;
import com.scaffold.springboot.mybatis.common.exception.ServiceException;
import com.scaffold.springboot.mybatis.mbg.model.Department;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author majian
 */
@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    private DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @ApiOperation(value = "list", nickname = "listDepartment")
    @GetMapping
    public ResponseEntity<PageResult<DepartmentModel>> list(DepartmentService.DepartmentQuery query) {

        PageResult<Department> page = service.list(query);

        List<DepartmentModel> models = page.getItems().stream().map(d -> {
            DepartmentModel model = new DepartmentModel();
            BeanUtils.copyProperties(d, model);
            return model;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(new PageResult<>(models, page.getTotal()));
    }

    @ApiOperation(value = "get", nickname = "getDepartment")
    @GetMapping("{name}")
    public ResponseEntity<DepartmentModel> get(@PathVariable String name) {

        Department department = service.getByName(name);

        if (department == null) {
            return ResponseEntity.notFound().build();
        }

        DepartmentModel model = new DepartmentModel();
        BeanUtils.copyProperties(department, model);

        return ResponseEntity.ok(model);
    }

    @ApiOperation(value = "post", nickname = "postDepartment")
    @PostMapping
    public ResponseEntity<Void> post(@RequestBody @Valid PostDepartmentModel model) throws ServiceException {

        Department department = Department.builder()
                .leader(model.getLeader())
                .name(model.getName())
                .build();

        service.create(department);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ApiOperation(value = "delete", nickname = "deleteDepartment")
    @DeleteMapping("{name}")
    public ResponseEntity<Void> delete(@PathVariable String name) {
        boolean success = service.delete(name);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "patch", nickname = "patchDepartment")
    @PatchMapping("{name}")
    public ResponseEntity<Void> patch(@PathVariable String name, @RequestBody @Valid PatchDepartmentModel model) throws ServiceException {
        Department department = Department.builder()
                .leader(model.getLeader())
                .build();

        service.update(name, department);
        return ResponseEntity.noContent().build();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    static class DepartmentModel {
        private String name;
        private String leader;
    }

    @Data
    static class PostDepartmentModel {
        private String name;
        private String leader;
    }

    @Data
    static class PatchDepartmentModel {
        private String leader;
    }
}
