package com.javaguides.organizationservice.service.Impl;

import com.javaguides.organizationservice.dto.OrganizationDto;
import com.javaguides.organizationservice.entity.Organization;
import com.javaguides.organizationservice.mapper.OrganizationMapper;
import com.javaguides.organizationservice.repository.OrganizationRepository;
import com.javaguides.organizationservice.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        //convert OrganizationDto into Organization jpa entity
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        OrganizationDto savedOrganizationDto = OrganizationMapper.mapToOrganizationDto(savedOrganization);

        return savedOrganizationDto;
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {

        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);

        OrganizationDto organizationDto = OrganizationMapper.mapToOrganizationDto(organization);

        return organizationDto;
    }
}
