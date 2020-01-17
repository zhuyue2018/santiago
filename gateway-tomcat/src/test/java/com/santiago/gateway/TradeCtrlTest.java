package com.santiago.gateway;

import com.santiago.commons.util.EncryptUtil;
import com.santiago.core.entity.dto.request.TradeRequest;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    @Transactional
    @Rollback
    public void concurrentCase() throws Exception {
        long start = new Date().getTime();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        threadPool.submit(new Call(countDownLatch, "1"));
        threadPool.submit(new Call(countDownLatch, "2"));
        threadPool.submit(new Call(countDownLatch, "3"));
        threadPool.submit(new Call(countDownLatch, "4"));
        threadPool.submit(new Call(countDownLatch, "5"));
        countDownLatch.await();
        long timeLong = new Date().getTime() - start;
        System.out.println(timeLong);

    }

    class Call implements Runnable {
        private CountDownLatch countDownLatch;
        private String prefix;

        public Call(CountDownLatch countDownLatch, String prefix) {
            this.countDownLatch = countDownLatch;
            this.prefix = prefix;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                TradeRequest request = createRequest(prefix + new Date().getTime());
                try {
                    mock(request, "000000");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            countDownLatch.countDown();
        }
    }

    private TradeRequest createRequest(String orderNo) {
        TradeRequest request = new TradeRequest();
        String merNo = "88882019102410001136";
        String productName = "volvo";
        request.setMerchantNo(merNo);
        request.setOrderNo(orderNo);
        request.setOrderPriceStr("99.99");
        request.setOrderIp("0.0.0.0");
        request.setProductName(productName);
        request.setPayProductCode("001");
        request.setOrderTimeStr("20191017203700");
        request.setOrderPeriodStr("30");
        request.setReturnUrl("returnUrl");
        request.setNotifyUrl("notifyUrl");
        request.setRemark("test");
        request.setSign(sign("123456", merNo, productName, orderNo));
        return request;
    }

    @Test
//    @Transactional
//    @Rollback
    public void successCase() throws Exception {
        TradeRequest request = createRequest("test" + System.currentTimeMillis());
        mock(request, "000000");
    }

    private String sign(String md5Key, String... params) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            sb.append(params[i]);
        }
        sb.append(md5Key);
        return EncryptUtil.encodeMD5String(sb.toString());
    }
}
