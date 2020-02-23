package com.santiago.portal;

import com.santiago.commons.util.JsonUtil;
import com.santiago.portal.controller.MerchantCtrl;
import com.santiago.portal.entity.dto.request.MerchantInsertReq;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MerchantCtrlTest extends BaseJunit {
    @Autowired
    MerchantCtrl merchantCtrl;

    private void mock(MerchantInsertReq req, String code) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/merchant/info/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JsonUtil.obj2JsonStrExcludeNull(req))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(Matchers.containsString(code)));
    }

    private MerchantInsertReq createMerchant() {
        MerchantInsertReq req = new MerchantInsertReq();
        req.setInsertMerchantName("jack");
        req.setInsertAccountNo("123456");
        req.setInsertMobile("13900000000");
        req.setInsertPassword("123456");
        req.setInsertPayPassword("123456");
        req.setInsertAutoSettle(true);
        req.setInsertSecurityRate("sign");
        req.setInsertMerchantServerIp("1.1.1.1");
        HashMap<String, String> payProductCode = new HashMap();
        payProductCode.put("001", "0.06");
        req.setInsertPayProductCode(payProductCode);
        req.setInsertRealName("jack real");
        return req;
    }

}
