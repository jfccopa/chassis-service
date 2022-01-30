package com.threetrack.dto;

import com.threetrack.entity.Organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrganizationResponseDto {
    private Integer id;
    private String  name;
    private String  description;
    private char    state;    

    public OrganizationResponseDto(Organization organization){
        this.id          = organization.getId();
        this.name        = organization.getName();
        this.description = organization.getDescription();
        this.state       = organization.getState();
    }
}
