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
        try {
            response.setData(userService.getAllUsers());
            response.setSuccess(true);
            response.setMessage(Constants.PROCESSED_OK);    
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(Constants.ERROR);
        }
        
        return response;
    }

    @GetMapping("/{id}")
    public ResponseDto<UserResponseDto> findUsers(@PathVariable(value = "id") Integer id){
        ResponseDto<UserResponseDto> response = new ResponseDto<>();
        try {
            UserResponseDto userResponseDto = userService.getUserId(id);
            if(userResponseDto != null){
                response.setData(userResponseDto);   
                response.setMessage(Constants.PROCESSED_OK);
            }else{
                response.setMessage(Constants.ERROR_NO_DATA);
            } 
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(Constants.ERROR);
        }
        
        return response;
    }

    @PostMapping
    public ResponseDto<UserResponseDto> addUser(@RequestBody UserRequestDto userRequestDto){
        ResponseDto<UserResponseDto> response= new ResponseDto<>();
        try {
            if(userService.addUser(userRequestDto)){
                response.setMessage(Constants.RESPONSE_CREATE);
            }else{
                response.setMessage(Constants.ERROR_CREATE);
            }    
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(Constants.ERROR);
        }
        return response;
    }

    @PutMapping
    public ResponseDto<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto){
        ResponseDto<UserResponseDto> response= new ResponseDto<>();
        try {
            if(userService.upUser(userRequestDto)){
                response.setMessage(Constants.RESPONSE_UPDATE);
            }else{
                response.setMessage(Constants.ERROR_NO_DATA);
            }
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(Constants.ERROR);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseDto<UserResponseDto> deleteUser(@PathVariable(value = "id") Integer id){
        ResponseDto<UserResponseDto> response= new ResponseDto<>();
        try {
            if(userService.deleteUser(id)){
                response.setMessage(Constants.RESPONSE_DELETE);
            }else{
                response.setMessage(Constants.ERROR_NO_DATA);
            }
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(Constants.ERROR);
        }
        return response;
    }
}