package com.threetrack.repository.dao;

import com.threetrack.entity.Organization;
import com.threetrack.repository.GenericDaoJpa;

import org.springframework.stereotype.Component;

@Component
public class OrganizationDaoJpa extends GenericDaoJpa<Organization, Integer> implements OrganizationDao{}
