package com.santiago.portal.controller;

import com.github.pagehelper.PageInfo;
import com.zhuyue.pay0929.commons.dto.resp.SimpleResponse;
import com.zhuyue.pay0929.portal.entity.domain.*;
import com.zhuyue.pay0929.portal.entity.dto.query.RoleQuery;
import com.zhuyue.pay0929.portal.entity.vo.RoleVO;
import com.zhuyue.pay0929.portal.mapper.*;
import com.zhuyue.pay0929.portal.service.SysResourceService;
import com.zhuyue.pay0929.portal.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/role")
public class RoleCtrl {
    @Autowired
    SysRoleMapper roleMapper;
    @Autowired
    SysResourceService resourceService;
    @Autowired
    SysUserRoleMapper userRoleMapper;
    @Autowired
    SysRoleResourceMapper roleResourceMapper;
    @Autowired
    SysRoleService roleService;
    @Autowired
    SysResourceMapper resourceMapper;


    @ModelAttribute
    public void init(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SysResource> menus= resourceService.listMenuTree(((SysUser) principal).getId());
        model.addAttribute("menus", menus);
        SysResource resource = new SysResource();
        List<SysResource> resourceList = resourceMapper.select(resource);
        model.addAttribute("resourceList", resourceList);
    }

    @RequestMapping(value = "")
    public String init() {
        return "/resource/role";
    }

    @PostMapping(value = "/insert")
    @Transactional
    @ResponseBody
    public SimpleResponse insert(HttpServletRequest request) {
        String roleKey = request.getParameter("insertRoleKey");
        String roleName = request.getParameter("insertRoleName");
        SysRole role = new SysRole();
        role.setRoleKey(roleKey);
        role.setRoleName(roleName);
        roleMapper.insert(role);
        return new SimpleResponse("000000", "cg");
    }

    @PostMapping(value = "/delete/{id}")
    @Transactional
    @ResponseBody
    public SimpleResponse delete(@PathVariable(value = "id") Integer id) {
        roleMapper.deleteByPrimaryKey(id);
        SysUserRole userRole = new SysUserRole();
        userRole.setRoleId(id);
        userRoleMapper.delete(userRole);
        SysRoleResource roleResource = new SysRoleResource(id);
        roleResourceMapper.delete(roleResource);
        return new SimpleResponse("000000", "cg");
    }

    @PostMapping(value = "query")
    @ResponseBody
    public PageInfo<RoleVO> query(HttpServletRequest request) {
        RoleQuery queryDTO = transferQueryDTO(request);
        PageInfo<RoleVO> pageInfo = roleService.page(queryDTO);
        return pageInfo;
    }

    private RoleQuery transferQueryDTO(HttpServletRequest request) {
        RoleQuery query = new RoleQuery();
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
