package com.santiago.settlement.wss;

import com.santiago.commons.dto.resp.UnionResult;
import com.santiago.commons.enums.VersionEnum;
import com.santiago.settlement.entity.domain.MerchantSettleConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * @program: dependency
 * @description:
 * @author: zhuyue
 * @create: 2019-10-25 12:55
 **/
@Controller
public class MerchantSettleConfigWss {
    @Autowired
    com.santiago.settlement.service.MerchantSettleConfigService configService;
    public UnionResult insert(MerchantSettleConfig merchantSettleConfig) {
        MerchantSettleConfig settleConfig = new MerchantSettleConfig();
        settleConfig.setGmtCreate(new Date());
        settleConfig.setGmtModified(new Date());
        settleConfig.setVersion(VersionEnum.INIT.getCode());
        settleConfig.setCreater("settlement");
        settleConfig.setSettleType("0");
        settleConfig.setIsAutoSettle("0");
        settleConfig.setSettlePeriod(1);
        configService.insert(settleConfig);
        return UnionResult.ok();
    }
}
