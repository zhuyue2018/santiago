package com.santiago.portal.controller;

import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.commons.enums.ErrorCodeEnum;
import com.santiago.commons.enums.StatusEnum;
import com.santiago.core.entity.domain.PayProduct;
import com.santiago.core.entity.dto.MerchantInsertDTO;
import com.santiago.core.wss.MerchantWss;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.domain.PmsOperatorMerchant;
import com.santiago.portal.entity.domain.PmsOperatorRole;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.entity.dto.request.MerchantInsertReq;
import com.santiago.portal.service.OperatorMerchantService;
import com.santiago.portal.service.OperatorRoleService;
import com.santiago.portal.service.OperatorService;
import com.santiago.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
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
