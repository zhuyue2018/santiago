package com.santiago.portal.entity.dto.vo;

public class RoleVO {
    private Integer id;
    private String roleName;
    private String roleKey;
    private String roleResources;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleResources() {
        return roleResources;
    }

    public void setRoleResources(String roleResources) {
        this.roleResources = roleResources;
    }
}
