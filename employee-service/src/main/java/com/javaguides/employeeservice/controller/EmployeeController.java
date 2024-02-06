package com.javaguides.employeeservice.controller;

import com.javaguides.employeeservice.dto.APIResponseDto;
import com.javaguides.employeeservice.dto.EmployeeDto;
import com.javaguides.employeeservice.service.Impl.EmployeeServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Employee Service EmployeeController",
        description = "Employee Service exposes REST APIs for Employee Controller"
)
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Operation(
            summary = "Save Employee REST API",
            description = "Save Employee REST API is used to save Employee object in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    // Build save Employee REST API
    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Employee REST API",
            description = "Get Employee REST API is used to get Employee object from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build Get Employee REST API
    @GetMapping("{employeeId}")
    public ResponseEntity<APIResponseDto> getEmployee(@PathVariable("employeeId") Long id){
        APIResponseDto apiResponseDto = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
}
