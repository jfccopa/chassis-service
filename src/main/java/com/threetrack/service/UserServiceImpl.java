package com.threetrack.service;

import com.threetrack.dto.UserRequestDto;
import com.threetrack.dto.UserResponseDto;
import com.threetrack.entity.User;
import com.threetrack.repository.dao.UserDao;
import com.threetrack.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    public final Logger LOG = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userDao.list().stream()
                .map(user -> new UserResponseDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserId(Integer id) {
        User user=userDao.findById(id);
        if(user!=null)
            return new UserResponseDto(user);
        return null;
    }

    @Override
    public boolean addUser(UserRequestDto dto) {
        try {
            User user = new User();
            user.setName(dto.getName());
            user.setId(dto.getId());
            user.setPassword(dto.getPassword());
            user.setState(Constants.STATE_ACTIVO);
            user.setPersonId(dto.getPersonId());
            user.setOrganizationId(dto.getOrganizationId());
            
            userDao.add(user,0);
            return  true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    public boolean upUser(UserRequestDto object) {
        try {
            User user = userDao.findById(object.getId());
            user.setName(object.getName());
            user.setPassword(object.getPassword());
            user.setState(Constants.STATE_ACTIVO);
            user.setPersonId(object.getPersonId());
            user.setOrganizationId(object.getOrganizationId());
            userDao.update(user,0);
            return  true;
        }catch (Exception e){
            LOG.warning(e.getMessage());
            return  false;
        }
    }

    @Override
    public boolean deleteUser(Integer id) {
        try{
            return userDao.delete(id, 0);
        }catch (Exception ex){
            return false;
        }
    }
}
