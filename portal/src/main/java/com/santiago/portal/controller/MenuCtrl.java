package com.santiago.portal.controller;

import com.github.pagehelper.PageInfo;
import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.portal.entity.domain.PmsMenu;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.entity.domain.PmsRoleMenu;
import com.santiago.portal.entity.dto.query.MenuQuery;
import com.santiago.portal.entity.enums.RoleCodeEnum;
import com.santiago.portal.mapper.PmsRoleMenuMapper;
import com.santiago.portal.service.MenuService;
import com.santiago.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/resource")
public class MenuCtrl {
    @Autowired
    MenuService menuService;
    @Autowired
    PmsRoleMenuMapper roleMenuMapper;
    @Autowired
    RoleService roleService;

    @ModelAttribute
    public void init(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PmsMenu> menuTree= menuService.listMenuTree(((PmsOperator) principal).getId());
        model.addAttribute("menuTree", menuTree);
        List<PmsMenu> menuList = menuService.list();
        model.addAttribute("menuList", menuList);
    }

    @RequestMapping(value = "")
    public String init() {
        return "resource/resource";
    }

    @PostMapping(value = "/insert")
    @Transactional
    @ResponseBody
    public SimpleResponse insert(HttpServletRequest request) {
        String name = request.getParameter("memuName");
        String level = request.getParameter("menuLevel");
        String url = request.getParameter("menuUrl");
        String parentId = request.getParameter("menuPid");
        PmsMenu menu = new PmsMenu();
        menu.setName(name);
        menu.setLevel(Short.valueOf(level));
        menu.setUrl(url);
        menu.setParentId(Long.valueOf(parentId));
        menuService.insert(menu);
        PmsRole admin = roleService.getByRoleCode(RoleCodeEnum.ADMIN.getCode());
        PmsRoleMenu roleMenu = new PmsRoleMenu();
        roleMenu.setRoleId(admin.getId());
        roleMenu.setMenuId(menu.getId());
        roleMenuMapper.insert(roleMenu);
        return new SimpleResponse("000000", "cg");
    }

    @PostMapping(value = "/delete/{id}")
    @Transactional
    @ResponseBody
    public SimpleResponse delete(@PathVariable(value = "id") Long id) {
        menuService.deleteByPrimaryKey(id);
        PmsRoleMenu roleMenu = new PmsRoleMenu();
        roleMenu.setMenuId(id);
        roleMenuMapper.delete(roleMenu);
        return new SimpleResponse("000000", "cg");
    }

    @PostMapping(value = "query")
    @ResponseBody
    public PageInfo<PmsMenu> query(HttpServletRequest request) {
        MenuQuery queryDTO = transferQueryDTO(request);
        PageInfo<PmsMenu> pageInfo = menuService.page(queryDTO);
        return pageInfo;
    }

    private MenuQuery transferQueryDTO(HttpServletRequest request) {
        MenuQuery query = new MenuQuery();
        if (null == request.getParameter("pageNum")) {
            query.setPageNum(1);
        } else {
            query.setPageNum(Integer.parseInt(request.getParameter("pageNum")));
        }
        if (null == request.getParameter("pageSize")) {
            query.setPageSize(10);
        } else {
            query.setPageSize(Integer.parseInt(request.getParameter("pageSize")));
        }
        return query;
    }
}
