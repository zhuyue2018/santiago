package com.santiago.portal;

import com.santiago.commons.util.JsonUtil;
import com.santiago.core.entity.dto.query.TradeOrderQuery;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderCtrlTest extends BaseJunit {

    private void mock(TradeOrderQuery query, String code) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/portal/order/list")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(JsonUtil.obj2JsonStrExcludeNull(query))
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(Matchers.containsString(code)));
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void successCase() throws Exception {
        TradeOrderQuery queryDTO = createQueryDTO();
        mock(queryDTO, "201910130907");
    }

    private TradeOrderQuery createQueryDTO() {
        TradeOrderQuery orderQuery = new TradeOrderQuery();
        orderQuery.setMerchantOrderNo("201910130907");
        return orderQuery;
    }

}
