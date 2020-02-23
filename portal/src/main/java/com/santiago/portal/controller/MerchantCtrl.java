package com.santiago.portal.controller;

import com.github.pagehelper.PageInfo;
import com.santiago.commons.dto.resp.UnionResp;
import com.santiago.commons.enums.StatusEnum;
import com.santiago.portal.annotation.ExtApiIdempotent;
import com.santiago.portal.annotation.ExtApiToken;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.entity.dto.MerchantInsertDTO;
import com.santiago.portal.entity.dto.request.MerchantInsertReq;
import com.santiago.portal.entity.dto.request.MerchantQueryReq;
import com.santiago.portal.service.*;
import com.santiago.portal.service.impl.RedisTokenService;
import com.santiago.portal.wss.MerchantWss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-24 12:13
 **/
@Controller
@RequestMapping(value = "/merchant/info")
public class MerchantCtrl {
    @Autowired
    MerchantWss merchantWss;
    @Autowired
    MerchantInfoService merchantInfoService;
    @Autowired
    OperatorService operatorService;
    @Autowired
    OperatorRoleService operatorRoleService;
    @Autowired
    OperatorMerchantService operatorMerchantService;
    @Autowired
    RoleService roleService;
    @Autowired
    RedisTokenService redisTokenUtils;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @Transactional
    public UnionResp insert(@Valid @RequestBody MerchantInsertReq req, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }
        String token = req.getToken();
        if (!redisTokenUtils.findToken(token)) {
            return UnionResp.buildResp("999998", "token过期或重复提交", null);
        }
        MerchantInsertDTO dto = createMerchantInsertDTO(req);
        Long merchantId = merchantWss.register(dto);
        PmsOperator operator = operatorService.create(1L, StatusEnum.SUCCESS.getCode(), "portal", req);
        PmsRole merchantRole = roleService.getMerchant();
        operatorRoleService.create(operator.getId(), merchantRole.getId());
        operatorMerchantService.create(operator.getId(), merchantId);
        return UnionResp.success();
    }

    @GetMapping(value = "/list")
    @ExtApiToken
    public String list(HttpServletRequest httpServletRequest, Model model) {
        model.addAttribute("token", httpServletRequest.getAttribute("token"));
        return "merchant/list";
    }

    @PostMapping(value = "/page")
    @ResponseBody
    public PageInfo page(@Valid @RequestBody MerchantQueryReq req) {
        PageInfo list =  merchantInfoService.page();
        return list;
    }

    private MerchantInsertDTO createMerchantInsertDTO(MerchantInsertReq req) {
        MerchantInsertDTO dto = new MerchantInsertDTO();
        dto.setMerchantName(req.getInsertMerchantName());
        dto.setAccountNo(req.getInsertAccountNo());
        dto.setMobile(req.getInsertMobile());
        dto.setPassword(req.getInsertPassword());
        dto.setPayPassword(req.getInsertPayPassword());
        dto.setAutoSettle(req.isInsertAutoSettle());
        dto.setSecurityRate(req.getInsertSecurityRate());
        dto.setMerchantServerIp(req.getInsertMerchantServerIp());
        dto.setPayProductCode(req.getInsertPayProductCode());
        dto.setRealName(req.getInsertRealName());
        return dto;
    }


}
