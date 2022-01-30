package com.threetrack.service;

import com.threetrack.dto.RoleRequestDto;
import com.threetrack.dto.RoleResponseDto;
import com.threetrack.entity.Role;
import com.threetrack.repository.dao.RoleDao;
import com.threetrack.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{

    public final Logger LOG = Logger.getLogger(RoleServiceImpl.class.getName());

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<RoleResponseDto> getAllRoles() {
        return roleDao.list().stream()
                .map(role -> new RoleResponseDto(role.getId(),role.getName(),role.getCode(),role.getState()) )
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDto getRoleId(Integer id) {
        Role role=roleDao.findById(id);
        if(role!=null)
            return  new RoleResponseDto(role.getId(),role.getName(),role.getCode(),role.getState());
        return null;
    }

    @Override
    public boolean addRole(RoleRequestDto dto) {
        try {
            Role role = new Role();
            role.setName(dto.getName());
            role.setId(dto.getId());
            role.setCode(dto.getCode());
            role.setState(Constants.STATE_ACTIVO);
            roleDao.add(role,0);
            return  true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean upRole(RoleRequestDto object) {
        try {
            Role role = roleDao.findById(object.getId());
            role.setState(object.getState());
            role.setCode(object.getCode());
            role.setName(object.getName());
            roleDao.update(role,0);
            return  true;
        }catch (Exception e){
            LOG.warning(e.getMessage());
            return  false;
        }
    }

    @Override
    public boolean deleteRole(Integer id) {
        try{
            return roleDao.delete(id, 0);
        }catch (Exception ex){
            return false;
        }
    }
}
