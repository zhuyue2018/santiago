package com.santiago.portal.controller;

import com.santiago.core.entity.domain.PayProduct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-24 12:13
 **/
@Controller
@RequestMapping(value = "/pay/payProduct")
public class PayProductCtrl {

    @ResponseBody
    @RequestMapping(value = "/list")
    public List<PayProduct> list() {
        List<PayProduct> list = new ArrayList();
        PayProduct payProduct = new PayProduct();
        payProduct.setPayProductCode("123");
        list.add(payProduct);
        PayProduct payProduct2 = new PayProduct();
        payProduct2.setPayProductCode("1234");
        list.add(payProduct2);
        return list;
    }
}
