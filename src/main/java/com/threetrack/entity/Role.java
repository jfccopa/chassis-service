package com.threetrack.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ad_role")
public class Role extends GenericEntity{

    @Id
    @Column(name = "i_role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="v_name",length = 32)
    private String name;

    @Column(name="ad_code",length = 8)
    private String code;

    @Column(name="c_state",length = 1)
    private String state;

    public Role() {
    }
}
