package com.santiago.settlement.wss;

import com.santiago.commons.dto.resp.SimpleResponse;
import com.santiago.commons.enums.VersionEnum;
import com.santiago.settlement.entity.domain.MerchantSettleConfig;
import com.santiago.settlement.service.MerchantSettleConfigService;
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
    MerchantSettleConfigService configService;
    public SimpleResponse insert() {
        MerchantSettleConfig settleConfig = new MerchantSettleConfig();
        settleConfig.setGmtCreate(new Date());
        settleConfig.setGmtModified(new Date());
        settleConfig.setVersion(VersionEnum.INIT.getCode());
        settleConfig.setCreater("settlement");
        settleConfig.setMerchantNo("");
        settleConfig.setSettleType("");
        settleConfig.setIsAutoSettle("");
        settleConfig.setSettlePeriod(1);
        configService.insert(settleConfig);
        return SimpleResponse.success();
    }
}
