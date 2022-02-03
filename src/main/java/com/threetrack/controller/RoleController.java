package com.threetrack.controller;

import com.threetrack.dto.RoleRequestDto;
import com.threetrack.dto.ResponseDto;
import com.threetrack.dto.RoleResponseDto;
import com.threetrack.service.RoleService;
import com.threetrack.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseDto<List<RoleResponseDto>> getAllRoles(){
        ResponseDto<List<RoleResponseDto>> response= new ResponseDto<>();
        try {
            response.setData(roleService.getAllRoles());
            response.setSuccess(true);
            response.setMessage(Constants.PROCESSED_OK); 
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(Constants.ERROR);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseDto<RoleResponseDto> findRoles(@PathVariable(value = "id") Integer id){
        ResponseDto<RoleResponseDto> response = new ResponseDto<>();
        try {
            RoleResponseDto roleResponseDto = roleService.getRoleId(id);
            if(roleResponseDto != null){
                response.setData(roleResponseDto);
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
    public ResponseDto<RoleResponseDto> addRole(@RequestBody RoleRequestDto roleRequestDto){
        ResponseDto<RoleResponseDto> response= new ResponseDto<>();
        try {
            if(roleService.addRole(roleRequestDto)){
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
    public ResponseDto<RoleResponseDto> updateRole(@RequestBody RoleRequestDto roleRequestDto){
        ResponseDto<RoleResponseDto> response= new ResponseDto<>();
        try {
            if(roleService.upRole(roleRequestDto)){
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
    public ResponseDto<RoleResponseDto> deleteRole(@PathVariable(value = "id") Integer id){
        ResponseDto<RoleResponseDto> response= new ResponseDto<>();
        try {
            if(roleService.deleteRole(id)){
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
