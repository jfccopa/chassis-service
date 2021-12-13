package com.threetrack.service;
import com.threetrack.dto.UserRequestDto;
import com.threetrack.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserId(Integer id);
    boolean addUser(UserRequestDto dto);
    boolean upUser(UserRequestDto dto);
    boolean deleteUser(Integer id);
}
