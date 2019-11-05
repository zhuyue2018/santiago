package com.santiago.core.wss;

import com.santiago.core.service.MerchantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MerchantInfoWss {
    @Autowired
    MerchantInfoService merchantInfoService;

    public void listNeedSettle() {
//        merchantInfoService.list();
    }
}
