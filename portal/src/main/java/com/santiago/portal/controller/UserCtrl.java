package com.santiago.portal.controller;

import com.github.pagehelper.PageInfo;
import com.zhuyue.pay0929.commons.dto.resp.SimpleResponse;
import com.zhuyue.pay0929.portal.entity.domain.*;
import com.zhuyue.pay0929.portal.entity.dto.query.UserQuery;
import com.zhuyue.pay0929.portal.entity.vo.UserVO;
import com.zhuyue.pay0929.portal.mapper.*;
import com.zhuyue.pay0929.portal.service.SysResourceService;
import com.zhuyue.pay0929.portal.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserCtrl {
    @Autowired
    SysRoleMapper roleMapper;
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    SysResourceService resourceService;
    @Autowired
    SysUserService userService;
    @Autowired
    SysUserRoleMapper userRoleMapper;


    @ModelAttribute
    public void init(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SysResource> menus= resourceService.listMenuTree(((SysUser) principal).getId());
        model.addAttribute("menus", menus);
        List<SysRole> roleList = roleMapper.listAll();
        model.addAttribute("roleList", roleList);
    }


    @RequestMapping(value = "")
    public String init() {
        return "/resource/user";
    }

    @PostMapping(value = "/insert")
    @Transactional
    @ResponseBody
    public SimpleResponse insert(HttpServletRequest request) {
        String username = request.getParameter("insertUsername");
        String password = request.getParameter("insertPassword");
        SysUser user = new SysUser();
        user.setUsername(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        userMapper.insert(user);
        List<SysRole> roleList = roleMapper.listAll();
        roleList.forEach(sysRole -> {
            String roleId = "roleId:" + sysRole.getId();
            String selected = request.getParameter(roleId);
            if ("on".equals(selected)) {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(sysRole.getId());
                userRoleMapper.insert(userRole);
            }
        });
        return new SimpleResponse("000000", "cg");
    }

    @PostMapping(value = "/delete/{id}")
    @Transactional
    @ResponseBody
    public SimpleResponse delete(@PathVariable(value = "id") Integer id) {
        userMapper.deleteByPrimaryKey(id);
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(id);
        userRoleMapper.delete(userRole);
        return new SimpleResponse("000000", "cg");
    }



    @PostMapping(value = "query")
    @ResponseBody
    public PageInfo<UserVO> query(HttpServletRequest request) {
        UserQuery queryDTO = transferQueryDTO(request);
        PageInfo<UserVO> pageInfo = userService.page(queryDTO);
        return pageInfo;
    }

    private UserQuery transferQueryDTO(HttpServletRequest request) {
        UserQuery query = new UserQuery();
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
