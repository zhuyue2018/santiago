package com.santiago.portal.entity.dto.vo;

public class RoleVO {
    private Long id;
    private String roleName;
    private String roleCode;
    private String roleResources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleResources() {
        return roleResources;
    }

    public void setRoleResources(String roleResources) {
        this.roleResources = roleResources;
    }
}
