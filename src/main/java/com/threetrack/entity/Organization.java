package com.threetrack.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ad_organization")
public class Organization extends GenericEntity{
    
    @Id
    @Column(name = "i_organization_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "v_name")
    private String name;

    @Column(name = "v_description")
    private String description;

    @Column(name = "c_state")
    private char state;

    public Organization() {}

    public Organization Clone(){
        Organization organization = new Organization();
        organization.setId(id);
        organization.setName(name);
        organization.setDescription(description);
        organization.setState(state);

        return organization;
    }
}
