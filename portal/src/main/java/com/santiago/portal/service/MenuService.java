package com.santiago.portal.service;


import com.github.pagehelper.PageInfo;
import com.santiago.portal.entity.domain.PmsMenu;
import com.santiago.portal.entity.dto.query.MenuQuery;

import java.util.List;

public interface MenuService {
    List<PmsMenu> listMenuTree(Long operatorId);
    PageInfo<PmsMenu> page(MenuQuery menuQuery);
    List<PmsMenu> listByRoleId(Long id);
    List<PmsMenu> list();
    void insert(PmsMenu menu);
    void deleteByPrimaryKey(Long id);
    List<PmsMenu> list(MenuQuery menuQuery);
    List<PmsMenu> listByPid(Long pid);
}
