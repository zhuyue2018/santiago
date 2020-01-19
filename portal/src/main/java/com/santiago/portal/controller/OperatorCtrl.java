package com.santiago.portal.controller;

import com.github.pagehelper.PageInfo;
import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.domain.PmsOperatorRole;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.entity.dto.query.OperatorQuery;
import com.santiago.portal.entity.dto.vo.OperatorVO;
import com.santiago.portal.mapper.PmsOperatorRoleMapper;
import com.santiago.portal.service.MenuService;
import com.santiago.portal.service.OperatorService;
import com.santiago.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class OperatorCtrl {
    @Autowired
    OperatorService operatorService;
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Autowired
    PmsOperatorRoleMapper operatorRoleMapper;


    @ModelAttribute
    public void init(Model model) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        List<PmsMenu> menuTree= menuService.listMenuTree(((PmsOperator) principal).getId());
//        model.addAttribute("menuTree", menuTree);
        List<PmsRole> roleList = roleService.list();
        model.addAttribute("roleList", roleList);
    }

    @RequestMapping(value = "")
    public String init() {
        return "/menu/user";
    }

    @PostMapping(value = "/insert")
    @Transactional
    @ResponseBody
    public UnionResp insert(HttpServletRequest request) {
        String username = request.getParameter("insertUsername");
        String password = request.getParameter("insertPassword");
        PmsOperator operator = new PmsOperator();
        operator.setLoginName(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        operator.setLoginPwd(encoder.encode(password));
        operatorService.insert(operator);
        List<PmsRole> roleList = roleService.list();
        roleList.forEach(PmsRole -> {
            String roleId = "roleId:" + PmsRole.getId();
            String selected = request.getParameter(roleId);
            if ("on".equals(selected)) {
                PmsOperatorRole operatorRole = new PmsOperatorRole();
                operatorRole.setOperatorId(operator.getId());
                operatorRole.setRoleId(PmsRole.getId());
                operatorRoleMapper.insert(operatorRole);
            }
        });
        return new UnionResp("000000", "cg");
    }

    @PostMapping(value = "/delete/{id}")
    @Transactional
    @ResponseBody
    public UnionResp delete(@PathVariable(value = "id") Long id) {
        operatorService.deleteByPrimaryKey(id);
        PmsOperatorRole operatorRole = new PmsOperatorRole();
        operatorRole.setOperatorId(id);
        operatorRoleMapper.delete(operatorRole);
        return new UnionResp("000000", "cg");
    }



    @PostMapping(value = "query")
    @ResponseBody
    public PageInfo<OperatorVO> query(HttpServletRequest request) {
        OperatorQuery queryDTO = transferQueryDTO(request);
        PageInfo<OperatorVO> pageInfo = operatorService.page(queryDTO);
        return pageInfo;
    }

    private OperatorQuery transferQueryDTO(HttpServletRequest request) {
        OperatorQuery query = new OperatorQuery();
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
