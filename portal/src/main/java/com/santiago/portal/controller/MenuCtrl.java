package com.santiago.portal.controller;

import com.github.pagehelper.PageInfo;
import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.portal.entity.domain.PmsMenu;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.entity.domain.PmsRoleMenu;
import com.santiago.portal.entity.dto.query.MenuQuery;
import com.santiago.portal.entity.dto.request.MenuInsertRequest;
import com.santiago.portal.entity.enums.RoleCodeEnum;
import com.santiago.portal.mapper.PmsRoleMenuMapper;
import com.santiago.portal.service.MenuService;
import com.santiago.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/pms/menu")
public class MenuCtrl {
    @Autowired
    MenuService menuService;
    @Autowired
    PmsRoleMenuMapper roleMenuMapper;
    @Autowired
    RoleService roleService;

    @ModelAttribute
    public void init(Model model) {
        List<PmsMenu> menuList = menuService.list();
        model.addAttribute("menuList", menuList);
    }

    @RequestMapping(value = "/list")
    public String init() {
        return "pms/menu/list";
    }

    @PostMapping(value = "/insert")
    @Transactional
    @ResponseBody
    public UnionResp insert(@RequestBody MenuInsertRequest request) {
        PmsMenu menu = new PmsMenu();
        menu.setName(request.getInsertMenuName());
        menu.setLevel(request.getInsertMenuLevel());
        menu.setUrl(request.getInsertMenuUrl());
        menu.setParentId(request.getInsertPid());
        menu.setGmtCreate(new Date());
        menu.setGmtModified(new Date());
        menu.setStatus("ACTIVE");
        menu.setVersion(0L);
        menuService.insert(menu);
        PmsRole admin = roleService.getByRoleCode(RoleCodeEnum.ADMIN.getCode());
        PmsRoleMenu roleMenu = new PmsRoleMenu();
        roleMenu.setRoleId(admin.getId());
        roleMenu.setMenuId(menu.getId());
        roleMenuMapper.insert(roleMenu);
        return new UnionResp("000000", "cg");
    }

    @PostMapping(value = "/delete/{id}")
    @Transactional
    @ResponseBody
    public UnionResp delete(@PathVariable(value = "id") Long id) {
        menuService.deleteByPrimaryKey(id);
        PmsRoleMenu roleMenu = new PmsRoleMenu();
        roleMenu.setMenuId(id);
        roleMenuMapper.delete(roleMenu);
        return new UnionResp("000000", "cg");
    }

    @RequestMapping(value = "/query")
    @ResponseBody
    public List<PmsMenu> query(@RequestBody MenuQuery menuQuery) {
        handleMenuQuery(menuQuery);
        return menuService.list(menuQuery);
    }

    @RequestMapping(value = "/page")
    @ResponseBody
    public PageInfo<PmsMenu> page(@RequestBody MenuQuery menuQuery) {
        handleMenuQuery(menuQuery);
        PageInfo<PmsMenu> pageInfo = menuService.page(menuQuery);
        return pageInfo;
    }

    private void handleMenuQuery(MenuQuery menuQuery) {
        if (null == menuQuery.getPageNum()) {
            menuQuery.setPageNum(1);
        }
        if (null == menuQuery.getPageSize()) {
            menuQuery.setPageSize(5);
        }
    }
}
