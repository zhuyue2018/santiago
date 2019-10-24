package com.santiago.portal.entity.dto.request;

import javax.validation.constraints.NotNull;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-24 10:31
 **/
public class RelateMenuReq {
    @NotNull
    private Long roleId;
    @NotNull
    private Long menuId;
    @NotNull
    private String name;
    @NotNull
    private Integer level;
    @NotNull
    private boolean checked;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
