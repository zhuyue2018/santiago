package com.santiago.portal.controller;

import com.santiago.portal.entity.domain.PmsMenu;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LoginCtrl {
    @Autowired
    MenuService menuService;

    @GetMapping(value = {"login", ""})
    public String login() {
        return "/login";
    }

    @GetMapping("index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PmsOperator operator = (PmsOperator)authentication.getPrincipal();
        List<PmsMenu> menus= menuService.listMenuTree(operator.getId());
        model.addAttribute("menus", menus);
        String welcomeMsg = String.format("欢迎您，%s！您的角色为，%s", (operator).getUsername(), "");
        model.addAttribute("welcomeMsg", welcomeMsg);
        return "/index";
    }
}
