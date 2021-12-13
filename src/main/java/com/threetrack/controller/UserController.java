package com.threetrack.controller;

import com.threetrack.dto.UserRequestDto;
import com.threetrack.dto.ResponseDto;
import com.threetrack.dto.UserResponseDto;
import com.threetrack.service.UserService;
import com.threetrack.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseDto<List<UserResponseDto>> getAllUsers(){
        ResponseDto<List<UserResponseDto>> response= new ResponseDto<>();
        response.setData(userService.getAllUsers());
        response.setSuccess(true);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseDto<UserResponseDto> findUsers(@PathVariable(value = "id") Integer id){
        ResponseDto<UserResponseDto> response = new ResponseDto<>();
        response.setData(userService.getUserId(id));
        response.setSuccess(true);
        return response;
    }

    @PostMapping
    public ResponseDto<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto){
        ResponseDto<UserResponseDto> response= new ResponseDto<>();

        if(userService.addUser(userRequestDto)){
            response.setMessage(Constants.RESPONSE_CREATE);
            response.setSuccess(true);
            return response;
        }else{
            response.setSuccess(true);
            return response;
        }
    }

    @PutMapping
    public ResponseDto<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto){

        ResponseDto<UserResponseDto> response= new ResponseDto<>();
        if(userService.upUser(userRequestDto)){
            response.setMessage(Constants.RESPONSE_UPDATE);
            response.setSuccess(true);
            return response;
        }else{
            response.setMessage(Constants.ERROR);
            return response;
        }

    }

    @DeleteMapping("/{id}")
    public ResponseDto<UserResponseDto> deleteUser(@PathVariable(value = "id") Integer id){
        ResponseDto<UserResponseDto> response= new ResponseDto<>();
        if(userService.deleteUser(id)){
            response.setMessage(Constants.RESPONSE_DELETE);
            response.setSuccess(true);
            return response;
        }else{
            response.setMessage(Constants.ERROR);
            return response;
        }

    }
}