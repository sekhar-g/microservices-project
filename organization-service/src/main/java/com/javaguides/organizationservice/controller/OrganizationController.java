package com.javaguides.organizationservice.controller;

import com.javaguides.organizationservice.dto.OrganizationDto;
import com.javaguides.organizationservice.entity.Organization;
import com.javaguides.organizationservice.service.Impl.OrganizationServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "Organization Service OrganizationController",
        description = "Organization Service exposes REST APIs for Organization Controller"
)
@RestController
@RequestMapping("api/organizations")
public class OrganizationController {

    @Autowired
    private OrganizationServiceImpl organizationService;

    @Operation(
            summary = "Save Organization REST API",
            description = "Save Organization REST API is used to save Organization object in a database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    // Build Save Organization REST API
    @PostMapping("add")
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto organizationDto){
        OrganizationDto savedOrg = organizationService.saveOrganization(organizationDto);

        return new ResponseEntity<>(savedOrg, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get Organization REST API",
            description = "Get Organization REST API is used to get Organization object from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCCESS"
    )
    // Build Get Organization REST API
    @GetMapping("/{id}")
    public ResponseEntity<OrganizationDto> getOrganizationByCode(@PathVariable String id){

        OrganizationDto organizationDto = organizationService.getOrganizationByCode(id);

        return new ResponseEntity<>(organizationDto, HttpStatus.OK);
    }
}
