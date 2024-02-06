package com.javaguides.employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class APIResponseDto {
    private EmployeeDto employee;
    private DepartmentDto department;
    private OrganizationDto organization;
}
