package com.santiago.portal.controller;

import com.santiago.commons.dto.exception.BizException;
import com.santiago.commons.dto.resp.BaseResponse;
import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.portal.entity.exception.PmsBizException;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@Slf4j
@RestControllerAdvice(basePackages = "com.santiago.portal.controller")
public class SpringControllerAdvice {
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
        model.addAttribute("author", "santiago");
    }
}