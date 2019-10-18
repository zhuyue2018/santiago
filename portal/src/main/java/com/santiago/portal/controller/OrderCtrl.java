package com.santiago.portal.controller;

import com.github.pagehelper.PageInfo;
import com.santiago.core.entity.vo.TradeOrderVO;
import com.santiago.portal.entity.domain.PmsOperator;
import com.santiago.portal.entity.domain.PmsRole;
import com.santiago.core.entity.dto.query.TradeOrderQuery;
import com.santiago.portal.entity.enums.RoleCodeEnum;
import com.santiago.portal.service.OperatorService;
import com.santiago.portal.service.OrderService;
import com.santiago.portal.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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

    @RequestMapping(value = {"", "/list", "/init"}, method = RequestMethod.GET)
    public String init() {
        return "trade/order/list";
    }

    @RequestMapping(value = {"/page"})
    @ResponseBody
    public PageInfo<TradeOrderVO> page(@RequestBody TradeOrderQuery tradeOrderQuery, HttpServletRequest request) {
        handleTradeOrderQuery(tradeOrderQuery, request);
        return orderService.pageTradeOrderVO(tradeOrderQuery);
    }

    private void handleTradeOrderQuery(TradeOrderQuery tradeOrderQuery, HttpServletRequest request) {
        if (StringUtils.isEmpty(tradeOrderQuery.getMerchantNo())) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            PmsOperator operator = (PmsOperator) authentication.getPrincipal();
//            List<PmsRole> roleList = authentication.getAuthorities().stream().map(grantedAuthority -> (PmsRole) grantedAuthority).collect(Collectors.toList());
            if (!hasAdminRole(operator)) {
                List<String> merchantNoList = operatorService.listMerchantNoByOperatorId(operator.getId());
                StringBuilder sb = new StringBuilder();
                merchantNoList.forEach(s -> sb.append(s).append("|"));
                sb.deleteCharAt(sb.length() - 1);
                tradeOrderQuery.setMerchantNo(sb.toString());
            }
        }
        Integer pageNum = tradeOrderQuery.getPageNum();
        Integer pageSize = tradeOrderQuery.getPageSize();
        if (null == pageNum) {
            tradeOrderQuery.setPageNum(1);
        }
        if (null == pageSize) {
            tradeOrderQuery.setPageSize(10);
        }
    }

    private boolean hasAdminRole(PmsOperator operator) {
        List<PmsRole> roleList = roleService.listByOperatorId(operator.getId());
        return roleList.stream().anyMatch(pmsRole -> RoleCodeEnum.ADMIN.getCode().equals(pmsRole.getRoleCode()));
    }
}
