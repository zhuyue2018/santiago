package com.santiago.portal.controller;

import com.github.pagehelper.PageInfo;
import com.santiago.order.api.dto.TradeOrderQuery;
import com.santiago.order.api.dto.TradeOrderVO;
import com.santiago.portal.annotation.WebLogParams;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.portal.entity.dto.vo.TradeDetail;
import com.santiago.portal.entity.enums.RoleCodeEnum;
import com.santiago.portal.service.OperatorService;
import com.santiago.portal.service.OrderService;
import com.santiago.portal.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping(value = "/trade/order")
public class OrderCtrl {
    @Autowired
    OrderService orderService;
    @Autowired
    OperatorService operatorService;
    @Autowired
    RoleService roleService;

    @RequestMapping(value = {"", "/list", "/init"})
    public String init() {
        return "trade/order/list";
    }

    @WebLogParams
    @RequestMapping(value = {"/page"})
    @ResponseBody
    public PageInfo<TradeOrderVO> page(@RequestBody TradeOrderQuery tradeOrderQuery) {
        handleTradeOrderQuery(tradeOrderQuery);
        return orderService.pageTradeOrderVO(tradeOrderQuery);
    }

    @RequestMapping(value = {"/detail"})
    @ResponseBody
    public TradeDetail detail(HttpServletRequest request) {
        String tradeOrderId = request.getParameter("id");
        return getTradeDetail(tradeOrderId);
    }

    private TradeDetail getTradeDetail(String tradeOrderId) {
        TradeDetail tradeDetail = new TradeDetail();
        tradeDetail.setTradeOrderId(tradeOrderId);
        return tradeDetail;
    }


//    @WebLogParams
//    @RequestMapping(value = {"/page"})
//    @ResponseBody
//    public PageInfo<TradeOrderVO> page(HttpServletRequest request) {
//        TradeOrderQuery tradeOrderQuery = httpRequest2Query(request);
//        return orderService.pageTradeOrderVO(tradeOrderQuery);
//    }

    private TradeOrderQuery httpRequest2Query(HttpServletRequest request) {
        TradeOrderQuery tradeOrderQuery = new TradeOrderQuery();
        Object status = request.getAttribute("status");
        Object merchantOrderNo = request.getAttribute("merchantOrderNo");
        Object merchantNo = request.getAttribute("merchantNo");
        Object payProductCode = request.getAttribute("payProductCode");
        Object trxNo = request.getAttribute("trxNo");
        Object pageNum = request.getAttribute("pageNum");
        Object pageSize = request.getAttribute("pageSize");
        if (null != status) {
            tradeOrderQuery.setStatus(status.toString());
        }
        if (null != merchantOrderNo) {
            tradeOrderQuery.setMerchantOrderNo(merchantOrderNo.toString());
        }
        if (null != merchantNo) {
            tradeOrderQuery.setMerchantNo(merchantNo.toString());
        }
        if (null != payProductCode) {
            tradeOrderQuery.setPayProductCode(payProductCode.toString());
        }
        if (null != trxNo) {
            tradeOrderQuery.setTrxNo(trxNo.toString());
        }
        if (null == tradeOrderQuery.getPageNum()) {
            tradeOrderQuery.setPageNum(1);
        } else {
            tradeOrderQuery.setPageNum((Integer) pageNum);
        }
        if (null == tradeOrderQuery.getPageSize()) {
            tradeOrderQuery.setPageSize(10);
        } else {
            tradeOrderQuery.setPageSize((Integer) pageSize);
        }
        return tradeOrderQuery;
    }

    private void handleTradeOrderQuery(TradeOrderQuery tradeOrderQuery) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PmsOperator operator = (PmsOperator) authentication.getPrincipal();
        if (StringUtils.isEmpty(tradeOrderQuery.getMerchantNo())) {
//            List<PmsRole> roleList = authentication.getAuthorities().stream().map(grantedAuthority -> (PmsRole) grantedAuthority).collect(Collectors.toList());
            if (!hasAdminRole(operator)) {
                List<String> merchantNoList = operatorService.listMerchantNoByOperatorId(operator.getId());
                if (CollectionUtils.isEmpty(merchantNoList)) {
                    tradeOrderQuery.setMerchantNo("000");
                } else {
                    StringBuilder sb = new StringBuilder();
                    merchantNoList.forEach(s -> sb.append(s).append("|"));
                    sb.deleteCharAt(sb.length() - 1);
                    tradeOrderQuery.setMerchantNo(sb.toString());
                }
            }
        } else {
            if (!hasAdminRole(operator)) {
                List<String> merchantNoList = operatorService.listMerchantNoByOperatorId(operator.getId());
                if (CollectionUtils.isEmpty(merchantNoList)) {
                    tradeOrderQuery.setMerchantNo("000");
                } else {
                    if (!merchantNoList.contains(tradeOrderQuery.getMerchantNo())) {
                        tradeOrderQuery.setMerchantNo("000");
                    }
                }
            }
        }
        if (null == tradeOrderQuery.getPageNum()) {
            tradeOrderQuery.setPageNum(1);
        }
        if (null == tradeOrderQuery.getPageSize()) {
            tradeOrderQuery.setPageSize(10);
        }
    }

    private boolean hasAdminRole(PmsOperator operator) {
        List<PmsRole> roleList = roleService.listByOperatorId(operator.getId());
        return roleList.stream().anyMatch(pmsRole -> RoleCodeEnum.ADMIN.getCode().equals(pmsRole.getRoleCode()));
    }
}
