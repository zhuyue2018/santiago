package com.santiago.portal.entity.dto.query;

import com.santiago.commons.dto.query.BaseQuery;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-15 13:15
 **/
public class MenuQuery extends BaseQuery {
    private Long level;
    private Long parentId;
    private String menuName;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
