package com.threetrack.controller;

import com.threetrack.dto.OrganizationRequestDto;
import com.threetrack.dto.OrganizationResponseDto;
import com.threetrack.dto.ResponseDto;
import com.threetrack.service.OrganizationService;
import com.threetrack.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Organizations")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping
    public ResponseDto<List<OrganizationResponseDto>> getAllOrganizations(){        
        ResponseDto<List<OrganizationResponseDto>> response = new ResponseDto<>();
        try{            
            response.setData(organizationService.getAllOrganizations());
            response.setSuccess(true);            
        }catch(Exception e){
            response.setSuccess(false);            
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseDto<OrganizationResponseDto> findOrganizations(@PathVariable(value = "id") Integer id){
        ResponseDto<OrganizationResponseDto> response = new ResponseDto<>();
        try{
            OrganizationResponseDto organizationResponseDto = organizationService.getOrganizationId(id);
            if (organizationResponseDto != null){ 
                response.setData(organizationResponseDto);
            }else{
                response.setMessage(Constants.ERROR_NO_DATA);
            }
            response.setSuccess(true);
        }catch(Exception e){
            response.setSuccess(false);
        }
        return response;    
    }

    @PostMapping
    public ResponseDto<OrganizationResponseDto> addOrganization(@RequestBody OrganizationRequestDto organizationRequestDto){
        ResponseDto<OrganizationResponseDto> response = new ResponseDto<>();
        try{
            if(organizationService.addOrganization(organizationRequestDto)){
                response.setMessage(Constants.RESPONSE_CREATE);
                
            }
            response.setSuccess(true);
        }catch(Exception e){
            response.setSuccess(false);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseDto<OrganizationResponseDto> deleteOrganization(@PathVariable(value = "id") Integer id){
        ResponseDto<OrganizationResponseDto> response = new ResponseDto<>();        
        try{
            if(organizationService.deleteOrganization(id)){
                response.setMessage(Constants.RESPONSE_DELETE);
            }else{
                response.setMessage(Constants.ERROR_NO_DATA);                
            }
            response.setSuccess(true);
        }catch(Exception e){
            response.setSuccess(false);            
        }
        return response;
    }
}
