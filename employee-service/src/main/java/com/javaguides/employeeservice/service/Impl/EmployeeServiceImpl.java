package com.javaguides.employeeservice.service.Impl;

import com.javaguides.employeeservice.dto.APIResponseDto;
import com.javaguides.employeeservice.dto.DepartmentDto;
import com.javaguides.employeeservice.dto.EmployeeDto;
import com.javaguides.employeeservice.dto.OrganizationDto;
import com.javaguides.employeeservice.entity.Employee;
import com.javaguides.employeeservice.repository.EmployeeRepository;
import com.javaguides.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;
    private WebClient webClient;
//    private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {

        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()
        );

        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savvedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode(),
                savedEmployee.getOrganizationCode()
        );

        return savvedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).get();

        System.out.println("DepartmentCode: "+employee.getDepartmentCode());

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate
//                .getForEntity("http://localhost:8080/api/department/"+employee.getDepartmentCode(),
//                DepartmentDto.class);
//        DepartmentDto departmentDto = responseEntity.getBody();

        DepartmentDto departmentDto = webClient.get()
                .uri("http://localhost:8080/api/department/"+employee.getDepartmentCode())
                .retrieve()
                .bodyToMono(DepartmentDto.class)
                .block();

//        DepartmentDto departmentDto = apiClient.getDepartmentByCode(employee.getDepartmentCode());

        OrganizationDto organizationDto = webClient.get()
                .uri("http://localhost:8084/api/organizations/"+employee.getOrganizationCode())
                .retrieve()
                .bodyToMono(OrganizationDto.class)
                .block();

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );

        APIResponseDto apiResponseDto = new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);
        apiResponseDto.setOrganization(organizationDto);

        return apiResponseDto;
    }
}
