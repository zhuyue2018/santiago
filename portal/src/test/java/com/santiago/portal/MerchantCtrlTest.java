package com.santiago.portal;

import com.santiago.commons.util.JsonUtil;
import com.santiago.core.entity.dto.query.TradeOrderQuery;
import com.santiago.portal.entity.dto.request.MerchantInsertReq;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MerchantCtrlTest extends BaseJunit {

    private void mock(MerchantInsertReq req, String code) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/merchant/merchantInfo/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JsonUtil.create()
                        .objectToJson(req))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(Matchers.containsString(code)));
    }

    @Test
//    @Transactional
//    @Rollback(value = true)
    public void successCase() throws Exception {
        MerchantInsertReq merchant = createMerchant();
        mock(merchant, "000000");
    }

    private MerchantInsertReq createMerchant() {
        MerchantInsertReq req = new MerchantInsertReq();
//        req.setMerchantName("jack");
//        req.setAccountNo("123456");
//        req.setMobile("13900000000");
//        req.setPassword("123456");
//        req.setPayPassword("123456");
//        req.setAutoSettle(true);
//        req.setSecurityRate("sign");
//        req.setMerchantServerIp("1.1.1.1");
//        Map<String, String> payProductCode = new HashMap();
//        payProductCode.put("001", "0.06");
//        req.setPayProductCode(payProductCode);
//        req.setRealName("jack real");
        return req;
    }

}
