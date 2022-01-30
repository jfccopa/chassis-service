package com.threetrack.service;

import com.threetrack.dto.OrganizationRequestDto;
import com.threetrack.dto.OrganizationResponseDto;

import java.util.List;

public interface OrganizationService {
    List<OrganizationResponseDto> getAllOrganizations();
    OrganizationResponseDto getOrganizationId(Integer id);
    boolean addOrganization(OrganizationRequestDto dto);
    boolean upOrganization(OrganizationRequestDto dto);
    boolean deleteOrganization(Integer id);
}
