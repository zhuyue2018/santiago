package com.santiago.account;

import com.santiago.account.controller.AccountingWss;
import com.santiago.account.entity.domain.TransactionDTO;
import com.santiago.commons.stress.test.IStressTest;
import com.santiago.commons.stress.test.StressTestCaseMain;
import com.santiago.commons.util.JsonUtil;
import com.santiago.commons.util.SequenceCreatorUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountingWssTests extends AccountApplicationTests {
    private MockMvc mockMvc;
    @Autowired
    AccountingWss accountingWss;

    @Autowired
    private WebApplicationContext wac;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/accounting/union")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.obj2JsonStrExcludeNull(build())))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"code\":\"000000\",\"msg\":\"接收待清分交易明细成功\"}"))
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        accountingWss.unionAccounting(build());
    }


    private TransactionDTO build() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTrxSerialNo(SequenceCreatorUtil.generateTrxSerialNo());
        transactionDTO.setTrxType("0001");
        transactionDTO.setAmount(BigDecimal.ONE);
        transactionDTO.setRescAccountNo("00000001");
        transactionDTO.setDestAccountNo("00000002");
        return transactionDTO;
    }

    public void stress00() throws Exception {
        long[] threadCount = {10};
        long[] loopExecuteCount = {100};
        List<IStressTest> listCase = new ArrayList<IStressTest>();
        listCase.add(new Stress());
        StressTestCaseMain.testMain(listCase, threadCount, loopExecuteCount);
    }

    class Stress implements IStressTest {
        public void testCase() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.post("/sttDetBook")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(JsonUtil.obj2JsonStrExcludeNull(build())))
                    .andExpect(status().isOk())
                    .andExpect(content().string("{\"code\":\"000000\",\"msg\":\"接收待清分交易明细成功\"}"))
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
        }
    }


}
