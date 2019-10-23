package com.santiago.portal.controller;

import com.github.pagehelper.PageInfo;
import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.portal.entity.domain.*;
import com.santiago.portal.entity.dto.query.RoleQuery;
import com.santiago.portal.entity.dto.vo.RelateMenu;
import com.santiago.portal.entity.dto.vo.RoleVO;
import com.santiago.portal.mapper.PmsOperatorRoleMapper;
import com.santiago.portal.mapper.PmsRoleMenuMapper;
import com.santiago.portal.service.MenuService;
import com.santiago.portal.service.RoleService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/pms/role")
public class RoleCtrl {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Autowired
    PmsOperatorRoleMapper operatorRoleMapper;
    @Autowired
    PmsRoleMenuMapper roleMenuMapper;


    @ModelAttribute
    public void init(Model model) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<PmsMenu> menuTree= menuService.listMenuTree(((PmsOperator) principal).getId());
//        model.addAttribute("menuTree", menuTree);
        List<PmsMenu> menuList = menuService.list();
        model.addAttribute("menuList", menuList);
    }

    @RequestMapping(value = {"", "list", "init"})
    public String init() {
        return "pms/role/list";
    }

    @PostMapping(value = "/insert")
    @Transactional
    @ResponseBody
    public SimpleResponse insert(HttpServletRequest request) {
        String roleCode = request.getParameter("roleCode");
        String roleName = request.getParameter("roleName");
        PmsRole role = new PmsRole();
        role.setRoleCode(roleCode);
        role.setRoleName(roleName);
        roleService.insert(role);
        return new SimpleResponse("000000", "cg");
    }

//    @RequestMapping(value = "/relateMenuInit/{id}")
//    public String relateMenuInit(@PathVariable(value = "id") String id, Model model) {
//        model.addAttribute("id", id);
//        return "pms/role/relateMenu";
//    }

    @ResponseBody
    @RequestMapping(value = "/relateMenu/{id}")
    public List<RelateMenu> relateMenu(@PathVariable(value = "id") String id) {
        List<PmsMenu> allMenus = menuService.list();
        List<PmsMenu> relatedMenus = menuService.listByRoleId(Long.parseLong(id));
        List<RelateMenu> list = new ArrayList();
        allMenus.forEach(pmsMenu -> {
            RelateMenu menu = new RelateMenu();
            menu.setId(String.valueOf(pmsMenu.getId()));
            menu.setName(pmsMenu.getName());
            menu.setpId(String.valueOf(pmsMenu.getParentId()));
            if ("1".equals(pmsMenu.getLevel()) || "2".equals(pmsMenu.getLevel())) {
                menu.setOpen(false);
            }
            for (PmsMenu related : relatedMenus) {
                if (pmsMenu.equals(related)) {
                    menu.setChecked(true);
                    break;
                }
            }
            list.add(menu);
        });
        return list;
    }

    @PostMapping(value = "/delete/{id}")
    @Transactional
    @ResponseBody
    public SimpleResponse delete(@PathVariable(value = "id") Long id) {
        roleService.deleteByPrimaryKey(id);
        PmsOperatorRole operatorRole = new PmsOperatorRole();
        operatorRole.setRoleId(id);
        operatorRoleMapper.delete(operatorRole);
        PmsRoleMenu roleMenu = new PmsRoleMenu();
        roleMenu.setRoleId(id);
        roleMenuMapper.delete(roleMenu);
        return new SimpleResponse("000000", "cg");
    }

    @PostMapping(value = "/page")
    @ResponseBody
    public PageInfo<RoleVO> page(HttpServletRequest request) {
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
