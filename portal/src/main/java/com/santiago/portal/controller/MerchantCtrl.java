package com.santiago.portal.controller;

import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.commons.enums.ErrorCodeEnum;
import com.santiago.commons.enums.StatusEnum;
import com.santiago.core.wss.MerchantWss;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.domain.PmsOperatorMerchant;
import com.santiago.portal.entity.domain.PmsOperatorRole;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.core.entity.dto.MerchantInsertDTO;
import com.santiago.portal.entity.dto.request.MerchantInsertReq;
import com.santiago.portal.service.OperatorMerchantService;
import com.santiago.portal.service.OperatorRoleService;
import com.santiago.portal.service.OperatorService;
import com.santiago.portal.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-24 12:13
 **/
@Controller
@RequestMapping(value = "/merchant/merchantInfo")
public class MerchantCtrl {
    @Autowired
    MerchantWss merchantWss;
    @Autowired
    OperatorService operatorService;
    @Autowired
    OperatorRoleService operatorRoleService;
    @Autowired
    OperatorMerchantService operatorMerchantService;
    @Autowired
    RoleService roleService;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public SimpleResponse insert(@Valid @RequestBody MerchantInsertReq req, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }
        MerchantInsertDTO dto = createMerchantInsertDTO(req);
        Long merchantId = merchantWss.register(dto);
        PmsOperator operator = operatorService.create(1L, StatusEnum.SUCCESS.getCode(), "portal", req);
        PmsRole merchantRole = roleService.getMerchant();
        PmsOperatorRole operatorRole = operatorRoleService.create(operator.getId(), merchantRole.getId());
        PmsOperatorMerchant pmsOperatorMerchant = operatorMerchantService.create(operator.getId(), merchantId);
        return new SimpleResponse(ErrorCodeEnum.SUCCESS.getCode(), "merchant inserted!");
    }



    @RequestMapping(value = "/view")
    public String view() {
        return "merchant/list";
    }

    private MerchantInsertDTO createMerchantInsertDTO(MerchantInsertReq req) {

        return null;
    }


}