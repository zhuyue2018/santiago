package com.santiago.cg.entity.domain;

import javax.persistence.*;

@Table(name = "sys_role")
public class SysRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_key")
    private String roleKey;

    @Column(name = "role_name")
    private String roleName;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return role_key
     */
    public String getRoleKey() {
        return roleKey;
    }

    /**
     * @param roleKey
     */
    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    /**
     * @return role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}