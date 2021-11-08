package com.threetrack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class RoleResponseDto {
    private Integer id;
    private String name;
    private String code;
    private String state;
}
