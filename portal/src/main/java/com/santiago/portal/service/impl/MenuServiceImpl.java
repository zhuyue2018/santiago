package com.santiago.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santiago.portal.entity.domain.PmsMenu;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.entity.domain.PmsRoleMenu;
import com.santiago.portal.entity.dto.query.MenuQuery;
import com.santiago.portal.mapper.PmsMenuMapper;
import com.santiago.portal.mapper.PmsRoleMapper;
import com.santiago.portal.mapper.PmsRoleMenuMapper;
import com.santiago.portal.service.MenuService;
import com.santiago.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    PmsMenuMapper menuMapper;
    @Autowired
    PmsRoleMapper roleMapper;
    @Autowired
    RoleService roleService;
    @Autowired
    PmsRoleMenuMapper roleMenuMapper;

    @Override
    public PageInfo<PmsMenu> page(MenuQuery queryDTO) {
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        Example example = new Example(PmsMenu.class);
        Example.Criteria criteria = example.createCriteria();
        // todo
        List<PmsMenu> list = menuMapper.selectByExample(example);
        PageInfo<PmsMenu> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    List<PmsMenu> list(MenuQuery queryDTO) {
        Example example = new Example(PmsMenu.class);
        Example.Criteria criteria = example.createCriteria();
        if (null != queryDTO.getLevel()) {
            criteria.andEqualTo("level", queryDTO.getLevel());
        }
        if (null != queryDTO.getParentId()) {
            criteria.andEqualTo("parentId", queryDTO.getParentId());
        }
        return menuMapper.selectByExample(example);
    }

    public List<PmsMenu> listMenuTree(Long operatorId) {
        Set<PmsMenu> menuTreeSet = new HashSet<>();
        Set<PmsMenu> menuLeafSet = new HashSet<>();
        List<PmsRole> roleList = roleService.listByOperatorId(operatorId);
        roleList.forEach(role -> {
            List<PmsMenu> menuList1 = listByRoleId(role.getId());
            List<PmsMenu> collect1 = menuList1.stream().filter(menu -> "1".equals(String.valueOf(menu.getLevel()))).collect(Collectors.toList());
            menuTreeSet.addAll(collect1);
            List<PmsMenu> collect2 = menuList1.stream().filter(menu -> "2".equals(String.valueOf(menu.getLevel()))).collect(Collectors.toList());
            menuLeafSet.addAll(collect2);
        });
        List<PmsMenu> menuTree = new ArrayList<>(menuTreeSet);
        menuTree.forEach(menu1 -> {
            ArrayList<PmsMenu> sonMenus = new ArrayList<>();
            menuLeafSet.forEach(menu2 -> {
                if (menu1.getId().compareTo(menu2.getParentId()) == 0) {
                    sonMenus.add(menu2);
                }
            });
            menu1.setSonMenus(sonMenus);
        });
        return menuTree;
    }

    public List<PmsMenu> listByRoleId(Long id) {
        PmsRoleMenu roleMenuTemp = new PmsRoleMenu();
        roleMenuTemp.setRoleId(id);
        List<PmsRoleMenu> roleMenuList = roleMenuMapper.select(roleMenuTemp);
        List<PmsMenu> menuList = new ArrayList<>();
        roleMenuList.forEach(roleMenu -> {
            menuList.add(menuMapper.selectByPrimaryKey(roleMenu.getMenuId()));
        });
        return menuList;
    }

    @Override
    public List<PmsMenu> list() {
        return menuMapper.selectAll();
    }

    @Override
    public void insert(PmsMenu menu) {
        menuMapper.insert(menu);
    }

    @Override
    public void deleteByPrimaryKey(Long id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
