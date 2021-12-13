package com.threetrack.service;
import com.threetrack.dto.RoleRequestDto;
import com.threetrack.dto.RoleResponseDto;

import java.util.List;

public interface RoleService {
    List<RoleResponseDto> getAllRoles();
    RoleResponseDto getRoleId(Integer id);
    boolean addRole(RoleRequestDto dto);
    boolean upRole(RoleRequestDto dto);
    boolean deleteRole(Integer id);
}
