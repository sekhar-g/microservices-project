package com.javaguides.employeeservice.service.Impl;

import com.javaguides.employeeservice.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
    // Build get department REST API
    @GetMapping("/api/department/{code}")
    DepartmentDto getDepartmentByCode(@PathVariable("code") String departmentCode);
}
