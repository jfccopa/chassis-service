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
        response.setData(roleService.getAllRoles());
        response.setSuccess(true);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseDto<RoleResponseDto> findRoles(@PathVariable(value = "id") Integer id){
        ResponseDto<RoleResponseDto> response = new ResponseDto<>();
        response.setData(roleService.getRoleId(id));
        response.setSuccess(true);
        return response;
    }

    @PostMapping
    public ResponseDto<RoleResponseDto> addRole(@RequestBody RoleRequestDto roleRequestDto){
        ResponseDto<RoleResponseDto> response= new ResponseDto<>();

        if(roleService.addRole(roleRequestDto)){
            response.setMessage(Constants.RESPONSE_CREATE);
            response.setSuccess(true);
            return response;
        }else{
            response.setSuccess(true);
            return response;
        }
    }

    @PutMapping
    public ResponseDto<RoleResponseDto> updateRole(@RequestBody RoleRequestDto roleRequestDto){

        ResponseDto<RoleResponseDto> response= new ResponseDto<>();
        if(roleService.upRole(roleRequestDto)){
            response.setMessage(Constants.RESPONSE_UPDATE);
            response.setSuccess(true);
            return response;
        }else{
            response.setMessage(Constants.ERROR);
            return response;
        }

    }

    @DeleteMapping("/{id}")
    public ResponseDto<RoleResponseDto> deleteRole(@PathVariable(value = "id") Integer id){
        ResponseDto<RoleResponseDto> response= new ResponseDto<>();
        if(roleService.deleteRole(id)){
            response.setMessage(Constants.RESPONSE_DELETE);
            response.setSuccess(true);
            return response;
        }else{
            response.setMessage(Constants.ERROR);
            return response;
        }

    }
}
