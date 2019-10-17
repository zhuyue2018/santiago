package com.santiago.portal.entity.dto.request;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-16 13:18
 **/
public class MenuInsertRequest {
    private Short insertMenuLevel;
    private Long insertPid;
    private String insertMenuName;
    private String insertMenuUrl;

    public Short getInsertMenuLevel() {
        return insertMenuLevel;
    }

    public void setInsertMenuLevel(Short insertMenuLevel) {
        this.insertMenuLevel = insertMenuLevel;
    }

    public Long getInsertPid() {
        return insertPid;
    }

    public void setInsertPid(Long insertPid) {
        this.insertPid = insertPid;
    }

    public String getInsertMenuName() {
        return insertMenuName;
    }

    public void setInsertMenuName(String insertMenuName) {
        this.insertMenuName = insertMenuName;
    }

    public String getInsertMenuUrl() {
        return insertMenuUrl;
    }

    public void setInsertMenuUrl(String insertMenuUrl) {
        this.insertMenuUrl = insertMenuUrl;
    }
}
