package com.santiago.portal.controller;

import com.santiago.portal.entity.domain.PmsMenu;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.service.MenuService;
import com.santiago.portal.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class LoginCtrl {
    private static final Logger logger = LoggerFactory.getLogger(LoginCtrl.class);
    @Autowired
    MenuService menuService;
    @Autowired
    RedisService redisService;
    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @RequestMapping(value = {"/login", ""})
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PmsOperator operator = (PmsOperator)authentication.getPrincipal();
        logger.info("当前用户为:{},获取菜单开始");
        List<PmsMenu> menuTree= menuService.listMenuTree(operator.getId());
        System.out.println(new Date().toString());
        model.addAttribute("menuTree", menuTree);
        String welcomeMsg = String.format("欢迎您，%s！您的角色为，%s", (operator).getUsername(), "");
        model.addAttribute("welcomeMsg", welcomeMsg);
        return "index";
    }

    public String tryCache(String viewName, Model model, HttpServletRequest request, HttpServletResponse response) {
        String key = "view:name:1:" + viewName;
        Object chcheHtml = redisService.get(key);
        if (null != chcheHtml && StringUtils.isNoneBlank(chcheHtml.toString())) {
            return chcheHtml.toString() + "from cache";
        }
        WebContext swc = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        String renderHtml = thymeleafViewResolver.getTemplateEngine().process(viewName, swc);
        if (!StringUtils.isEmpty(renderHtml)) {
            redisService.set(key, renderHtml, TimeUnit.MINUTES.toSeconds(10));
        }
        return renderHtml;
    }
}
