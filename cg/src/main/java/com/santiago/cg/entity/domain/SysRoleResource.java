package com.santiago.cg.entity.domain;

import javax.persistence.*;

@Table(name = "sys_role_resource")
public class SysRoleResource {
    @Id
    @Column(name = "role_id")
    private Integer roleId;

    @Id
    @Column(name = "resource_id")
    private Integer resourceId;

    /**
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * @return resource_id
     */
    public Integer getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId
     */
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
}