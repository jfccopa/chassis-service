package com.threetrack.dto;

import lombok.NoArgsConstructor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrganizationRequestDto {
    private Integer id;
    private String  name;
    private String  description;
    private char    state;    
}
