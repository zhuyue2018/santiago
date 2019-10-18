package com.santiago.gateway;

import com.santiago.commons.util.JsonUtil;
import com.santiago.core.entity.dto.request.TradeRequest;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TradeCtrlTest extends BaseJunit {

    private void mock(TradeRequest request, String code) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/trade/preOrder")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JsonUtil.create()
                        .objectToJson(request))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(Matchers.containsString(code)));
    }

    @Test
//    @Transactional
//    @Rollback(value = true)
    public void successCase() throws Exception {
        TradeRequest request = createRequest();
        mock(request, "000000");
    }

    private TradeRequest createRequest() {
        TradeRequest request = new TradeRequest();
        request.setMerchantNo("001");
        request.setOrderNo("123458");
        request.setOrderPriceStr("99.99");
        request.setOrderIp("0.0.0.0");
        request.setPayProductCode("001");
        request.setOrderTimeStr("20191017203700");
        request.setOrderPeriodStr("30");
        request.setReturnUrl("returnUrl");
        request.setNotifyUrl("notifyUrl");
        request.setRemark("test");
        request.setSign("sign");
        return request;
    }
    /**
     * @NotNull(message = "merchantNo should not be null")
     *     private String merchantNo;
     *     @Size(max = 20, min = 1, message = "productName should be between 1 and 20")
     *     private String productName;
     *     @Size(max = 20, min = 1, message = "orderNo should be between 1 and 20")
     *     private String orderNo;
     *     @NotNull(message = "orderPriceStr should not be null")
     *     private String orderPriceStr;
     *     @NotNull(message = "orderIp should not be null")
     *     private String orderIp;
     *     @NotNull(message = "payProductCode should not be null")
     *     private String payProductCode;
     *     @NotNull(message = "orderTimeStr should not be null")
     *     private String orderTimeStr;
     *     @NotNull(message = "orderPeriodStr should not be null")
     *     private String orderPeriodStr;
     *     @NotNull(message = "returnUrl should not be null")
     *     private String returnUrl;
     *     @NotNull(message = "notifyUrl should not be null")
     *     private String notifyUrl;
     *     @NotNull(message = "remark should not be null")
     *     private String remark;
     *     @NotNull(message = "sign should not be null")
     *     private String sign; // 签名
     */

}
