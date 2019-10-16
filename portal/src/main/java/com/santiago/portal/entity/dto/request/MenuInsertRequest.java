package com.santiago.portal.entity.dto.request;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-16 13:18
 **/
public class MenuInsertRequest {
    private Short level;
    private Long parentId;
    private String name;
    private String url;

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
