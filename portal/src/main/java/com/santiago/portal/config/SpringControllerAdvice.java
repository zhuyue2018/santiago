package com.santiago.portal.config;

import com.santiago.commons.dto.resp.BaseResponse;
import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.portal.entity.domain.PmsMenu;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.exception.PmsBizException;
import com.santiago.portal.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice(basePackages = "com.santiago.portal.controller")
public class SpringControllerAdvice {
    @Autowired
    MenuService menuService;

    @ExceptionHandler(PmsBizException.class)
    public BaseResponse handleBizException(PmsBizException e) {
        return new SimpleResponse(e.getErrCode(), e.getErrMsg());
    }
    @ExceptionHandler(Exception.class)
    public BaseResponse handleException(Exception e) {
        return new SimpleResponse("todo", e.getMessage());
    }
    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
//        log.info("binder.getFieldDefaultPrefix {}",binder.getFieldDefaultPrefix());
//        log.info("binder.getFieldMarkerPrefix {}",binder.getFieldMarkerPrefix());
    }
    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!"anonymousUser".equals(principal.toString())) {
            List<PmsMenu> menuTree= menuService.listMenuTree(((PmsOperator) principal).getId());
            model.addAttribute("menuTree", menuTree);
        }
        model.addAttribute("author", "santiago");
    }
}