package com.santiago.portal.controller;

import com.github.pagehelper.PageInfo;
import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.portal.entity.domain.PmsMenu;
import com.santiago.portal.entity.domain.PmsOperator;
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
public class ResourceCtrl {
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
        String resourceName = request.getParameter("insertResourceName");
        String type = request.getParameter("insertType");
        String url = request.getParameter("insertUrl");
        String pid = request.getParameter("insertPid");
        PmsMenu menu = new PmsMenu();
        menu.setResourceName(resourceName);
        menu.setType(Integer.parseInt(type));
        menu.setUrl(url);
        menu.setPid(Integer.parseInt(pid));
        menuService.insert(resource);
        SysRoleResource roleResource = new SysRoleResource(1, resource.getId());
        rolemenuService.insert(roleResource);
        return new SimpleResponse("000000", "cg");
    }

    private void validateInsert(SysResource resource) {
        if (!"1".equals(String.valueOf(resource.getType()))
                && null == resource.getPid()) {
            throw new ResourceBizException("000001", "非一级菜单的父菜单id不能为空");
        }
    }

    @PostMapping(value = "/delete/{id}")
    @Transactional
    @ResponseBody
    public SimpleResponse delete(@PathVariable(value = "id") Integer id) {
        menuService.deleteByPrimaryKey(id);
        SysRoleResource roleResource = new SysRoleResource();
        roleResource.setResourceId(id);
        rolemenuService.delete(roleResource);
        return new SimpleResponse("000000", "cg");
    }

    @PostMapping(value = "query")
    @ResponseBody
    public PageInfo<PmsMenu> query(HttpServletRequest request) {
        ResourceQuery queryDTO = transferQueryDTO(request);
        PageInfo<PmsMenu> pageInfo = resourceService.page(queryDTO);
        return pageInfo;
    }

    private ResourceQuery transferQueryDTO(HttpServletRequest request) {
        ResourceQuery query = new ResourceQuery();
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
