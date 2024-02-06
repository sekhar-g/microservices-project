package com.javaguides.departmentservice.controller;

import com.javaguides.departmentservice.dto.DepartmentDto;
import com.javaguides.departmentservice.entity.Department;
import com.javaguides.departmentservice.service.impl.DepartmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Department Service DepartmentController",
        description = "Department Service exposes REST APIs for Department Controller"
)
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentServiceImpl departmentService;

    @Operation(
            summary = "Save Department REST API",
            description = "Save Department REST API is used to save department object in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    // Build save department REST API
    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto){

       DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
       return new ResponseEntity<>(savedDepartment,HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Department REST API",
            description = "Get Department REST API is used to get department object from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build get department REST API
    @GetMapping("/{code}")
    public ResponseEntity<DepartmentDto> getDepartmentByCode(@PathVariable("code") String departmentCode){
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);

        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }
}
