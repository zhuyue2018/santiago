package com.santiago.portal.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.santiago.commons.util.JsonUtil;
import com.santiago.portal.service.RedisService;
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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MenuServiceImpl implements MenuService {
    private static final JsonUtil jsonUtil = JsonUtil.create();

    @Autowired
    PmsMenuMapper menuMapper;
    @Autowired
    PmsRoleMapper roleMapper;
    @Autowired
    RoleService roleService;
    @Autowired
    PmsRoleMenuMapper roleMenuMapper;
    @Autowired
    RedisService redisService;

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
//        List<Object> list = redisService.lGet("menuTree:operatorId:" + operatorId, 0, -1);
//        if (!CollectionUtils.isEmpty(list)) {
//            List<PmsMenu> menuList = list.stream().map(o -> (PmsMenu) o).collect(Collectors.toList());
//            return menuList;
//        }
        Object o = redisService.get("menuTree:operatorId:" + operatorId);
        if (null != o) {
            List<PmsMenu> menuTree = jsonUtil.jsonToObject((String)o, List.class);
            return menuTree;
        }
        List<PmsMenu> menuList = listByOperatorId(operatorId);
        List<PmsMenu> menuTree = menuList.stream().filter(menu -> "1".equals(String.valueOf(menu.getLevel()))).collect(Collectors.toList());
        List<PmsMenu> menuLeaf = menuList.stream().filter(menu -> "2".equals(String.valueOf(menu.getLevel()))).collect(Collectors.toList());
        menuTree.forEach(menu1 -> {
            ArrayList<PmsMenu> sonMenus = new ArrayList<>();
            menuLeaf.forEach(menu2 -> {
                if (menu1.getId().compareTo(menu2.getParentId()) == 0) {
                    sonMenus.add(menu2);
                }
            });
            menu1.setSonMenus(sonMenus);
        });
        String menuTreeStr = jsonUtil.objectToJson(menuTree);
        redisService.set("menuTree:operatorId:" + operatorId, menuTreeStr, 300);
        return menuTree;
    }

    public List<PmsMenu> listByOperatorId(Long operatorId) {
        List<PmsRole> roleList = roleService.listByOperatorId(operatorId);
        Example roleMenuExample = new Example(PmsRoleMenu.class);
        Example.Criteria roleMenuCriteria = roleMenuExample.createCriteria();
        roleList.forEach(role -> {
            roleMenuCriteria.orEqualTo("roleId", role.getId());
        });
        List<PmsRoleMenu> roleMenuList = roleMenuMapper.selectByExample(roleMenuExample);

        Example menuExample = new Example(PmsMenu.class);
        Example.Criteria menuCriteria = menuExample.createCriteria();
        roleMenuList.forEach(roleMenu -> {
            menuCriteria.orEqualTo("id", roleMenu.getMenuId());
        });
        return menuMapper.selectByExample(menuExample);
    }

    public List<PmsMenu> listByRoleId(Long id) {
        Object o = redisService.get("menuList:roleId");
        if (null != o) {
            List<PmsMenu> menuList = jsonUtil.jsonToObject((String)o, List.class);
            return menuList;
        }

        PmsRoleMenu roleMenuTemp = new PmsRoleMenu();
        roleMenuTemp.setRoleId(id);
        List<PmsRoleMenu> roleMenuList = roleMenuMapper.select(roleMenuTemp);
        Example example = new Example(PmsMenu.class);
        Example.Criteria criteria = example.createCriteria();
        roleMenuList.forEach(roleMenu -> {
            criteria.orEqualTo("id", roleMenu.getMenuId());
        });
        List<PmsMenu> menuList = menuMapper.selectByExample(example);
        String menuListStr = jsonUtil.objectToJson(menuList);
        redisService.set("menuList:roleId" + id, menuListStr, 300);
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
