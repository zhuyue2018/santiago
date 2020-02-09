package com.santiago.portal.controller;

import com.santiago.portal.annotation.ExtApiIdempotent;
import com.santiago.portal.annotation.ExtApiToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderPageController {

    @RequestMapping("/indexPage")
    @ExtApiToken
    public String indexPage(HttpServletRequest req) {
        return "indexPage";
    }

    @RequestMapping("/addOrderPage")
    @ExtApiIdempotent(value = "EXTAPIFROM")
    public void addOrder() {

    }

}
