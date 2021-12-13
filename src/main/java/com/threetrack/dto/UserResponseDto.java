package com.threetrack.dto;

import com.threetrack.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Integer id;
    private String name;
    private String password;
    private String state;
    private Integer personId;
    private Integer organizationId;

    public UserResponseDto(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.state = user.getState();
        this.personId = user.getPersonId();
        this.organizationId = user.getOrganizationId();
    }
}
