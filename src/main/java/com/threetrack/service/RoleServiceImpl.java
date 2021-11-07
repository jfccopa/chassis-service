package com.threetrack.service;

import com.threetrack.dto.RoleRequestDto;
import com.threetrack.entity.Role;
import com.threetrack.repository.dao.RoleDao;
import com.threetrack.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class RoleServiceImpl implements RoleService{

    public final Logger LOG = Logger.getLogger(RoleServiceImpl.class.getName());

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAllRoles() {
        return roleDao.list();
    }

    @Override
    public Role getRoleId(Integer id) {
        return roleDao.findById(id);
    }

    @Override
    public boolean addRol(RoleRequestDto dto) {
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
    public boolean upRol(RoleRequestDto object) {
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
    public boolean deleteRol(Integer id) {
        try{
            roleDao.delete(id, 0);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
