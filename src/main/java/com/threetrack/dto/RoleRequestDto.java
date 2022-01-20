package com.threetrack.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleRequestDto {
    private Integer id;
    private String name;
    private String code;
    private char state;
}
