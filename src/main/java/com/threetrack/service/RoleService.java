package com.threetrack.service;
import com.threetrack.dto.RoleRequestDto;
import com.threetrack.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleId(Integer id);
    boolean addRol(RoleRequestDto dto);
    boolean upRol(RoleRequestDto dto);
    boolean deleteRol(Integer id);
}
