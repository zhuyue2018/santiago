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

    @RequestMapping(value = {"", "list", "init"}, method = RequestMethod.GET)
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

    @ResponseBody
    @GetMapping(value = "/relateMenu/{id}")
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
            relatedMenus.forEach(pmsMenu1 -> {
                if (pmsMenu.equals(pmsMenu1)) {
                    menu.setChecked(true);
                }
            });
            list.add(menu);
        });
        return list;
        /**
         *         var zNodes =[
         *             { id:1, pId:0, name:"随意勾选 1", open:false},
         *             { id:11, pId:1, name:"随意勾选 1-1", open:false},
         *             { id:111, pId:11, name:"随意勾选 1-1-1"},
         *             { id:112, pId:11, name:"随意勾选 1-1-2"},
         *             { id:12, pId:1, name:"随意勾选 1-2", open:false},
         *             { id:121, pId:12, name:"随意勾选 1-2-1"},
         *             { id:122, pId:12, name:"随意勾选 1-2-2"},
         *             { id:2, pId:0, name:"随意勾选 2", checked:true, open:false},
         *             { id:21, pId:2, name:"随意勾选 2-1"},
         *             { id:22, pId:2, name:"随意勾选 2-2", open:false},
         *             { id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
         *             { id:222, pId:22, name:"随意勾选 2-2-2"},
         *             { id:23, pId:2, name:"随意勾选 2-3"}
         *         ];
         */
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
