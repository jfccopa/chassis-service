package com.threetrack.entity;

import javax.persistence.*;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
