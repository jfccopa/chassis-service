package com.threetrack.service;

import java.util.stream.Collectors;

import com.threetrack.dto.OrganizationRequestDto;
import com.threetrack.dto.OrganizationResponseDto;
import com.threetrack.entity.Organization;
import com.threetrack.repository.dao.OrganizationDao;
import com.threetrack.utils.Constants;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.netty.util.Constant;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    
    public final Logger LOG = Logger.getLogger(OrganizationServiceImpl.class.getName());

    @Autowired
    private OrganizationDao organizationDao;

    @Override
    public List<OrganizationResponseDto> getAllOrganizations(){
        return organizationDao.list().stream()
                .map(organization -> new OrganizationResponseDto(organization))
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationResponseDto getOrganizationId(Integer id){
        Organization organization = organizationDao.findById(id);

        return organization != null ? new OrganizationResponseDto(organization) : null;
    }

    @Override
    public boolean addOrganization(OrganizationRequestDto dto){
        try{
            Organization organization = new Organization();           
            organization.setName(dto.getName());
            organization.setDescription(dto.getDescription());
            organization.setState(Constants.STATE_ACTIVO);

            organizationDao.add(organization, 0);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean upOrganization(OrganizationRequestDto dto){
        try{
            Organization organization = organizationDao.findById(dto.getId());
            organization.setName(dto.getName());
            organization.setDescription(dto.getDescription());
            organization.setState(Constants.STATE_ACTIVO);

            organizationDao.update(organization, 0);
            return true;
        }catch (Exception e){
            LOG.warn(e.getMessage());
            return false;
        }        
    }

    @Override
    public boolean deleteOrganization(Integer id){
        try{
            return organizationDao.delete(id, 0);
        }catch(Exception e){
            return false;
        }
    }
}
