package com.threetrack.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestDto {
    private Integer id;
    private String name;
    private String password;
    private String state;
    private Integer personId;
    private Integer organizationId;
}
