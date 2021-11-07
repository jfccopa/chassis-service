package com.threetrack.controller;

import com.threetrack.dto.RoleRequestDto;
import com.threetrack.dto.ResponseDto;
import com.threetrack.entity.Role;
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
    public ResponseDto<List<Role>> getAllRoles(){
        ResponseDto<List<Role>> response= new ResponseDto<>();
        response.setData(roleService.getAllRoles());
        response.setSuccess(true);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseDto<Role> findRoles(@PathVariable(value = "id") Integer id){
        ResponseDto<Role> response = new ResponseDto<>();
        response.setData(roleService.getRoleId(id));
        response.setSuccess(true);
        return response;
    }

    @PostMapping
    public ResponseDto addRole(@RequestBody RoleRequestDto roleRequestDto){
        ResponseDto<Role> response= new ResponseDto<>();

        if(roleService.addRol(roleRequestDto)){
            response.setMessage(Constants.RESPONSE_CREATE);
            response.setSuccess(true);
            return response;
        }else{
            response.setSuccess(true);
            return response;
        }
    }

    @PutMapping
    public ResponseDto<Role> updateRole(@RequestBody RoleRequestDto roleRequestDto){

        ResponseDto<Role> response= new ResponseDto<>();
        if(roleService.upRol(roleRequestDto)){
            response.setMessage(Constants.RESPONSE_UPDATE);
            response.setSuccess(true);
            return response;
        }else{
            response.setMessage(Constants.ERROR);
            return response;
        }

    }

    @DeleteMapping("/{id}")
    public ResponseDto<Role> deleteRole(@PathVariable(value = "id") Integer id){
        ResponseDto<Role> response= new ResponseDto<>();
        if(roleService.deleteRol(id)){
            response.setMessage(Constants.RESPONSE_DELETE);
            response.setSuccess(true);
            return response;
        }else{
            response.setMessage(Constants.ERROR);
            return response;
        }

    }
}
